package hu.bme.mit.swsv.ris.common;

/**
 * Defines global constants.
 */
public final class Constants {

	/**
	 * Period of the heartbeat messages in milliseconds.
	 */
	public static final long HEARTBEAT_PERIOD_MS = 1000;

	/**
	 * Number of heartbeat periods after which a component is considered faulty.
	 */
	public static final long HEARTBEAT_WAIT_NR = 4;

	private Constants() {
		// Utility class. This constructor intentionally left blank.
	}
}
