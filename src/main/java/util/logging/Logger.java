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
    private static Level level;


    /**
     * Sets the Level of all the Loggers, since level is static.
     * @param level The Level of all Loggers.
     */
    public void setLevel(Level level) {
        Logger.level = level;
    }

    /**
     * Entry point for the logging framework.
     * This method should be called by classes that want something logged.
     * Checks if the message exists and if its Level is important enough to be logged.
     * If so it adds a new LogRecord to the ArrayList.
     * @param level The Level this LogRecord is logged at.
     * @param message The message to be logged.
     */
    public void log(Level level, String message) {
        if (message != null && level.getValue() >= Logger.level.getValue()) {
            logRecords.add(new LogRecord(level, getCallerClassName(), getCallerMethodName(), message, System.currentTimeMillis()));
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
