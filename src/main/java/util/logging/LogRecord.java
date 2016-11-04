package util.logging;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * LogRecord class to represent a log message and all it's attributes.
 */
class LogRecord {

    /**
     * The logLevel of this message.
     */
    private final transient LogLevel logLevel;
    /**
     * The name of the class this logRecord comes from.
     */
    private final transient String sourceClassName;
    /**
     * The name of the method this logRecord is created from.
     */
    private final transient String sourceMethodName;
    /**
     * The log message.
     */
    private final transient String message;
    /**
     * The timestamp this logRecord is created at.
     */
    private final transient long milliseconds;

    /**
     * Constructor for LogRecord. Should only be called by Logger.log().
     * @param logLevel         The LogLevel for this LogRecord.
     * @param sourceClassName  The name of the class that called logger.log().
     * @param sourceMethodName The name of the method that called logger.log().
     * @param message          The message of this LogRecord.
     * @param milliseconds     The amount of milliseconds since epoch in POSIX time.
     */
    LogRecord(
            final LogLevel logLevel, final String sourceClassName,
            final String sourceMethodName, final String message, final long milliseconds) {
        this.logLevel = logLevel;
        this.sourceClassName = sourceClassName;
        this.sourceMethodName = sourceMethodName;
        this.message = message;
        this.milliseconds = milliseconds;
    }

    /**
     * Formats the LogRecord as a String to be written to the log file.
     * Example:
     * [2016-09-21][13:17:45.980][main.BubbleTrouble][start]
     * DEBUG: Testing Logging...
     * @return a String representing the LogRecord.
     */
    /* default */ final String format() {
        return new SimpleDateFormat("[yyyy-MM-dd][HH:mm:ss.SSS][", Locale.ENGLISH).format(new Date(milliseconds))
                + sourceClassName + "][" + sourceMethodName + "]\n" + logLevel.getName() + ": " + message + "\n";
    }
}
