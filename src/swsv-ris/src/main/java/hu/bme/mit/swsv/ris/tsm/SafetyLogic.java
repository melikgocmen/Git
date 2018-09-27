package hu.bme.mit.swsv.ris.tsm;

import java.util.Date;

import hu.bme.mit.swsv.ris.common.Direction;
import hu.bme.mit.swsv.ris.common.NeighborTsmStatus;
import hu.bme.mit.swsv.ris.common.SectionOccupancy;
import hu.bme.mit.swsv.ris.common.Side;

/**
 * Interface for safety logic, which keeps track of the status of its associated
 * sections, turnouts and neighbors, controls its sections and informs its
 * neighbors about its status.
 */
public interface SafetyLogic {

	/**
	 * Notifies the logic that the status of one of its associated sections
	 * changed.
	 *
	 * @param side Side of the associated section
	 * @param occupancy New occupancy of the associated section
	 */
	void sectionOccupancyChanged(Side side, SectionOccupancy occupancy);

	/**
	 * Notifies the logic that the direction of its associated turnout changed.
	 *
	 * @param direction New direction of the associated turnout
	 */
	void turnoutDirectionChanged(Direction direction);

	/**
	 * Notifies the logic that the status of one of its associated neighbors
	 * changed.
	 *
	 * @param side Side, where the associated neighbor is connected
	 * @param timestamp Timestamp of the new status
	 * @param status New status of the associated neighbor
	 */
	void neighborStatusChanged(Side side, Date timestamp, NeighborTsmStatus status);
}
