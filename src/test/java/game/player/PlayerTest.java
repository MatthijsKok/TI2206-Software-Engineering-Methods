package game.player;

import bubbletrouble.BubbleTroubleApplicationTest;
import com.sun.javafx.geom.Vec2d;
import entities.character.Character;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import util.KeyboardInputManager;
import util.Pair;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;

/**
 * Test suite for the Player class.
 */
public class PlayerTest extends BubbleTroubleApplicationTest {

    private Player player;
    private Character character;

    @Before
    public void setUp() {
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
    public void testUpdateFromCharacterDie() {
        int lives = player.getLives();

        player.update(character, new Pair<>("die", true));

        assertThat(player.getLives(), lessThan(lives));
    }

    @Test
    public void testUpdateFromCharacterIncreaseScore() {
        player.update(character, new Pair<>("increaseScore", 100));

        assertThat(player.getScore(), is(100));
    }

    @Test
    public void testUpdateFromKeyboard() {
        player.setCharacter(character);
        Vec2d speed = player.getCharacter().getSpeed();

        player.update(new KeyboardInputManager(), null);

        assertThat(player.getCharacter().getSpeed(), is(speed));
    }

    @Test
    public void testIncreaseLivesGreaterThanZero() {
        player.update(character, new Pair<>("die", true));

        int lives = player.getLives();

        player.increaseLives(3);
        assertThat(player.getLives(), greaterThan(lives));
    }

    @Test
    public void testIncreaseLivesSmallerThanZero() {
        int lives = player.getLives();

        player.increaseLives(-3);
        assertThat(player.getLives(), is(lives));
    }


    @Test
    public void testResetLives() {
        int lives = player.getLives();

        player.update(character, new Pair<>("die", true));
        player.update(character, new Pair<>("die", true));

        player.resetLives();

        assertThat(player.getLives(), is(lives));
    }
}
