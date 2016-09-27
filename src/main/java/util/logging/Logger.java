package util.logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * The Logger class is the entry point for the logging framework and handles its logic.
 * All classes that want to log should have the following line in their class:
 * <code>private static final Logger logger = Logger.getInstance();;</code>
 */
public final class Logger {

    /**
     * Eagerly create the static unique instance of the Logger.
     */
    private static Logger uniqueInstance = new Logger();

    /**
     * The File in which all logging shall take place this run of the program.
     */
    private static final File LOG_FILE = new File("docs/logs/BubbleTrouble Log " + new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()) + ".log");

    /**
     * An ArrayList which holds all LogRecord's that haven't been written to file.
     */
    private static ArrayList<LogRecord> logRecords = new ArrayList<>();

    /**
     * The LogLevel for the unique instance of the Logger.
     * Set to LogLevel.INFO by default.
     */
    private static LogLevel logLevel = LogLevel.INFO;

    /**
     * The depth of the stack trace where the className and methodName is that called Logger.
     */
    private static final int STACK_DEPTH = 4;

    /**
     * Private constructor for Logger class.
     * This should only be called once, when eagerly creating the unique instance.
     */
    private Logger() {

    }

    /**
     * Returns the only instance of Logger that should exist.
     * @return An instance of Logger.
     */
    public static Logger getInstance() {
        return uniqueInstance;
    }

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
    private void log(LogLevel logLevel, String message) {
        if (message != null && logLevel.getValue() >= Logger.logLevel.getValue()) {
            logRecords.add(new LogRecord(logLevel, getCallerClassName(), getCallerMethodName(), message, System.currentTimeMillis()));
        }
    }

    /**
     * Logs a FATAL message.
     * The FATAL LogLevel designates very severe error events that will presumably lead the application to abort.
     * @param message The String message to be logged.
     */
    public void fatal(String message) {
        log(LogLevel.FATAL, message);
    }

    /**
     * Logs an ERROR message.
     * The ERROR LogLevel designates error events that might still allow the application to continue running.
     * @param message The String message to be logged.
     */
    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    /**
     * Logs a WARN message.
     * The WARN LogLevel designates potentially harmful situations.
     * @param message The String message to be logged.
     */
    public void warn(String message) {
        log(LogLevel.WARN, message);
    }

    /**
     * Logs an INFO message.
     * The INFO LogLevel designates informational messages that highlight the progress of the application at coarse-grained level.
     * @param message The String message to be logged.
     */
    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    /**
     * Logs a DEBUG message.
     * The DEBUG LogLevel designates fine-grained informational events that are most useful to debug an application.
     * @param message The String message to be logged.
     */
    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    /**
     * Logs a TRACE message.
     * The TRACE LogLevel designates finer-grained informational events than the DEBUG LogLevel.
     * Mostly used in loops.
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
        return Thread.currentThread().getStackTrace()[STACK_DEPTH].getClassName();
    }

    /**
     * Gets the method name of the caller of the Logger.
     * @return The method name of the caller of the Logger.
     */
    private String getCallerMethodName() {
        return Thread.currentThread().getStackTrace()[STACK_DEPTH].getMethodName();
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
