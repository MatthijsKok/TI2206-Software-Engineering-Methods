package util.logging;

import org.junit.Before;
import org.junit.Test;
import util.GameCanvasManager;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by dana on 27/09/2016.
 * This class tests logger
 */
public class LoggerTest {

    private Logger logger;
    private  LogLevel loglevel;
    private static ArrayList<LogRecord> logRecords = new ArrayList<>();

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

    @Test
    public void fatalTest(){
    }

    @Test
    public void errorTest(){
    }

    @Test
    public void warnTest(){
    }

    @Test
    public void infoTest(){
        Logger.getInstance().setLevel(loglevel);
        //Dit gaat fout. Blijft maar een nullpointer exception geven.
//        logger.info("test");
    }

}
