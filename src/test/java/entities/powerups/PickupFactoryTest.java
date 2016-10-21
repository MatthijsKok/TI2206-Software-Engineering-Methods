package entities.powerups;

import bubbletrouble.BubbleTroubleApplicationTest;
import com.sun.javafx.geom.Vec2d;
import entities.character.Character;
import game.Game;
import game.player.Player;
import level.Level;
import org.junit.Before;
import org.junit.Test;

import static entities.powerups.PickupFactory.spawnRandomPickUp;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Tests for the PickupFactory.
 */
public class PickupFactoryTest extends BubbleTroubleApplicationTest {

    private Vec2d spawnPosition = new Vec2d(100, 300);
    private Level level;

    @Before
    public void setUp() {
        Character character = new Character(new Vec2d(100, 300));
        Player player = new Player(1, "left", "right", "shoot");
        character.setPlayer(player);
        level = Game.getInstance().getState().getCurrentLevel();
    }

    @Test
    public void testSpawnRandomPickUp() {
        spawnRandomPickUp(level, spawnPosition);
        assertThat(level.getEntities().size(), is(2));
    }
}
