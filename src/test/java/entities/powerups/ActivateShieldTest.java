package entities.powerups;

import bubbletrouble.BubbleTroubleApplicationTest;
import com.sun.javafx.geom.Vec2d;
import entities.Character;
import entities.Shield;
import game.Game;
import game.player.Player;
import level.Level;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test class for shield test.
 */
public class ActivateShieldTest extends BubbleTroubleApplicationTest {


    private ActivateShield activateShield;
    private Level level;

    @Before
    public void setUp() {
        activateShield = new ActivateShield();
        Character character = new Character(new Vec2d(100, 300));
        Player player = new Player(1, "leftKey", "rightKey", "shootKey");
        character.setPlayer(player);
        activateShield.setTarget(character);
        level = Game.getInstance().getState().getCurrentLevel();
        level.addEntity(character);
    }

    @Test
    public void testConstructorSetsSprite() {
        assertNotNull("A floor should have a sprite", activateShield.getSprite());
    }

    @Test
    public void testApplyEffect() {
        Shield shield = (Shield) level.getEntities().stream()
                .filter(entity -> entity instanceof Shield)
                .toArray()[0];

        activateShield.applyEffect();

        assertThat(shield.isVisible(), is(true));
    }
}
