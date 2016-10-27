package entities.character;

import bubbletrouble.BubbleTroubleApplicationTest;
import com.sun.javafx.geom.Vec2d;
import entities.balls.AbstractBall;
import entities.blocks.FloorBlock;
import entities.blocks.WallBlock;
import game.player.Player;
import geometry.Rectangle;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * Test suite for the Character class.
 */
public class CharacterTest extends BubbleTroubleApplicationTest {

    private Player player;
    private Character character;
    private Gun gun;
    private Vec2d spawnPosition = new Vec2d(100, 300);

    private int countEntities() {
        return character.getLevel().getEntities().size();
    }

    @Before
    public void setUp() {
        character = new Character(spawnPosition);
        gun = character.getGun();
        player = new Player(0, "", "", "");
        player.setCharacter(character);
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

    /*@Test
    public void testSetShooting() {
        character.setShooting(true);
        character.update(1);
        assertThat("A character should shoot when told to.", character.getVine().isVisible(), is(true));
    }*/

    @Test
    public void testCollideWithBall() {
        AbstractBall mockedBall = Mockito.mock(AbstractBall.class);

        character.collideWith(mockedBall);

        assertThat("A entities.character should die when it collides with a ball", character.isAlive(), is(false));
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
        Rectangle rect = (Rectangle) character.getShape();
        FloorBlock floor = new FloorBlock(
                new Vec2d(
                        spawnPosition.x,
                        rect.getBottom() - 50));

        double y = character.getY();
        character.collideWith(floor);

        assertThat(character.getY(), greaterThan(y));
    }

    @Test
    public void testCollideWithWallBlockOutside() {
        Rectangle rect = (Rectangle) character.getShape();
        WallBlock wall = new WallBlock(
                new Vec2d(
                        spawnPosition.x + 100,
                        rect.getBottom()));

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

    /*@Test
    public void testIncreaseScore() {
        int score = images.player.getScore();
        character.increaseScore(100);
        assertThat(images.player.getScore(), is(score + 100));
    }*/

    @Test
    public void testShoot() {
        int count = countEntities();
        gun.setShooting(true);
        gun.update(1);
        assertThat(countEntities(), greaterThan(count));
    }

    @Test
    public void testIncreaseVineCount() {
        int count = countEntities();
        gun.increaseMaxConcurrentShots(1);
        gun.setShooting(true);
        gun.update(1); // 1 shot
        gun.setShooting(false);
        gun.update(1); // 1 shot
        gun.setShooting(true);
        gun.update(1); // 2 shots
        assertThat(countEntities(), is(count + 2));
    }

    @Test
    public void testIncreaseLife() {
        character.die();

        int lives = player.getLives();

        character.increaseLife();

        assertThat(player.getLives(), is(lives + 1));
    }
}