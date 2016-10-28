package game.player;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Test suite for the PlayerFactory class.
 */
public class PlayerFactoryTest {

    @Test
    public void testPlayer1Factory() {
        Player player = PlayerFactory.createPlayer(0);
        assertNotNull(player);
    }
    @Test

    public void testPlayer2Factory() {
        Player player = PlayerFactory.createPlayer(1);
        assertNotNull(player);
    }
    @Test
    public void testPlayerFactory2() {
        Player player = PlayerFactory.createPlayer(2);
        assertNotNull(player);
    }
}
