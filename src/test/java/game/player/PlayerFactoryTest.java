package game.player;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Test suite for the PlayerFactory class.
 */
public class PlayerFactoryTest {

    @Test
    public void testPlayerFactory2() {
        Player player = PlayerFactory.createPlayer(2);
        assertNotNull(player);
    }
}
