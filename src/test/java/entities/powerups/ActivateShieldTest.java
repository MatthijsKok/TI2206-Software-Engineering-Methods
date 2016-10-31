package entities.powerups;

import main.BubbleTroubleApplicationTest;
import com.sun.javafx.geom.Vec2d;
import entities.character.Character;
import entities.character.Shield;
import game.Game;
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
        activateShield.setTarget(character);
        level = Game.getCurrentLevel();
        level.addEntity(character);
    }

    @Test
    public void testConstructorSetsSprite() {
        assertNotNull("A shield should have a sprite", activateShield.getSprite());
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
