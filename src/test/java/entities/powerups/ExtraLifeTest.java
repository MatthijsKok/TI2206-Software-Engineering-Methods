package entities.powerups;

import bubbletrouble.BubbleTroubleApplicationTest;
import com.sun.javafx.geom.Vec2d;
import entities.Character;
import game.player.Player;
import org.junit.Before;
import org.junit.Test;
import util.Pair;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Test class for the Extra Life Powerup.
 */
public class ExtraLifeTest extends BubbleTroubleApplicationTest {

    private ExtraLife extraLife;
    private Character character;
    private Player player;
    private Vec2d spawnPosition = new Vec2d(100, 300);

    @Before
    public void setUp() {
        extraLife = new ExtraLife();
        character = new Character(spawnPosition);
        player = new Player(1, "leftKey", "rightKey", "shootKey");
        character.setPlayer(player);
        extraLife.setTarget(character);
    }

    @Test
    public void testConstructorSetsSprite() {
        assertNotNull("A extraLife should have a sprite", extraLife.getSprite());
    }

    @Test
    public void testApplyEffect() {
        player.update(character, new Pair<>("die", true));
        int livesBeforePickup = character.getPlayer().getLives();
        extraLife.applyEffect();
        assertEquals(livesBeforePickup+1, character.getPlayer().getLives());
    }
}
