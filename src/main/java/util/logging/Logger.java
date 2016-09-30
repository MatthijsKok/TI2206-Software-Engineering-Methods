package util.logging;

//import java.io.*;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

/**
 * The Logger class is the entry point for the logging framework and handles its logic.
 * Constructor is not needed, compiler provides an empty one by default.
 * All classes that want to log should have the following line in their class:
 * <code>private static final Logger logger = new Logger();</code>
 */
public class Logger {

    /**
     * The file where the log information is stored.
     */
    private final static File LOG_FILE = new File(System.getProperty("user.home"), "/desktop/log.txt");

    /**
     * An arraylist of the log records.
     */
    private static ArrayList<LogRecord> logRecords = new ArrayList<>();

    /**
     * The chosen level of logging. The levels are: fatal, error, warn, info, debug and trace.
     */
    private static LogLevel logLevel;

    /**
     * This method should only be called at the initialization of the program.
     * Sets the LogLevel of all the Loggers, since logLevel is static.
     * @param logLevel The LogLevel of all Loggers.
     */
    public void setLevel(LogLevel logLevel) {
        Logger.logLevel = logLevel;
    }

    /**
     * Gets the LogLevel of all the Loggers, since logLevel is static.
     * @return The LogLevel of all Loggers.
     */
    public LogLevel getLevel() {
        return logLevel;
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
        if (message != null && LogLevel.FATAL.getValue() >= Logger.logLevel.getValue()) {
            logRecords.add(new LogRecord(LogLevel.FATAL, getCallerClassName(), getCallerMethodName(), message, System.currentTimeMillis()));
        }
    }

    /**
     * Logs an ERROR message.
     * @param message The String message to be logged.
     */
    public void error(String message) {
        if (message != null && LogLevel.ERROR.getValue() >= Logger.logLevel.getValue()) {
            logRecords.add(new LogRecord(LogLevel.ERROR, getCallerClassName(), getCallerMethodName(), message, System.currentTimeMillis()));
        }
    }

    /**
     * Logs a WARN message.
     * @param message The String message to be logged.
     */
    public void warn(String message) {
        if (message != null && LogLevel.WARN.getValue() >= Logger.logLevel.getValue()) {
            logRecords.add(new LogRecord(LogLevel.WARN, getCallerClassName(), getCallerMethodName(), message, System.currentTimeMillis()));
        }
    }

    /**
     * Logs an INFO message.
     * @param message The String message to be logged.
     */
    public void info(String message) {
        if (message != null && LogLevel.INFO.getValue() >= Logger.logLevel.getValue()) {
            logRecords.add(new LogRecord(LogLevel.INFO, getCallerClassName(), getCallerMethodName(), message, System.currentTimeMillis()));
        }
    }

    /**
     * Logs a DEBUG message.
     * @param message The String message to be logged.
     */
    public void debug(String message) {
        if (message != null && LogLevel.DEBUG.getValue() >= Logger.logLevel.getValue()) {
            logRecords.add(new LogRecord(LogLevel.DEBUG, getCallerClassName(), getCallerMethodName(), message, System.currentTimeMillis()));
        }
    }

    /**
     * Logs a TRACE message.
     * @param message The String message to be logged.
     */
    public void trace(String message) {
        if (message != null && LogLevel.TRACE.getValue() >= Logger.logLevel.getValue()) {
            logRecords.add(new LogRecord(LogLevel.TRACE, getCallerClassName(), getCallerMethodName(), message, System.currentTimeMillis()));
        }
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
