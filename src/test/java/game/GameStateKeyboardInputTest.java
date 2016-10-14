package game;

import bubbletrouble.BubbleTroubleApplicationTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import util.KeyboardInputManager;
import util.StageManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test suite for the KeyboardInput on GameState.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(KeyboardInputManager.class)
public class GameStateKeyboardInputTest extends BubbleTroubleApplicationTest {

    private GameState gameState;
    private KeyboardInputManager kim = new KeyboardInputManager();

    @Before
    public void setUp() {
        StageManager.getStage().getScene();
        gameState = new GameState(Game.getInstance());
        gameState.reset();
    }

    @Test
    public void testPause() {
        gameState.resume();

        PowerMockito.mockStatic(KeyboardInputManager.class);
        PowerMockito.when(KeyboardInputManager.keyPressed("P")).thenReturn(true);
        gameState.update(kim, null);

        assertThat(gameState.isInProgress(), is(false));
    }

    @Test
    public void testResume() {
        gameState.pause();

        PowerMockito.mockStatic(KeyboardInputManager.class);
        PowerMockito.when(KeyboardInputManager.keyPressed("P")).thenReturn(true);
        gameState.update(kim, null);

        assertThat(gameState.isInProgress(), is(true));
    }

    @Test
    public void testWin() {
        gameState.win();

        PowerMockito.mockStatic(KeyboardInputManager.class);
        PowerMockito.when(KeyboardInputManager.keyPressed("R")).thenReturn(true);

        assertThat(gameState.isInProgress(), is(false));
    }

    @Test
    public void testLose() {
        gameState.lose();

        PowerMockito.mockStatic(KeyboardInputManager.class);
        PowerMockito.when(KeyboardInputManager.keyPressed("R")).thenReturn(true);

        assertThat(gameState.isInProgress(), is(false));
    }

    @Test
    public void testLevelWon() {
        gameState.getCurrentLevel().win();

        PowerMockito.mockStatic(KeyboardInputManager.class);
        PowerMockito.when(KeyboardInputManager.keyPressed("R")).thenReturn(true);

        assertThat(gameState.isInProgress(), is(false));
    }

    @Test
    public void testLevelLost() {
        gameState.getCurrentLevel().lose();

        PowerMockito.mockStatic(KeyboardInputManager.class);
        PowerMockito.when(KeyboardInputManager.keyPressed("R")).thenReturn(true);

        assertThat(gameState.getCurrentLevel().getFilename(), is("src/main/resources/levels/level1.json"));
    }

}
