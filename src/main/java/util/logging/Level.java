package util.logging;

/**
 * Created by Matthijs on 19-Sep-16.
 */
public class Level {

    private String name;
    private int value;

    // The spaces in the names are done to better align the logfile output.
    public static final Level OFF   = new Level("OFF", Integer.MAX_VALUE);
    public static final Level FATAL = new Level("FATAL", 1000);
    public static final Level ERROR = new Level("ERROR", 900);
    public static final Level WARN  = new Level("WARN", 800);
    public static final Level INFO  = new Level("INFO", 700);
    public static final Level DEBUG = new Level("DEBUG", 600);
    public static final Level TRACE = new Level("TRACE", 500);
    public static final Level ALL   = new Level("ALL", Integer.MIN_VALUE);

    public Level(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

}
