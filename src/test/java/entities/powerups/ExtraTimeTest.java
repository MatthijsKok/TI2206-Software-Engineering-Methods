package entities.powerups;

import bubbletrouble.BubbleTroubleApplicationTest;
import com.sun.javafx.geom.Vec2d;
import entities.Character;
import game.Game;
import game.player.Player;
import level.Level;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

/**
 * Test for ExtraTime powerup.
 */
public class ExtraTimeTest extends BubbleTroubleApplicationTest {

    private ExtraTime extraTime;
    private Vec2d spawnPosition = new Vec2d(100, 300);
    private Level level;

    @Before
    public void setUp() {
        extraTime = new ExtraTime();
        Character character = new Character(spawnPosition);
        Player player = new Player(1, "leftKey", "rightKey", "shootKey");
        character.setPlayer(player);
        extraTime.setTarget(character);
        level = Game.getInstance().getState().getCurrentLevel();
    }

    @Test
    public void testConstructorSetsSprite() {
        assertNotNull("A extraTime should have a sprite", extraTime.getSprite());
    }

    @Test
    public void testApplyEffect() {
        level.update(10);
        double timeBeforePickup = level.getTimeLeft();
        extraTime.applyEffect();
        assertThat(level.getTimeLeft(), greaterThan(timeBeforePickup));
    }
}
