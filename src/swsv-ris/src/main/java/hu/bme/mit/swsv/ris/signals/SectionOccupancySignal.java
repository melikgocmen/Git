package hu.bme.mit.swsv.ris.signals;

import hu.bme.mit.swsv.ris.common.SectionOccupancy;

/**
 * Represents an occupancy status signal from a section.
 */
public final class SectionOccupancySignal extends AbstractSignal {
	private static final long serialVersionUID = 1L;

	private final SectionOccupancy sectionOccupancy;

	/**
	 * Initializes the signal with the id of the sender and the occupancy of the
	 * sender.
	 *
	 * @param senderId Id of the sender
	 * @param sectionOccupancy Occupancy of the sender
	 */
	public SectionOccupancySignal(final int senderId, final SectionOccupancy sectionOccupancy) {
		super(senderId);
		this.sectionOccupancy = sectionOccupancy;
	}

	/**
	 * Gets the occupancy of the sender.
	 *
	 * @return Occupancy of the sender
	 */
	public SectionOccupancy getSectionOccupancy() {
		return sectionOccupancy;
	}

	@Override
	public String toString() {
		return String.format("SectionOccupancySignal(sender: %s, occupancy: %s)", getSenderId(), getSectionOccupancy());
	}
}
