package entities;

import bubbletrouble.BubbleTroubleApplicationTest;
import com.sun.javafx.geom.Vec2d;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

/**
 * Test suite for the Character class.
 */
public class CharacterTest extends BubbleTroubleApplicationTest {

    private Character character;
    private Vec2d spawnPosition = new Vec2d(100, 300);

    @Before
    public void setUp() {
        character = new Character(spawnPosition);
    }

    @Test
    public void testCharacterHasHarpoon() {
        assertNotNull("Each character should have a harpoon", character.getHarpoon());
    }

    @Test
    public void testDie() {
        character.die();
        assertFalse("A character should not be alive when it died.", character.isAlive());
    }

    @Test
    public void testStop() {
        character.stop();
        double x = character.getX();
        character.update(1);
        character.updatePosition(1);
        assertThat("A character should not move in x-direction if its direction is 0", character.getX(), is(x));
    }

    @Test
    public void testMoveRight() {
        character.moveRight();
        double x = character.getX();
        character.update(1);
        character.updatePosition(1);
        assertThat("A character should move to the right if its direction is 1", character.getX(), greaterThan(x));
    }

    @Test
    public void testMoveLeft() {
        character.moveLeft();
        double x = character.getX();
        character.update(1);
        character.updatePosition(1);
        assertThat("A character should move to the left if its direction is -1", character.getX(), lessThan(x));
    }

    @Test
    public void testSetShooting() {
        character.setShooting(true);
        character.update(1);
        assertThat("A character should shoot when told to.", character.getHarpoon().isVisible(), is(true));
    }

    @Test
    public void testCollideWithBall() {
        Ball mockedBall = Mockito.mock(Ball.class);

        character.collideWith(mockedBall);

        assertThat("A character should die when it collides with a ball", character.isAlive(), is(false));
    }

    @Test
    public void testCollideWithFloorBlockOutside() {
        FloorBlock floor = new FloorBlock(
                new Vec2d(
                        spawnPosition.x,
                        spawnPosition.y + 100));

        double y = character.getY();
        character.collideWith(floor);

        assertThat(character.getY(), is(y));
    }

    @Test
    public void testCollideWithFloorBlockFromAbove() {
        FloorBlock floor = new FloorBlock(
                new Vec2d(
                        spawnPosition.x,
                        spawnPosition.y - 10));

        double y = character.getY();
        character.collideWith(floor);

        assertThat(character.getY(), lessThan(y));
    }

    @Test
    public void testCollideWithFloorBlockFromBelow() {
        FloorBlock floor = new FloorBlock(
                new Vec2d(
                        spawnPosition.x,
                        spawnPosition.y - 50));

        double y = character.getY();
        character.collideWith(floor);

        assertThat(character.getY(), greaterThan(y));
    }

    @Test
    public void testCollideWithWallBlockOutside() {
        WallBlock wall = new WallBlock(
                new Vec2d(
                        spawnPosition.x + 100,
                        spawnPosition.y));

        double x = character.getX();
        character.collideWith(wall);

        assertThat(character.getX(), is(x));
    }

    @Test
    public void testCollideWithWallBlockFromLeft() {
        WallBlock wall = new WallBlock(
                new Vec2d(
                        spawnPosition.x + 5,
                        spawnPosition.y));

        double x = character.getX();
        character.collideWith(wall);

        assertThat(character.getX(), lessThan(x));
    }

    @Test
    public void testCollideWithWallBlockFromRight() {
        WallBlock wall = new WallBlock(
                new Vec2d(
                        spawnPosition.x - 37,
                        spawnPosition.y));

        double x = character.getX();
        character.collideWith(wall);

        assertThat(character.getX(), greaterThan(x));
    }
}