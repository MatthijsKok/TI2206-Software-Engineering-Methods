package util.logging;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Class that tests LogRecord.
 */
public class LogRecordTest {

    private String sourceClassName = "TestClass";
    private String sourceMethodName = "TestMethod";
    private String logMessage = "TestMessage";

    @Test
    public void formatTest(){
        LogRecord logRecord = new LogRecord(LogLevel.INFO, sourceClassName, sourceMethodName, logMessage, 10000000);
        assertEquals(logRecord.format(),
                "[1970-01-01][03:46:40.000][TestClass][TestMethod]\n" +
                "INFO: TestMessage\n");
    }
}
