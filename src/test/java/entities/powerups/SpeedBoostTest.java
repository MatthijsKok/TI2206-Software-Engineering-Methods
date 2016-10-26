package entities.powerups;

import bubbletrouble.BubbleTroubleApplicationTest;
import com.sun.javafx.geom.Vec2d;
import entities.character.Character;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

/**
 * Tests for speedBoost.
 */
public class SpeedBoostTest extends BubbleTroubleApplicationTest {

    private SpeedBoost speedBoost;
    private Character character;

    @Before
    public void setUp() {
        speedBoost = new SpeedBoost();
        character = new Character(new Vec2d(100, 300));
        speedBoost.setTarget(character);
    }

    @Test
    public void testConstructorSetsSprite() {
        assertNotNull("A speedBoost should have a sprite", speedBoost.getSprite());
    }

    @Test
    public void testEnableEffect() {
        double runSpeedBeforePickup = character.getRunSpeed();
        speedBoost.enableEffect();
        assertThat(character.getRunSpeed(), greaterThan(runSpeedBeforePickup));
    }

    @Test
    public void testDisableEffect() {
        double runSpeedBeforePickup = character.getRunSpeed();
        speedBoost.enableEffect();
        speedBoost.disableEffect();
        assertThat(character.getRunSpeed(), is(runSpeedBeforePickup));
    }
}
