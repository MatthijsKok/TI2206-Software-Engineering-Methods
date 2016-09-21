package util.logging;

/**
 * Level class to represent log's Levels.
 * Levels are static and immutable, have a name and a value.
 * The name is used in the log message.
 * The value is used to determine if this LogRecord should be logged.
 */
public final class Level {

    // Used in the LogRecord output
    private final String name;
    // Used in determining if this LogRecord should be logged.
    private final int value;

    // The only instances of Level that should exist.
    public static final Level OFF   = new Level("OFF", Integer.MAX_VALUE); // Never write logs with this Level.
    public static final Level FATAL = new Level("FATAL", 1000);
    public static final Level ERROR = new Level("ERROR", 900);
    public static final Level WARN  = new Level("WARN", 800);
    public static final Level INFO  = new Level("INFO", 700);
    public static final Level DEBUG = new Level("DEBUG", 600);
    public static final Level TRACE = new Level("TRACE", 500);
    public static final Level ALL   = new Level("ALL", Integer.MIN_VALUE); // Never write logs with this Level.

    /**
     * Constructor for the log's Level.
     * This method should always be private.
     * @param name The name is used in the log message.
     * @param value The value is used to determine if this LogRecord should be logged.
     */
    private Level(String name, int value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Returns the name of this Level.
     * @return The name of this Level.
     */
    String getName() {
        return name;
    }

    /**
     * Returns the value of this Level.
     * @return The value of this Level.
     */
    int getValue() {
        return value;
    }

}
