package hu.bme.mit.swsv.ris.tsm;

import hu.bme.mit.swsv.ris.common.NeighborTsmStatus;
import hu.bme.mit.swsv.ris.common.SectionControl;
import hu.bme.mit.swsv.ris.common.SideTriple;
import hu.bme.mit.swsv.ris.signals.HeartBeatSignal;
import hu.bme.mit.swsv.ris.signals.SectionOccupancySignal;
import hu.bme.mit.swsv.ris.signals.TurnoutDirectionSignal;

/**
 * Interface for signal mapper, which is responsible for translating ids to
 * sides and sides to ids when receiving and sending signals.
 */
public interface SignalMapper {

	/**
	 * Receives an occupancy signal from a section.
	 *
	 * @param signal Signal
	 */
	void receiveSectionOccupancy(SectionOccupancySignal signal);

	/**
	 * Receives a direction signal from a turnout.
	 *
	 * @param signal Signal
	 */
	void receiveTurnoutDirection(TurnoutDirectionSignal signal);

	/**
	 * Receives a heartbeat signal from a neighbor.
	 *
	 * @param signal Signal
	 */
	void receiveHeartBeat(HeartBeatSignal signal);

	/**
	 * Sends control signals to associated sections.
	 *
	 * @param decision A triple containing a control for each section
	 */
	void sendControl(SideTriple<SectionControl> decision);

	/**
	 * Sends heartbeat signals to neighbors.
	 *
	 * @param statusForNeighbors A triple containing a status for each neighbor
	 */
	void sendStatus(SideTriple<NeighborTsmStatus> statusForNeighbors);
}
