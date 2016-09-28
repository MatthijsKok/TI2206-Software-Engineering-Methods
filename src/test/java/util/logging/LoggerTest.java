package util.logging;

import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by dana on 27/09/2016.
 * This tests test the Logger class.
 */
public class LoggerTest {

    private static final String LOG_MESSAGE = "hello hello, check out this BEAUTIFUL logger!";
    private File logFile;
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
        logger.setLevel(LogLevel.FATAL);
        logger.fatal(LOG_MESSAGE);
        assertEquals(logger.getLogRecords().size(),1);
    }

    @Test
    public void errorTest() {
        logger.setLevel(LogLevel.ERROR);
        logger.error(LOG_MESSAGE);
        logger.error(LOG_MESSAGE);
        assertEquals(logger.getLogRecords().size(),2);
    }

    @Test
    public void warnTest() {
        logger.setLevel(LogLevel.WARN);
        assertEquals(logger.getLogRecords().size(),0);
        logger.warn(LOG_MESSAGE);
        assertEquals(logger.getLogRecords().size(),1);
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
        assertTrue(logger.getLogRecords().size()==1);
    }

    @Test
    public void logWriterTest() {
//        logger.setFile(logFile);
//        logger.setLevel(LogLevel.INFO);
//        logger.info(LOG_MESSAGE);
//
//        logger.writeLogRecords();
//        assertEquals();
    }

}
