package util.logging;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * Class that tests Logger.
 */
public class LoggerTest {

    private static final String LOG_MESSAGE = "hello hello, check out this BEAUTIFUL logger!";
    private static Logger logger;
    private File logFile = new File("docs/logs/LoggerTestLog.log");

    @BeforeClass
    public static void setUpClass() {
        logger = Logger.getInstance();
    }

    @Before
    public void setUp() {
        logger.purgeLogRecords();
    }

    @Test
    public void getInstanceTest() {
        assertEquals(logger, Logger.getInstance());
    }

    @Test
    public void setLevelTest() {
        LogLevel logLevel = LogLevel.DEBUG;
        logger.setLevel(logLevel);
        assertEquals(Logger.getInstance().getLevel(), logLevel);
    }

    @Test
    public void getFileTest() {
        logger.setFile(logFile);
        assertEquals(logger.getFile(), logFile);
    }

    @Test
    public void fatalTest() {
        logger.setLevel(LogLevel.FATAL);
        logger.fatal(LOG_MESSAGE);
        assertEquals(logger.getLogRecords().size(), 1);
    }

    @Test
    public void errorTest() {
        logger.setLevel(LogLevel.ERROR);
        logger.error(LOG_MESSAGE);
        logger.error(LOG_MESSAGE);
        assertEquals(logger.getLogRecords().size(), 2);
    }

    @Test
    public void warnTest() {
        logger.setLevel(LogLevel.WARN);
        assertEquals(logger.getLogRecords().size(), 0);
        logger.warn(LOG_MESSAGE);
        assertEquals(logger.getLogRecords().size(), 1);
    }

    @Test
    public void infoTest() {
        logger.setLevel(LogLevel.INFO);
        logger.info(LOG_MESSAGE);
        assertEquals(logger.getLogRecords().size(), 1);
    }

    @Test
    public void debugTest() {
        logger.setLevel(LogLevel.DEBUG);
        logger.debug(LOG_MESSAGE);
        assertEquals(logger.getLogRecords().size(), 1);
    }

    @Test
    public void traceTest() {
        logger.setLevel(LogLevel.TRACE);
        logger.trace(LOG_MESSAGE);
        assertEquals(logger.getLogRecords().size(), 1);
    }

    @Test
    public void logWriterTest() {
        logger.setFile(logFile);
        logger.setLevel(LogLevel.INFO);
        logger.info(LOG_MESSAGE);

        try {
            logger.writeLogRecords();
        } catch (IOException e) {
            fail(e.getMessage());
        }

        assertEquals(logger.getLogRecords().size(), 0);
    }

    @Test(expected = IOException.class)
    public void logWriterExceptionTest() throws IOException {
        logger.setFile(new File("does_not_exist/fake.log"));
        logger.writeLogRecords();
    }
}
