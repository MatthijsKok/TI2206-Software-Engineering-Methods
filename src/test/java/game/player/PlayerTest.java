package game.player;

import bubbletrouble.BubbleTroubleApplicationTest;
import com.sun.javafx.geom.Vec2d;
import entities.character.Character;
import entities.character.CharacterMovement;
import entities.character.Gun;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import util.Pair;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

/**
 * Test suite for the Player class.
 */
public class PlayerTest extends BubbleTroubleApplicationTest {

    private Player player;
    private Character character;

    @Before
    public void setUp() {
        Gun gun = Mockito.mock(Gun.class);
        CharacterMovement movement = Mockito.mock(CharacterMovement.class);
        character = Mockito.mock(Character.class);
        when(character.getGun()).thenReturn(gun);
        when(character.getMovement()).thenReturn(movement);
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

        player.update(character, new Pair<>("increaseLives", -1));

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

        player.updateKeyboardInput();

        assertThat(player.getCharacter().getSpeed(), is(speed));
    }

    @Test
    public void testIncreaseLivesGreaterThanZero() {
        player.update(character, new Pair<>("increaseLives", -1));

        int lives = player.getLives();

        player.increaseLives(3);
        assertThat(player.getLives(), greaterThan(lives));
    }

    @Test
    public void testIncreaseLivesSmallerThanZero() {
        int lives = player.getLives();

        player.increaseLives(0);
        assertThat(player.getLives(), is(lives));
    }


    @Test
    public void testResetLives() {
        int lives = player.getLives();

        player.update(character, new Pair<>("increaseLives", -1));
        player.update(character, new Pair<>("increaseLives", -1));

        player.resetLives();

        assertThat(player.getLives(), is(lives));
    }
}
