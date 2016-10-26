package entities.powerups;

import bubbletrouble.BubbleTroubleApplicationTest;
import com.sun.javafx.geom.Vec2d;
import entities.character.Character;
import game.player.Player;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Tests for Harpoon class.
 */
public class ExtraVineTest extends BubbleTroubleApplicationTest {

    private ExtraVine extraVine;
    private Character character;

    @Before
    public void setUp() {
        extraVine = new ExtraVine();
        character = new Character(new Vec2d(100, 300));
        Player player = new Player(1, "leftKey", "rightKey", "shootKey");
        extraVine.setTarget(character);
    }

    @Test
    public void testConstructorSetsSprite() {
        assertNotNull("A harpoon should have a sprite", extraVine.getSprite());
    }

    @Test
    public void testEnableEffect() {
        int maxHarpoonCountBeforePickup = character.getMaxVineCount();
        extraVine.enableEffect();
        assertEquals(maxHarpoonCountBeforePickup + 1, character.getMaxVineCount());
    }

    @Test
    public void testDisableEffect() {
        int maxHarpoonCountBeforePickup = character.getMaxVineCount();
        extraVine.disableEffect();
        assertEquals(maxHarpoonCountBeforePickup - 1, character.getMaxVineCount());
    }
}
