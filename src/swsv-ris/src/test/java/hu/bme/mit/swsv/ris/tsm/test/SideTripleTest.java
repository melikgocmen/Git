package hu.bme.mit.swsv.ris.tsm.test;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import hu.bme.mit.swsv.ris.common.SideTriple;

@RunWith(Parameterized.class)
public class SideTripleTest {

	@Parameter(value = 0)
	public SideTriple<Integer> t0;

	@Parameter(value = 1)
	public SideTriple<Integer> t1;

	@Parameter(value = 2)
	public boolean expected;

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {

				{ SideTriple.of(1, 2, 3), SideTriple.of(1, 2, 3), true },

				{ SideTriple.of(1, 2, 3), SideTriple.of(1, 2, 4), false },

				{ SideTriple.of(1, 2, 3), SideTriple.of(1, 3, 3), false },

				{ SideTriple.of(1, 2, 3), SideTriple.of(2, 2, 3), false },

				{ SideTriple.of(1, 2, 3), SideTriple.of(2, 3, 4), false },

		});
	}

	@Test
	public void test() {
		Assert.assertEquals(expected, t0.equals(t1));
		Assert.assertEquals(expected, t1.equals(t0));
	}
}
