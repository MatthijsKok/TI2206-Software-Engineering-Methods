package util.logging;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

/**
 * LogRecord class to represent a log message and all it's attributes.
 */
class LogRecord {

    /**
     * The chosen level of logging. The levels are: fatal, error, warn, info, debug and trace.
     */
    private final LogLevel logLevel;

    /**
     * The name of the class the method is from.
     */
    private final String sourceClassName;

    /**
     * The name of the method the message is from.
     */
    private final String sourceMethodName;

    /**
     * The message of this log record.
     */
    private final String message;

    /**
     * The current local time.
     */
    private final LocalTime localTime;

    /**
     * The current local date.
     */
    private final LocalDate localDate;

    /**
     * Constructor for LogRecord. Should only be called by Logger.log().
     * @param logLevel The LogLevel for this LogRecord.
     * @param sourceClassName The name of the class that called logger.log().
     * @param sourceMethodName The name of the method that called logger.log().
     * @param message The message of this LogRecord.
     * @param milliseconds The amount of milliseconds since epoch in POSIX time.
     */
    LogRecord(LogLevel logLevel, String sourceClassName, String sourceMethodName, String message, long milliseconds) {
        this.logLevel = logLevel;
        this.sourceClassName = sourceClassName;
        this.sourceMethodName = sourceMethodName;
        this.message = message;
        this.localDate = Instant.ofEpochMilli(milliseconds).atZone(ZoneId.systemDefault()).toLocalDate();
        this.localTime = Instant.ofEpochMilli(milliseconds).atZone(ZoneId.systemDefault()).toLocalTime();
    }

    /**
     * Formats the LogRecord as a String to be written to the log file.
     * Example:
     *
     * [2016-09-21][13:17:45.980][BubbleTrouble][start]
     * DEBUG: Testing Logging....
     *
     * @return a String representing the LogRecord.
     */
    String format() {
        return "[" + localDate.toString() + "][" + localTime.toString() + "][" + sourceClassName + "][" + sourceMethodName + "]\n" + logLevel.getName() + ": " + message + "\n";
    }
}
