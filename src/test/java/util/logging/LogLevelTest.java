package util.logging;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Class that tests LogLevel.
 */
public class LogLevelTest {

    @Test
    public void getOFFNameTest() {
        assertEquals(LogLevel.OFF.getName(), "OFF");
    }

    @Test
    public void getFATALNameTest() {
        assertEquals(LogLevel.FATAL.getName(), "FATAL");
    }

    @Test
    public void getERRORNameTest() {
        assertEquals(LogLevel.ERROR.getName(), "ERROR");
    }

    @Test
    public void getWARNNameTest() {
        assertEquals(LogLevel.WARN.getName(), "WARN");
    }

    @Test
    public void getINFONameTest() {
        assertEquals(LogLevel.INFO.getName(), "INFO");
    }

    @Test
    public void getDEBUGNameTest() {
        assertEquals(LogLevel.DEBUG.getName(), "DEBUG");
    }

    @Test
    public void getTRACENameTest() {
        assertEquals(LogLevel.TRACE.getName(), "TRACE");
    }

    @Test
    public void getALLNameTest() {
        assertEquals(LogLevel.ALL.getName(), "ALL");
    }

    @Test
    public void getOFFValueTest() {
        assertEquals(LogLevel.OFF.getValue(), Integer.MAX_VALUE);
    }

    @Test
    public void getFATALValueTest() {
        assertEquals(LogLevel.FATAL.getValue(), 1000);
    }

    @Test
    public void getERRORValueTest() {
        assertEquals(LogLevel.ERROR.getValue(), 900);
    }

    @Test
    public void getWARNValueTest() {
        assertEquals(LogLevel.WARN.getValue(), 800);
    }

    @Test
    public void getINFOValueTest() {
        assertEquals(LogLevel.INFO.getValue(), 700);
    }

    @Test
    public void getDEBUGValueTest() {
        assertEquals(LogLevel.DEBUG.getValue(), 600);
    }

    @Test
    public void getTRACEValueTest() {
        assertEquals(LogLevel.TRACE.getValue(), 500);
    }

    @Test
    public void getALLValueTest() {
        assertEquals(LogLevel.ALL.getValue(), Integer.MIN_VALUE);
    }
}
