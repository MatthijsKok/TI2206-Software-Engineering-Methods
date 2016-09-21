package util.logging;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

/**
 * Created by Matthijs on 19-Sep-16.
 */
public class LogRecord {

    private Level level;
    private String sourceClassName;
    private String sourceMethodName;
    private String message;
    private LocalTime localTime;
    private LocalDate localDate;

    public LogRecord(Level level, String sourceClassName, String sourceMethodName, String message, long milliseconds) {
        this.level = level;
        this.sourceClassName = sourceClassName;
        this.sourceMethodName = sourceMethodName;
        this.message = message;
        this.localDate = Instant.ofEpochMilli(milliseconds).atZone(ZoneId.systemDefault()).toLocalDate();
        this.localTime = Instant.ofEpochMilli(milliseconds).atZone(ZoneId.systemDefault()).toLocalTime();
    }

    public String format() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append(localDate.toString());
        stringBuilder.append("][");
        stringBuilder.append(localTime.toString());
        stringBuilder.append("][");
        stringBuilder.append(sourceClassName);
        stringBuilder.append("][");
        stringBuilder.append(sourceMethodName);
        stringBuilder.append("]\n");
        stringBuilder.append(level.getName());
        stringBuilder.append(": ");
        stringBuilder.append(message);
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
