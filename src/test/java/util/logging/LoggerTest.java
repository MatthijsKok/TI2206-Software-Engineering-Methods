package util.logging;

import game.Game;
import game.state.NotStartedState;
import main.BubbleTroubleApplicationTest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Class that tests Logger.
 */
public class LoggerTest extends BubbleTroubleApplicationTest {

    private static final String LOG_MESSAGE = "hello hello, check out this BEAUTIFUL logger!";
    private final Logger logger = Logger.getInstance();
    private File logFile = new File("docs/logs/LoggerTestLog.log");

    @Before
    public void setUp() {
        Game.setState(new NotStartedState());
        logger.purgeLogRecords();
    }

    @Test
    public void getInstanceTest() {
        assertThat(logger, is(Logger.getInstance()));
    }

    @Test
    public void setLevelTest() {
        LogLevel logLevel = LogLevel.DEBUG;
        logger.setLevel(logLevel);
        assertThat(Logger.getInstance().getLevel(), is(logLevel));
    }

    @Test
    public void getFileTest() {
        logger.setFile(logFile);
        assertThat(logger.getFile(), is(logFile));
    }

    @Test
    public void fatalTest() {
        logger.setLevel(LogLevel.FATAL);
        logger.fatal(LOG_MESSAGE);
        assertThat(logger.getLogRecords().size(), is(1));
    }

    @Test
    public void errorTest() {
        logger.setLevel(LogLevel.ERROR);
        logger.error(LOG_MESSAGE);
        assertThat(logger.getLogRecords().size(), is(1));
    }

    @Test
    public void warnTest() {
        logger.setLevel(LogLevel.WARN);
        assertThat(logger.getLogRecords().size(), is(0));
        logger.warn(LOG_MESSAGE);
        assertThat(logger.getLogRecords().size(), is(1));
    }

    @Test
    public void infoTest() {
        logger.setLevel(LogLevel.INFO);
        logger.info(LOG_MESSAGE);
        assertThat(logger.getLogRecords().size(), is(1));
    }

    @Test
    public void debugTest() {
        logger.setLevel(LogLevel.DEBUG);
        logger.debug(LOG_MESSAGE);
        assertThat(logger.getLogRecords().size(), is(1));
    }

    @Test
    public void traceTest() {
        logger.setLevel(LogLevel.TRACE);
        logger.trace(LOG_MESSAGE);
        assertThat(logger.getLogRecords().size(), is(1));
    }

    @Test
    public void logWriterTest() {
        logger.setFile(logFile);
        logger.setLevel(LogLevel.INFO);
        logger.info(LOG_MESSAGE);

        logger.writeLogRecords();

        assertThat(logger.getLogRecords().size(), is(0));
    }
}
