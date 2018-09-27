package hu.bme.mit.swsv.ris.common.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Wrapper class for the logger, providing higher-level logging functionality.
 */
public class LoggerWrapper {

	private final Logger logger;

	private LoggerWrapper(final String name) {
		logger = LoggerFactory.getLogger(name);
	}

	/**
	 * Returns a logger named according to the name parameter.
	 *
	 * @param name Name of the logger
	 * @return Logger
	 */
	public static LoggerWrapper getLogger(final String name) {
		return new LoggerWrapper(name);
	}

	/**
	 * Returns a logger named according to the name parameter and the parent.
	 *
	 * @param name Name of the logger
	 * @param parent Parent of the logger
	 * @return Logger
	 */
	public static LoggerWrapper getLogger(final String name, final LoggerWrapper parent) {
		return getLogger(parent.logger.getName() + "." + name);
	}

	/**
	 * Logs an entry with an additional message.
	 *
	 * @param entry Entry to be logged
	 * @param message Additional message
	 */
	public void log(final LogEntry entry, final Object message) {
		switch (entry.getLevel()) {
		case TRACE:
			logger.trace(entry.toString(), message.toString());
			break;
		case DEBUG:
			logger.debug(entry.toString(), message.toString());
			break;
		case INFO:
			logger.info(entry.toString(), message.toString());
			break;
		case WARN:
			logger.warn(entry.toString(), message.toString());
			break;
		case ERROR:
			logger.error(entry.toString(), message.toString());
			break;
		default:
			throw new UnsupportedOperationException("Unknown logging level: " + entry.getLevel());
		}
	}

	/**
	 * Logs an entry.
	 *
	 * @param entry Entry to be logged
	 */
	public void log(final LogEntry entry) {
		log(entry, "");
	}
}
