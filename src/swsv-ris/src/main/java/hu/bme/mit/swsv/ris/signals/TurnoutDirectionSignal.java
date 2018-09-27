package hu.bme.mit.swsv.ris.signals;

import hu.bme.mit.swsv.ris.common.Direction;

/**
 * Represents a status signal from a turnout indicating its direction.
 */
public final class TurnoutDirectionSignal extends AbstractSignal {
	private static final long serialVersionUID = 1L;

	private final Direction turnoutDirection;

	/**
	 * Initializes the signal with the id of the sender and the direction of the
	 * sender.
	 *
	 * @param senderId Id of the sender
	 * @param turnoutDirection Direction of the sender
	 */
	public TurnoutDirectionSignal(final int senderId, final Direction turnoutDirection) {
		super(senderId);
		this.turnoutDirection = turnoutDirection;
	}

	/**
	 * Gets the direction of the sender.
	 *
	 * @return Direction of the sender
	 */
	public Direction getTurnoutDirection() {
		return turnoutDirection;
	}

	@Override
	public String toString() {
		return String.format("TurnoutDirectionSignal(sender: %s, direction: %s)", getSenderId(), getTurnoutDirection());
	}
}
