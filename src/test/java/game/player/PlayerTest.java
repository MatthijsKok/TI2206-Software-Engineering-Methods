package game.player;

import bubbletrouble.BubbleTroubleApplicationTest;
import com.sun.javafx.geom.Vec2d;
import entities.Ball;
import entities.Character;
import entities.Harpoon;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import util.KeyboardInputManager;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Test suite for the Player class.
 */
public class PlayerTest extends BubbleTroubleApplicationTest {

    private Player player;
    private Character character;
    private Harpoon harpoon;

    @Before
    public void setUp() {
        harpoon = Mockito.mock(Harpoon.class);
        character = Mockito.mock(Character.class);
        player = PlayerFactory.createPlayer(0);
    }

    @Test
    public void testGetLives() {
        assertEquals(player.getLives(), Player.getLivesAtStart());
    }

    @Test
    public void testGetScore() {
        assertThat(player.getScore(), is(0));
    }

    @Test
    public void testUpdateFromRope() {
        Ball ball = Mockito.mock(Ball.class);
        when(ball.getSize()).thenReturn(2);

        player.update(harpoon, ball);

        assertThat(player.getScore(), greaterThan(0));
    }

    @Test
    public void testUpdateFromKeyboard() {
        player.setCharacter(character);
        Vec2d speed = player.getCharacter().getSpeed();

        player.update(new KeyboardInputManager(), null);

        assertThat(player.getCharacter().getSpeed(), is(speed));
    }
}
