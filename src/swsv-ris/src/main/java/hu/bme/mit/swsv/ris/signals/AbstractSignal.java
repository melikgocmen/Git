package hu.bme.mit.swsv.ris.signals;

import java.io.Serializable;

/**
 * Immutable base class for signals, containing the id of the sender.
 */
public abstract class AbstractSignal implements Serializable {
	private static final long serialVersionUID = 1L;

	private final Integer senderId;

	/**
	 * Initializes a signal with sender id.
	 *
	 * @param senderId Id of the sender
	 */
	public AbstractSignal(final Integer senderId) {
		this.senderId = senderId;
	}

	/**
	 * Gets the id of the sender.
	 *
	 * @return Id of the sender
	 */
	public Integer getSenderId() {
		return senderId;
	}
}
