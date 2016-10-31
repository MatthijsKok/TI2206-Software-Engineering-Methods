package entities.powerups;

import main.BubbleTroubleApplicationTest;
import com.sun.javafx.geom.Vec2d;
import entities.character.Character;
import entities.character.CharacterMovement;
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
    private CharacterMovement movement;

    @Before
    public void setUp() {
        speedBoost = new SpeedBoost();
        Character character = new Character(new Vec2d(100, 300));
        movement = character.getMovement();
        speedBoost.setTarget(character);
    }

    @Test
    public void testConstructorSetsSprite() {
        assertNotNull("A speedBoost should have a sprite", speedBoost.getSprite());
    }

    @Test
    public void testEnableEffect() {
        double runSpeedBeforePickup = movement.getRunSpeed();
        speedBoost.enableEffect();
        assertThat(movement.getRunSpeed(), greaterThan(runSpeedBeforePickup));
    }

    @Test
    public void testDisableEffect() {
        double runSpeedBeforePickup = movement.getRunSpeed();
        speedBoost.enableEffect();
        speedBoost.disableEffect();
        assertThat(movement.getRunSpeed(), is(runSpeedBeforePickup));
    }
}
