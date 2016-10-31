package entities.powerups;

import main.BubbleTroubleApplicationTest;
import com.sun.javafx.geom.Vec2d;
import entities.character.Character;
import game.Game;
import level.Level;
import level.LevelTimer;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

/**
 * Test for ExtraTime power-up.
 */
public class ExtraTimeTest extends BubbleTroubleApplicationTest {

    private ExtraTime extraTime;
    private Vec2d spawnPosition = new Vec2d(100, 300);
    private Level level;
    private LevelTimer timer;

    @Before
    public void setUp() {
        extraTime = new ExtraTime();
        Character character = new Character(spawnPosition);
        extraTime.setTarget(character);
        level = Game.getCurrentLevel();
        timer = level.getTimer();
    }

    @Test
    public void testConstructorSetsSprite() {
        assertNotNull("A extraTime should have a sprite", extraTime.getSprite());
    }

    @Test
    public void testApplyEffect() {
        level.update(10);
        double timeBeforePickup = timer.getTimeLeft();
        extraTime.applyEffect();
        assertThat(timer.getTimeLeft(), greaterThan(timeBeforePickup));
    }
}
