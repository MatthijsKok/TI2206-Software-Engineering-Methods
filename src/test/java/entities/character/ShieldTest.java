package entities.character;

import main.BubbleTroubleApplicationTest;
import com.sun.javafx.geom.Vec2d;
import entities.balls.ColoredBall;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test suite for the Shield class.
 */
public class ShieldTest extends BubbleTroubleApplicationTest {

    private Character character;
    private Shield shield;

    @Before
    public void setUp() {
        character = new Character(new Vec2d(0, 0));
        shield = new Shield(character);
    }

    @Test
    public void testActivate() {
        shield.activate();
        assertThat(shield.isVisible(), is(true));
    }

    @Test
    public void testCollideWithBall() {
        shield.activate();

        ColoredBall ball = Mockito.mock(ColoredBall.class);
        shield.collideWith(ball);

        assertThat(shield.isVisible(), is(false));
    }

    @Test
    public void testCollideWithOther() {
        shield.activate();

        shield.collideWith(character);

        assertThat(shield.isVisible(), is(true));
    }

}
