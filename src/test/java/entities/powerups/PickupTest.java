package entities.powerups;

import bubbletrouble.BubbleTroubleApplicationTest;
import com.sun.javafx.geom.Vec2d;
import entities.*;
import entities.Character;
import game.Game;
import game.player.Player;
import graphics.Sprite;
import level.Level;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

/**
 * Tests for Pickups.
 */
public class PickupTest extends BubbleTroubleApplicationTest{

    private Pickup pickup;
    private Vec2d spawnPosition = new Vec2d(100, 300);
    private Character character;
    private Level level;
    private static FloorBlock floor;

    @Before
    public void setUp() {
        Sprite sprite = new Sprite("powerUps/1-up.png");
        AbstractPowerUp mockedPowerUp = Mockito.mock(AbstractPowerUp.class);
        when(mockedPowerUp.getSprite()).thenReturn(sprite);
        pickup = new Pickup(spawnPosition, mockedPowerUp);

        character = new Character(new Vec2d(100, 300));
        floor = new FloorBlock(new Vec2d(0, 0));
        Player player = new Player(1, "left", "right", "shoot");
        character.setPlayer(player);
        level = Game.getInstance().getState().getCurrentLevel();
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
