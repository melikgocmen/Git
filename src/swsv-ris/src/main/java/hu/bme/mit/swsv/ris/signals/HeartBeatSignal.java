package hu.bme.mit.swsv.ris.signals;

import java.util.Date;

import hu.bme.mit.swsv.ris.common.NeighborTsmStatus;
import hu.bme.mit.swsv.ris.common.Side;

/**
 * Represents a heartbeat signal that is sent from one TSM to another, reporting
 * its status along with a timestamp.
 */
public final class HeartBeatSignal extends AbstractTargetedSignal {
	private static final long serialVersionUID = 1L;

	private final NeighborTsmStatus status;
	private final Side senderSide;
	private final Date timestamp;

	/**
	 * Initializes the signal with the id of the sender and target, the status
	 * of the sender and the side of the sender that is connected to the target.
	 * The timestamp is set to the current local date and time.
	 *
	 * @param senderId Id of the sender
	 * @param targetId Id of the target
	 * @param status Status od the sender
	 * @param senderSide Indicates the direction of the connected neighbor
	 */
	public HeartBeatSignal(final int senderId, final int targetId, final NeighborTsmStatus status,
			final Side senderSide) {
		super(senderId, targetId);
		this.status = status;
		this.timestamp = new Date();
		this.senderSide = senderSide;
	}

	/**
	 * Gets the status of the sender.
	 *
	 * @return Status of the sender
	 */
	public NeighborTsmStatus getStatus() {
		return status;
	}

	/**
	 * Gets the timestamp of the signal.
	 *
	 * @return Timestamp of the signal
	 */
	public Date getTimestamp() {
		return new Date(timestamp.getTime());
	}

	/**
	 * Gets the side of the sender that is connected to the target.
	 *
	 * @return Side of the sender that is connected to the target
	 */
	public Side getSenderSide() {
		return senderSide;
	}

	@Override
	public String toString() {
		return String.format("HeartBeatSignal(sender: %s (%s), target: %s, status: %s, timestamp: %s)", getSenderId(),
				getSenderSide(), getTargetId(), getStatus(), getTimestamp());
	}
}
