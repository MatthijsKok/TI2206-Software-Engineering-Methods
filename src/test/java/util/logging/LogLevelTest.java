package util.logging;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Class that tests LogLevel.
 */
public class LogLevelTest {

    @Test
    public void getNameTest(){
        assertEquals(LogLevel.OFF.getName(), "OFF");
        assertEquals(LogLevel.FATAL.getName(), "FATAL");
        assertEquals(LogLevel.ERROR.getName(), "ERROR");
        assertEquals(LogLevel.WARN.getName(), "WARN");
        assertEquals(LogLevel.INFO.getName(), "INFO");
        assertEquals(LogLevel.DEBUG.getName(), "DEBUG");
        assertEquals(LogLevel.TRACE.getName(), "TRACE");
        assertEquals(LogLevel.ALL.getName(), "ALL");
    }

    @Test
    public void getValueTest(){
        assertEquals(LogLevel.OFF.getValue(), Integer.MAX_VALUE);
        assertEquals(LogLevel.FATAL.getValue(), 1000);
        assertEquals(LogLevel.ERROR.getValue(), 900);
        assertEquals(LogLevel.WARN.getValue(), 800);
        assertEquals(LogLevel.INFO.getValue(), 700);
        assertEquals(LogLevel.DEBUG.getValue(), 600);
        assertEquals(LogLevel.TRACE.getValue(), 500);
        assertEquals(LogLevel.ALL.getValue(), Integer.MIN_VALUE);
    }
}
