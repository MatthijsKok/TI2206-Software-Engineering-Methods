package level;

import bubbletrouble.BubbleTroubleApplicationTest;
import entities.AbstractEntity;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Test class to test Level.
 */
public class LevelTest extends BubbleTroubleApplicationTest {

    private Level level1;
    private Level level2;
    private Level noBall;
    private Level timeUp;

    @Before
    public void setUp(){
        level1 = new Level("src/main/resources/levels/level1.json");
        level2 = new Level("src/main/resources/levelsForTesting/levelForLoader.json");
        noBall = new Level("src/main/resources/levelsForTesting/noBallsLevel.json");
        timeUp = new Level("src/main/resources/levelsForTesting/timeUpLevel.json");
    }

    @Test
    public void testLevelConstructor(){
        assertEquals(level1.getFilename(), "src/main/resources/levels/level1.json");
    }

    @Test
    public void testLevelLoadIsWon(){
        try {
            level1.load();
        } catch (IOException e) {
            fail();
        }
        assertFalse(level1.isWon());
    }

    @Test
    public void testLevelLoadIsLost(){
        try {
            level1.load();
        } catch (IOException e) {
            fail();
        }
        assertFalse(level1.isLost());
    }

    @Test
    public void testCountBalls(){
        try {
            noBall.load();
        } catch (IOException e) {
            fail();
        }
        noBall.update(2);
        assertTrue(noBall.isWon());
    }

    @Test
    public void testSetSize(){
        try {
            level1.load();
        } catch (IOException e) {
            fail();
        }
        level1.setSize(6,8);
        assertTrue(level1.getWidth() == 6 && level1.getHeight() == 8);
    }

    @Test
    public void testRemoveEntities(){
        try {
            level2.load();
        } catch (IOException e) {
            fail();
        }
        int sizeBeforeRemoval = level2.getEntities().size();
        AbstractEntity removeThisBall = level2.getEntities().get(3);
        level2.removeEntity(removeThisBall);
        assertEquals(sizeBeforeRemoval - 1, level2.getEntities().size());
    }

    @Test
    public void testRemoveEntity(){
        try {
            level2.load();
        } catch (IOException e) {
            fail();
        }
        AbstractEntity removeThisBall = level2.getEntities().get(3);
        assertTrue(level2.removeEntity(removeThisBall));
    }

    /**
     * Removes the entity from the entities arrayList and therefore causes a false when calling removeEntity.
     */
    @Test
    public void testRemoveEntityFalse(){
        try {
            level2.load();
        } catch (IOException e) {
            fail();
        }
        AbstractEntity removeThisBall = level2.getEntities().get(3);
        level2.removeEntity(removeThisBall);
        assertFalse(level2.removeEntity(removeThisBall));
    }

    @Test
    public void testLost(){
        try {
            level1.load();
        } catch (IOException e) {
            fail();
        }
        level1.lose();
        assertTrue(level1.isLost());
    }

    /**
     * The duration in timeUp is set to 5.
     */
    @Test
    public void testTimeUp(){
        try {
            timeUp.load();
        } catch (IOException e) {
            fail();
        }
        timeUp.update(6);
        assertTrue(timeUp.isLost());
    }

    @Test
    public void testRestart(){
        try {
            timeUp.load();
            timeUp.setDuration(60000);
            timeUp.restart();
        } catch (IOException e) {
            fail();
        }
        assertTrue(5 == timeUp.getDuration());
    }

    @Test
    public void testGetTimeLeft(){
        try {
            level2.load();
        } catch (IOException e) {
            fail();
        }
        level2.update(20);
        assertThat(level2.getTimeLeft(), is(level2.getDuration() - 20));
    }
}
