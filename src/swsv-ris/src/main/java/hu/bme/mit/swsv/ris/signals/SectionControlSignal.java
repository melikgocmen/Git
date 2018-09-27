package hu.bme.mit.swsv.ris.signals;

import hu.bme.mit.swsv.ris.common.SectionControl;

/**
 * Represents a control signal sent to a section.
 */
public final class SectionControlSignal extends AbstractTargetedSignal {
	private static final long serialVersionUID = 1L;

	private final SectionControl sectionControl;

	/**
	 * Initializes the signal with the id of the sender and target, and the
	 * control of the target.
	 *
	 * @param senderId Id of the sender
	 * @param targetId Id of the target
	 * @param sectionControl Control of the target
	 */
	public SectionControlSignal(final int senderId, final int targetId, final SectionControl sectionControl) {
		super(senderId, targetId);
		this.sectionControl = sectionControl;
	}

	/**
	 * Gets the control of the target.
	 *
	 * @return Control of the target
	 */
	public SectionControl getSectionControl() {
		return sectionControl;
	}

	@Override
	public String toString() {
		return String.format("SectionControlSignal(sender: %s, target: %s, control: %s)", getSenderId(), getTargetId(),
				getSectionControl());
	}
}
