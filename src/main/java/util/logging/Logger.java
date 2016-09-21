package util.logging;

import java.io.*;
import java.util.ArrayList;

public class Logger {

    private static final Logger logger = new Logger();
    private final static File LOG_FILE = new File(System.getProperty("user.home"), "/desktop/log.txt");
    private static ArrayList<LogRecord> logRecords = new ArrayList<>();
    private static Level level;

    public Logger() {

    }

    public void setLevel(Level level) {
        this.level = level;
    }

    /**
     * Checks if the message exists and if its Level is important enough to be logged.
     * If so it adds a new LogRecord to the ArrayList.
     * @param level The Level this LogRecord is logged at.
     * @param message The message to be logged.
     */
    public void log(Level level, String message) {
        if (message != null && level.getValue() >= this.level.getValue()) {
            logRecords.add(new LogRecord(level, getCallerClassName(), getCallerMethodName(), message, System.currentTimeMillis()));
        }
    }

    /**
     * Gets the class name of the caller of the Logger.
     * @return The class name of the caller of the Logger.
     */
    public String getCallerClassName() {
        return Thread.currentThread().getStackTrace()[3].getClassName();
    }

    /**
     * Gets the method name of the caller of the Logger.
     * @return The method name of the caller of the Logger.
     */
    public String getCallerMethodName() {
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
                System.out.println(logRecord.format());
            }
            logger.log(Level.DEBUG, "Exited writing LogRecord loop....");
            writer.close();
            purgeLogRecords();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
