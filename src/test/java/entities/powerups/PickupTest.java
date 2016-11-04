package entities.powerups;

import graphics.Sprite;
import main.BubbleTroubleApplicationTest;
import com.sun.javafx.geom.Vec2d;
import entities.blocks.FloorBlock;
import entities.character.Character;
import game.Game;
import level.Level;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Tests for Pickups.
 */
public class PickupTest extends BubbleTroubleApplicationTest{

    private Pickup pickup;
    private Vec2d spawnPosition = new Vec2d(100, 300);
    private Character character;
    private Level level;
    private static FloorBlock floor;
    private Sprite sprite = new Sprite("/blocks/mushroom_block.png");

    @Before
    public void setUp() {
        AbstractPowerUp powerUp = new AbstractPowerUp() {
            @Override
            protected void activate() {

            }
        };

        pickup = new Pickup(spawnPosition, powerUp);

        character = new Character(new Vec2d(100, 300));
        floor = new FloorBlock(new Vec2d(0, 0), sprite);

        level = Game.getCurrentLevel();
        level.addEntity(pickup);
        level.update(1);
    }

    @Test
    public void testConstructor() {
        assertNotNull("There should be a pickup", pickup);
    }

    @Test
    public void testUpdate1sec() {
        pickup.update(1);
        assertThat(level.getEntities().contains(pickup), is(true));
    }

    @Test
    public void testUpdate30sec() {
        pickup.update(30);
        assertThat(level.getEntities().contains(pickup), is(false));
    }

    @Test
    public void testCollideWithCharacter() {
        pickup.collideWith(character);
        level.update(1);
        assertThat(level.getEntities().contains(pickup), is(false));
    }

    @Test
    public void testCollideWithFloor() {
        pickup.collideWith(floor);
        level.update(1);
        assertThat(level.getEntities().contains(pickup), is(true));
    }
}
