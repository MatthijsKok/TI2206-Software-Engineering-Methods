package entities.powerups;

import bubbletrouble.BubbleTroubleApplicationTest;
import com.sun.javafx.geom.Vec2d;
import entities.Character;
import game.Game;
import game.player.Player;
import level.Level;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

/**
 * Tests for Pickups.
 */
public class PickupTest extends BubbleTroubleApplicationTest{

    private ExtraLife extraLife;
    private Character character;
    private Player player;
    private Vec2d spawnPosition = new Vec2d(100, 300);
    private Level level;

    @Before
    public void setUp() {
        extraLife = new ExtraLife();
        character = new Character(spawnPosition);
        player = new Player(1, "left", "right", "shoot");
        character.setPlayer(player);
        extraLife.setTarget(character);
        level = Game.getInstance().getState().getCurrentLevel();
    }

    @Test
    public void testConstructor() {
        Pickup pickup = new Pickup(spawnPosition, extraLife);
        assertNotNull("There should be a pickup", pickup);
    }

    @Test
    public void testUpdate() {
        level.update(400);
        assertNotNull(level.getTimeLeft());
    }

}
