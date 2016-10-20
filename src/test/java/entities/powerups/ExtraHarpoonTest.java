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
 * Tests for Harpoon class.
 */
public class ExtraHarpoonTest extends BubbleTroubleApplicationTest {

    private ExtraHarpoon extraHarpoon;
    private Character character;
    private Player player;
    private Vec2d spawnPosition = new Vec2d(100, 300);

    @Before
    public void setUp() {
        extraHarpoon = new ExtraHarpoon();
        character = new Character(spawnPosition);
        player = new Player(1, "leftKey", "rightKey", "shootKey");
        character.setPlayer(player);
        extraHarpoon.setTarget(character);
    }

    @Test
    public void testConstructorSetsSprite() {
        assertNotNull("A floor should have a sprite", extraHarpoon.getSprite());
    }

    @Test
    public void testEnableEffect() {
        int maxHarpoonCountBeforePickup = character.getMaxHarpoonCount();
        extraHarpoon.enableEffect();
        assertEquals(maxHarpoonCountBeforePickup + 1, character.getMaxHarpoonCount());
    }

    @Test
    public void testDisableEffect() {
        int maxHarpoonCountBeforePickup = character.getMaxHarpoonCount();
        extraHarpoon.disableEffect();
        assertEquals(maxHarpoonCountBeforePickup - 1, character.getMaxHarpoonCount());
    }

}
