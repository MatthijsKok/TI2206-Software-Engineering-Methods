package util.logging;

import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by dana on 27/09/2016.
 * This tests test the Logger class.
 */
public class LoggerTest {

    private static final String LOG_MESSAGE = "hello hello, check out this BEAUTIFUL logger!";

    private static Logger logger;

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
    public void fatalTest() {
    }

    @Test
    public void errorTest() {
    }

    @Test
    public void warnTest() {
    }

    @Test
    public void infoTest() {
        logger.setLevel(LogLevel.INFO);
        logger.info(LOG_MESSAGE);
        assertEquals(logger.getLogRecords().size(), 1);
    }

}
