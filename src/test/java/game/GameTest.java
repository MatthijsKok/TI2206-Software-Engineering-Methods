package game;

import bubbletrouble.BubbleTroubleApplicationTest;
import game.player.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test suite for the game class.
 */
public class GameTest extends BubbleTroubleApplicationTest {

    private Game game;

    @Before
    public void setUp() {
        game = Game.getInstance();
    }

    @Test
    public void testGetInstance() {
        assertThat(Game.getInstance(), is(game));
    }

    @Test
    public void testSetPlayerCount() {
        final int count = 2;
        game.setPlayerCount(count);
        assertThat(game.getPlayerCount(), is(count));
    }

    @Test
    public void testGetPlayer() {
        game.setPlayerCount(1);
        assertThat(game.getPlayer(0), instanceOf(Player.class));
    }

    @Test
    public void testSetLevelsFromFiles() {
        final String path = "levelsForTesting";
        List<String> levelFiles = new ArrayList<>();
        levelFiles.add(path);

        game.setLevelsFromFiles(levelFiles);

        assertThat(game.getLevels().get(0).getFilename(), is(path));
    }

    @Test
    public void testGetState() {
        assertNotNull(game.getState());
    }

//    @Test
//    public void testStart() {
//        try {
//            game.start();
//        } catch (IOException e) {
//            fail();
//        }
//
//        assertTrue(game.getState().isInProgress());
//        game.stop();
//    }

}
