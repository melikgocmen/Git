package hu.bme.mit.swsv.ris.common.logging;

import org.slf4j.event.Level;

/**
 * Defines the possible log entries. A log entry consists of a code, a level and
 * a description. The description can have placeholders for additional message.
 */
public enum LogEntry {

	// @formatter:off
	METHOD_ENTER(701, Level.TRACE, "Entering method: {}."),

	STARTED(300, Level.INFO, "Component started."),
	STOPPED(301, Level.INFO, "Component stopped."),
	IT_STEP(302, Level.INFO, "Integration test scenario step: {}."),
	IT_MSG_PUB(303, Level.INFO, "Message published: {}."),
	IT_MSG_REC(304, Level.INFO, "Message received: {}."),

	WRONG_SIGNAL(400, Level.WARN, "Wrong signal: {}."),
	CANNOT_PUBLISH(401, Level.WARN, "Could not publish message, details: {}."),
	CANNOT_SERIALIZE(402, Level.WARN, "Could not serialize message, details: {}."),
	UNKNOWN_SIGNAL(403, Level.WARN, "Unknown signal: {}."),
	UNKNOWN_MESSAGE(404, Level.WARN, "A message arrived that is not a signal: {}."),

	CANNOT_CONNECT(500, Level.ERROR, "Cannot connect to the server."),
	CONNECTION_LOST(501, Level.ERROR, "Connection to the server lost."),
	DISCONNECT_ERROR(502, Level.ERROR, "Error while disconnecting component, details: {}."),
	JSON_PARSE_ERROR(503, Level.ERROR, "Error while parsing JSON file, details: {}.");
	// @formatter:on

	private final int code;
	private final Level level;
	private final String description;

	private LogEntry(final int code, final Level level, final String description) {
		this.code = code;
		this.level = level;
		this.description = description;
	}

	/**
	 * Gets the code.
	 *
	 * @return Code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Gets the level.
	 *
	 * @return Level
	 */
	public Level getLevel() {
		return level;
	}

	/**
	 * Gets the description.
	 *
	 * @return Description
	 */
	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return code + " - " + description;
	}
}
