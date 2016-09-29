package util.logging;

/**
 * LogLevel class to represent log's Levels.
 * Levels are static and immutable, have a name and a value.
 * The name is used in the log message.
 * The value is used to determine if this LogRecord should be logged.
 */
public final class LogLevel {

    /**
     * Used in the LogRecord output.
     */
    private final String name;

    /**
     * Used in determining if this LogRecord should be logged.
     */
    private final int value;

    /**
     * The OFF LogLevel has the highest possible rank and is intended to turn off logging.
     * Never write logs with this LogLevel.
     */
    public static final LogLevel OFF   = new LogLevel("OFF", Integer.MAX_VALUE);

    /**
     * The FATAL LogLevel designates very severe error events that will presumably lead the application to abort.
     */
    public static final LogLevel FATAL = new LogLevel("FATAL", 1000);

    /**
     * The ERROR LogLevel designates error events that might still allow the application to continue running.
     */
    public static final LogLevel ERROR = new LogLevel("ERROR", 900);

    /**
     * The WARN LogLevel designates potentially harmful situations.
     */
    public static final LogLevel WARN  = new LogLevel("WARN", 800);

    /**
     * The INFO LogLevel designates informational messages that highlight the progress of the application at coarse-grained level.
     */
    public static final LogLevel INFO  = new LogLevel("INFO", 700);

    /**
     * The DEBUG LogLevel designates fine-grained informational events that are most useful to debug an application.
     */
    public static final LogLevel DEBUG = new LogLevel("DEBUG", 600);

    /**
     * The TRACE LogLevel designates finer-grained informational events than the DEBUG LogLevel.
     * Mostly used in loops.
     */
    public static final LogLevel TRACE = new LogLevel("TRACE", 500);

    /**
     * The ALL level has the lowest possible rank and is intended to turn on all logging.
     * Never write logs with this LogLevel.
     */
    public static final LogLevel ALL   = new LogLevel("ALL", Integer.MIN_VALUE);

    /**
     * Constructor for the log's LogLevel.
     * The only instances of LogLevel that should exist are declared above.
     * @param name The name is used in the log message.
     * @param value The value is used to determine if this LogRecord should be logged.
     */
    private LogLevel(String name, int value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Returns the name of this LogLevel.
     * @return The name of this LogLevel.
     */
    String getName() {
        return name;
    }

    /**
     * Returns the value of this LogLevel.
     * @return The value of this LogLevel.
     */
    int getValue() {
        return value;
    }

}
