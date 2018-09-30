package hu.bme.mit.swsv.ris.tsm.test;
import hu.bme.mit.swsv.ris.common.*;
import hu.bme.mit.swsv.ris.common.logging.LoggerWrapper;
import hu.bme.mit.swsv.ris.tsm.SignalMapper;
import hu.bme.mit.swsv.ris.tsm.impl.SafetyLogicImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.InOrder;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * Test class for local decision procedure implemented in
 * {@link SafetyLogicImpl} class.
 *
 * @author Canberk Zeytin, Melik Göçmen
 *
 */
@RunWith(Parameterized.class)

public class SafetyLogicLocalTest {
	// TODO Create tests for the local decision logic.
    SignalMapper signalMapper;
    SafetyLogicImpl safetyLogic;

    private final Side occupiedSide;
    private final Direction firstDirection;
    private final Direction secondDirection;

    public SafetyLogicLocalTest(final Side occupiedSide, final Direction firstDir, final Direction secondDir) {
        this.occupiedSide = occupiedSide;
        this.firstDirection = firstDir;
        this.secondDirection = secondDir;
    }

    @Before
    public void createSafetyLogicImpl() {
        final SideTriple<SectionOccupancy> sectionOccupancies = SideTriple.of(SectionOccupancy.FREE,
                SectionOccupancy.FREE, SectionOccupancy.FREE);
        final Direction turnoutDirection = Direction.STRAIGHT;
        final SideTriple<NeighborTsmInfo> neighborStatuses = SideTriple.of(
                NeighborTsmInfo.some(NeighborTsmStatus.ALLOWED), NeighborTsmInfo.some(NeighborTsmStatus.ALLOWED),
                NeighborTsmInfo.some(NeighborTsmStatus.ALLOWED));
        safetyLogic = new SafetyLogicImpl(sectionOccupancies, turnoutDirection, neighborStatuses,
                LoggerWrapper.getLogger("testlogger"));
        signalMapper = mock(SignalMapper.class);
        safetyLogic.setSignalMapper(signalMapper);

    }

    /**
     * Test for events that do not trigger sent signals.
     */
    @Test
    public void testNoStatusChangeSwitching() {
        safetyLogic.sectionOccupancyChanged(Side.FACING, SectionOccupancy.FREE);
        safetyLogic.sectionOccupancyChanged(Side.STRAIGHT, SectionOccupancy.FREE);
        safetyLogic.sectionOccupancyChanged(Side.DIVERGENT, SectionOccupancy.FREE);

        verify(signalMapper, times(0)).sendControl(any());
    }

    /**
     * Test for events that do not disable any sections. (REQ-TSM-03-01-01-05)
     */
    @Test
    public void testNoDisabledSectionSwitching() {
        safetyLogic.sectionOccupancyChanged(Side.FACING, SectionOccupancy.OCCUPIED);
        safetyLogic.turnoutDirectionChanged(Direction.DIVERGENT);
        safetyLogic.sectionOccupancyChanged(Side.FACING, SectionOccupancy.FREE);
        safetyLogic.sectionOccupancyChanged(Side.DIVERGENT, SectionOccupancy.OCCUPIED);
        safetyLogic.sectionOccupancyChanged(Side.DIVERGENT, SectionOccupancy.FREE);
        safetyLogic.turnoutDirectionChanged(Direction.STRAIGHT);
        safetyLogic.sectionOccupancyChanged(Side.STRAIGHT, SectionOccupancy.OCCUPIED);
        final InOrder inOrderSignalMapper = inOrder(signalMapper);
        inOrderSignalMapper.verify(signalMapper, times(7)).sendControl(
                eq(SideTriple.of(SectionControl.ENABLED, SectionControl.ENABLED, SectionControl.ENABLED)));

        verify(signalMapper, times(7)).sendControl(any());
    }

    /**
     * Test for switching the turnout that had linked occupied sections before.
     */
    @Test
    public void testTurnoutSwitching() {

        safetyLogic.sectionOccupancyChanged(Side.FACING, SectionOccupancy.OCCUPIED);
        safetyLogic.sectionOccupancyChanged(Side.STRAIGHT, SectionOccupancy.OCCUPIED);
        safetyLogic.turnoutDirectionChanged(Direction.DIVERGENT);
        safetyLogic.turnoutDirectionChanged(Direction.STRAIGHT);
        safetyLogic.sectionOccupancyChanged(Side.FACING, SectionOccupancy.FREE);

        final InOrder inOrderSignalMapper = inOrder(signalMapper);
        inOrderSignalMapper.verify(signalMapper).sendControl(
                eq(SideTriple.of(SectionControl.ENABLED, SectionControl.ENABLED, SectionControl.ENABLED)));
        inOrderSignalMapper.verify(signalMapper).sendControl(
                eq(SideTriple.of(SectionControl.DISABLED, SectionControl.DISABLED, SectionControl.ENABLED)));
        inOrderSignalMapper.verify(signalMapper).sendControl(
                eq(SideTriple.of(SectionControl.ENABLED, SectionControl.DISABLED, SectionControl.ENABLED)));
        inOrderSignalMapper.verify(signalMapper).sendControl(
                eq(SideTriple.of(SectionControl.DISABLED, SectionControl.DISABLED, SectionControl.ENABLED)));

        // Facing section is free.
        inOrderSignalMapper.verify(signalMapper).sendControl(
                eq(SideTriple.of(SectionControl.ENABLED, SectionControl.ENABLED, SectionControl.ENABLED)));

        verify(signalMapper, times(5)).sendControl(any());
    }

    @Parameters
    public static Collection testParams() {
        return Arrays.asList(new Object[][] { { Side.STRAIGHT, Direction.STRAIGHT, Direction.DIVERGENT },
                { Side.DIVERGENT, Direction.DIVERGENT, Direction.STRAIGHT } });
    }

    /**
     * Parametric test for REQ-TSM-03-01-01-03 and REQ-TSM-03-01-01-04.
     */
    @Test
    public void testOccupiedSectionsLinkage() {
        safetyLogic.sectionOccupancyChanged(Side.FACING, SectionOccupancy.OCCUPIED);
        safetyLogic.turnoutDirectionChanged(firstDirection);

        safetyLogic.sectionOccupancyChanged(occupiedSide, SectionOccupancy.OCCUPIED);

        safetyLogic.turnoutDirectionChanged(secondDirection);

        final InOrder inOrderSignalMapper = inOrder(signalMapper);
        final SideTriple<SectionControl> initialSectionControls = SideTriple.of(SectionControl.ENABLED,
                SectionControl.ENABLED, SectionControl.ENABLED);
        inOrderSignalMapper.verify(signalMapper, atLeastOnce()).sendControl(eq(initialSectionControls));
        inOrderSignalMapper.verify(signalMapper).sendControl(
                eq(SideTriple.of(SectionControl.DISABLED, SectionControl.ENABLED, SectionControl.ENABLED).with(
                        occupiedSide, SectionControl.DISABLED)));
        inOrderSignalMapper.verify(signalMapper).sendControl(
                eq(initialSectionControls.with(occupiedSide, SectionControl.DISABLED)));

        verify(signalMapper, atLeast(3)).sendControl(any());
    }

    /**
     * Test for REQ-TSM-03-01-01-01 and REQ-TSM-03-01-01-02.
     */
    @Test
    public void testSwitchTrailing() {
        safetyLogic.sectionOccupancyChanged(Side.STRAIGHT, SectionOccupancy.OCCUPIED);
        safetyLogic.turnoutDirectionChanged(Direction.DIVERGENT);
        safetyLogic.sectionOccupancyChanged(Side.DIVERGENT, SectionOccupancy.OCCUPIED);
        safetyLogic.turnoutDirectionChanged(Direction.STRAIGHT);

        final InOrder inOrderSignalMapper = inOrder(signalMapper);
        inOrderSignalMapper.verify(signalMapper).sendControl(
                eq(SideTriple.of(SectionControl.ENABLED, SectionControl.ENABLED, SectionControl.ENABLED)));
        inOrderSignalMapper.verify(signalMapper, times(2)).sendControl(
                eq(SideTriple.of(SectionControl.ENABLED, SectionControl.DISABLED, SectionControl.ENABLED)));
        inOrderSignalMapper.verify(signalMapper).sendControl(
                eq(SideTriple.of(SectionControl.ENABLED, SectionControl.ENABLED, SectionControl.DISABLED)));

        verify(signalMapper, times(4)).sendControl(any());
    }
}
