package level;

import main.BubbleTroubleApplicationTest;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Test class to test Level.
 */
public class LevelTest extends BubbleTroubleApplicationTest {

    private Level level2;

    @Before
    public void setUp(){
        level2 = new Level("levelForLoader.json");
    }

    @Test
    public void testGetTimeLeft(){
        try {
            level2.load();
        } catch (IOException e) {
            fail();
        }

        LevelTimer timer = level2.getTimer();
        level2.update(20);
        assertThat(timer.getTimeLeft(), is(timer.getDuration() - 20));
    }
}
