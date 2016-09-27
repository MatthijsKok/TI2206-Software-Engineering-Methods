package util.logging;

import org.junit.Before;
import org.junit.Test;
import util.GameCanvasManager;

import static org.junit.Assert.assertEquals;

/**
 * Created by dana on 27/09/2016.
 * This class tests logger
 */
public class LoggerTest {

    private Logger logger;
    private  LogLevel loglevel;

    @Before
    public void setUp() {
        logger = Logger.getInstance();
    }

    @Test
    public void getInstanceTest(){ assertEquals(logger, Logger.getInstance()); }

    @Test
    public void setLevelTest(){
        Logger.getInstance().setLevel(loglevel);
        assertEquals(Logger.getInstance().getLevel(), loglevel );
    }
}
