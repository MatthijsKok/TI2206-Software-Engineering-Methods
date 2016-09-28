package util.logging;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by dana on 27/09/2016.
 * This class tests logger
 */
public class LoggerTest {

    private static final String LOG_MESSAGE = "hallo hallo, een log berichtje hier.";

    private Logger logger;
    private ArrayList<LogRecord> logRecords = new ArrayList<>();

    @Before
    public void setUp() {
        logger = Logger.getInstance();
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
    }

}
