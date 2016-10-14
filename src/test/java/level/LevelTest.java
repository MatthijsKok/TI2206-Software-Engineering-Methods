package level;

import bubbletrouble.BubbleTroubleApplicationTest;
import entities.AbstractEntity;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        level2 = new Level("src/main/resources/levels/level2.json");
        noBall = new Level("src/main/resources/levelsForTesting/noBallsLevel.json");
        timeUp = new Level("src/main/resources/levelsForTesting/timeUpLevel.json");
    }

    @Test
    public void testLevelConstructor(){
        assertEquals(level1.getFilename(), "src/main/resources/levels/level1.json");
    }

    @Test
    public void testLevelLoadIsWon(){
        level1.load();
        assertFalse(level1.isWon());
    }

    @Test
    public void testLevelLoadIsLost(){
        level1.load();
        assertFalse(level1.isLost());
    }

    @Test
    public void testCountBalls(){
        noBall.load();
        noBall.update(2);
        assertTrue(noBall.isWon());
    }

    @Test
    public void testSetSize(){
        level1.load();
        level1.setSize(6,8);
        assertTrue(level1.getWidth() == 6 && level1.getHeight() == 8);
    }

    @Test
    public void testRemoveEntities(){
        level2.load();
        AbstractEntity removeThisBall = level2.getEntities().get(3);
        level2.removeEntity(removeThisBall);
        assertEquals(111, level2.getEntities().size());
    }

    @Test
    public void testRemoveEntity(){
        level2.load();
        AbstractEntity removeThisBall = level2.getEntities().get(3);
        assertTrue(level2.removeEntity(removeThisBall));
    }

    /**
     * Removes the entity from the entities arrayList and therefore causes a false when calling removeEntity.
     */
    @Test
    public void testRemoveEntityFalse(){
        level2.load();
        AbstractEntity removeThisBall = level2.getEntities().get(3);
        level2.removeEntity(removeThisBall);
        assertFalse(level2.removeEntity(removeThisBall));
    }

    @Test
    public void testLost(){
        level1.load();
        level1.lose();
        assertTrue(level1.isLost());
    }

    /**
     * The duration in timeUp is set to 5.
     */
    @Test
    public void testTimeUp(){
        timeUp.load();
        timeUp.update(6);
        assertTrue(timeUp.isLost());
    }

    @Test
    public void testRestart(){
        timeUp.load();
        timeUp.setDuration(60000);
        timeUp.restart();
        assertTrue(5 == timeUp.getDuration());
    }

    @Test
    public void testGetTimeLeft(){
        level2.load();
        level2.update(20);
        assertTrue(10 == level2.getTimeLeft());
    }
}
