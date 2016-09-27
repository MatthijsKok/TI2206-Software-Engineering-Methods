package util.logging;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * LogRecord class to represent a log message and all it's attributes.
 */
class LogRecord {

    private final LogLevel logLevel;
    private final String sourceClassName;
    private final String sourceMethodName;
    private final String message;
    private final long milliseconds;

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
        this.milliseconds = milliseconds;
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
        return new SimpleDateFormat("[yyyy-MM-dd][HH:mm:ss.SSS][").format(new Date(milliseconds)) + sourceClassName + "][" + sourceMethodName + "]\n" + logLevel.getName() + ": " + message + "\n";
    }
}
