package entities;

import bubbletrouble.BubbleTroubleApplicationTest;
import com.sun.javafx.geom.Vec2d;
import game.Game;
import graphics.Sprite;
import javafx.scene.image.Image;
import level.Level;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Test suite for the Ball class.
 */
public class BallTest extends BubbleTroubleApplicationTest {

    private Ball ball;
    private Harpoon harpoon;
    private Vec2d spawnPosition = new Vec2d(100, 300);
    private int ballSize = 2;
    private Ball.Color ballColor = Ball.Color.BLUE;

    private Level level;

    @Before
    public void setUp() {
        level = Game.getInstance().getState().getCurrentLevel();
        ball = new Ball(spawnPosition, ballSize, ballColor);
        level.addEntity(ball);
        level.update(0);

        harpoon = new Harpoon(new Vec2d(0, 0), Mockito.mock(Character.class));
    }

    @After
    public void tearDown() {
        level.removeEntity(ball);
        level.update(0);
    }

    @Test
    public void testGetSize() {
        assertThat(ball.getSize(), is(ballSize));
    }

    @Test
    public void testGetColor() {
        assertThat(ball.getColor(), is(ballColor));
    }

    @Test
    public void testUpdate() {
        double ySpeed = ball.getYSpeed();
        ball.update(1);
        assertThat(ball.getYSpeed(), greaterThan(ySpeed));
    }

    @Test
    public void testCollideWithFloorBlock() {
        FloorBlock floor = new FloorBlock(
                new Vec2d(
                        ball.getX(),
                        ball.getY()));

        ball.collideWith(floor);
        assertThat("A ball should bounce up when it hits a floor", ball.getYSpeed(), lessThan(0.d));
    }

    @Test
    public void testCollideWithWallBlockOutside() {
        WallBlock wall = new WallBlock(
                new Vec2d(
                        ball.getX() + 1000,
                        ball.getY()));

        double xSpeed = ball.getXSpeed();
        ball.collideWith(wall);
        assertThat(ball.getXSpeed(), is(xSpeed));
    }

    @Test
    public void testCollideWithWallBlockFromLeft() {
        WallBlock wall = new WallBlock(
                new Vec2d(
                        ball.getX() + ball.getRadius() - 1,
                        ball.getY()));

        ball.getSpeed().x = 10;

        ball.collideWith(wall);
        assertThat(ball.getXSpeed(), lessThan(0.d));
    }

    @Test
    public void testCollideWithWallBlockFromRight() {
        WallBlock wall = new WallBlock(
                new Vec2d(
                        ball.getX() - ball.getRadius() - 32 + 1,
                        ball.getY()));

        ball.getSpeed().x = -10;

        ball.collideWith(wall);
        assertThat(ball.getXSpeed(), greaterThan(0.d));
    }

    @Test
    public void testCollideWithWallBlockFromLeftMovingLeft() {
        WallBlock wall = new WallBlock(
                new Vec2d(
                        ball.getX() + ball.getRadius() - 1,
                        ball.getY()));

        ball.getSpeed().x = -10;

        ball.collideWith(wall);
        assertThat(ball.getXSpeed(), lessThan(0.d));
    }

    @Test
    public void testCollideWithWallBlockFromRightMovingRight() {
        WallBlock wall = new WallBlock(
                new Vec2d(
                        ball.getX() - ball.getRadius() - 32 + 1,
                        ball.getY()));

        ball.getSpeed().x = 10;

        ball.collideWith(wall);
        assertThat(ball.getXSpeed(), greaterThan(0.d));
    }

    @Test
    public void testCollideWithHarpoon() {
        ball.collideWith(harpoon);

        assertFalse(level.getEntities().contains(ball));
    }

    /*@Test
    public void testCollideWithHarpoonSizeIsZero() {
        Ball ball2 = new Ball(spawnPosition, 0);

        level.removeEntity(ball);
        level.addEntity(ball2);
        level.update(0);

        int size = level.getEntities().size();

        ball2.collideWith(harpoon);

        assertThat(level.getEntities().size(), is(size - 1));
    }*/

    @Test
    public void testCollideWithOtherEntity() {
        Character character = new Character(spawnPosition);
        Vec2d position = ball.getPosition();
        Vec2d speed = ball.getSpeed();

        ball.collideWith(character);
        assertThat(ball.getPosition(), is(position));
        assertThat(ball.getSpeed(), is(speed));
    }

    @Test
    public void testBallRandomColor() {
        assertNotNull(Ball.randomColor());
    }

    @Test
    public void testBallGetColor() {
        List<String> colorNames = new ArrayList<>();

        colorNames.add("blue");
        colorNames.add("green");
        colorNames.add("orange");
        colorNames.add("purple");
        colorNames.add("red");
        colorNames.add("yellow");

        assertArrayEquals(
                Ball.Color.values(),
                colorNames.stream()
                        .map(Ball::getColor).toArray());
    }
}