package util.logging;

import java.io.*;
import java.util.ArrayList;

/**
 * The Logger class is the entry point for the logging framework and handles its logic.
 * Constructor is not needed, compiler provides an empty one by default.
 * All classes that want to log should have the following line in their class:
 * <code>private static final Logger logger = new Logger();</code>
 */
public class Logger {

    private final static File LOG_FILE = new File(System.getProperty("user.home"), "/desktop/log.txt");
    private static ArrayList<LogRecord> logRecords = new ArrayList<>();
    private static LogLevel logLevel;


    /**
     * Sets the LogLevel of all the Loggers, since logLevel is static.
     * @param logLevel The LogLevel of all Loggers.
     */
    public void setLevel(LogLevel logLevel) {
        Logger.logLevel = logLevel;
    }

    /**
     * Entry point for the logging framework.
     * This method should be called by classes that want something logged.
     * Checks if the message exists and if its LogLevel is important enough to be logged.
     * If so it adds a new LogRecord to the ArrayList.
     * @param logLevel The LogLevel this LogRecord is logged at.
     * @param message The String message to be logged.
     */
    public void log(LogLevel logLevel, String message) {
        if (message != null && logLevel.getValue() >= Logger.logLevel.getValue()) {
            logRecords.add(new LogRecord(logLevel, getCallerClassName(), getCallerMethodName(), message, System.currentTimeMillis()));
        }
    }

    /**
     * Logs a FATAL message.
     * @param message The String message to be logged.
     */
    public void fatal(String message) {
        log(LogLevel.FATAL, message);
    }

    /**
     * Logs an ERROR message.
     * @param message The String message to be logged.
     */
    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    /**
     * Logs a WARN message.
     * @param message The String message to be logged.
     */
    public void warn(String message) {
        log(LogLevel.WARN, message);
    }

    /**
     * Logs an INFO message.
     * @param message The String message to be logged.
     */
    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    /**
     * Logs a DEBUG message.
     * @param message The String message to be logged.
     */
    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    /**
     * Logs a TRACE message.
     * @param message The String message to be logged.
     */
    public void trace(String message) {
        log(LogLevel.TRACE, message);
    }

    /**
     * Gets the class name of the caller of the Logger.
     * @return The class name of the caller of the Logger.
     */
    private String getCallerClassName() {
        return Thread.currentThread().getStackTrace()[3].getClassName();
    }

    /**
     * Gets the method name of the caller of the Logger.
     * @return The method name of the caller of the Logger.
     */
    private String getCallerMethodName() {
        return Thread.currentThread().getStackTrace()[3].getMethodName();
    }

    /**
     * Write all LogRecords in the ArrayList to the log file,
     * and then purges the ArrayList.
     */
    public void writeLogRecords() {
        try {
            Writer writer = new BufferedWriter(new FileWriter(LOG_FILE, true));
            for (LogRecord logRecord : logRecords) {
                writer.write(logRecord.format());
            }
            writer.close();
            purgeLogRecords();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Empties the ArrayList which holds the LogRecords.
     */
    private void purgeLogRecords() {
        logRecords = new ArrayList<>();
    }
}
