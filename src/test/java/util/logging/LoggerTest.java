package util.logging;

import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.IOException;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
        logger.writeLogRecords();
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

        logger.writeLogRecords();
        assertEquals(logger.getLogRecords().size(), 0);
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void logWriterExceptionTest(){
//        exception.expect(Exception.class);
//        exception.expectMessage("IOException! Probable cause:\n" +
//                "Two instances of Logger exist in different threads. /\n" +
//                "This was thrown during testing of Logger");

        logger.setFile(new File("doesntexist/fake.log"));
        logger.writeLogRecords();
    }
}
