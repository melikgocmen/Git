package hu.bme.mit.swsv.ris.signals;

/**
 * Immutable base class for targeted signals, containing the id of the target in
 * addition to an AbstractSignal.
 */
public abstract class AbstractTargetedSignal extends AbstractSignal {
	private static final long serialVersionUID = 1L;

	private final Integer targetId;

	/**
	 * Initializes a signal with the id of the sender and target.
	 *
	 * @param senderId Id of the sender
	 * @param targetId Id of the target
	 */
	public AbstractTargetedSignal(final Integer senderId, final Integer targetId) {
		super(senderId);
		this.targetId = targetId;
	}

	/**
	 * Gets the id of the target.
	 *
	 * @return Id of the target
	 */
	public Integer getTargetId() {
		return targetId;
	}
}
