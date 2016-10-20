package entities;

import bubbletrouble.BubbleTroubleApplicationTest;
import com.sun.javafx.geom.Vec2d;
import entities.balls.ColoredBall;
import game.Game;
import geometry.Circle;
import level.Level;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Test suite for the AbstractBall class.
 */
public class BallTest extends BubbleTroubleApplicationTest {

    private ColoredBall ball;
    private Vine vine;
    private Vec2d spawnPosition = new Vec2d(100, 300);
    private int ballSize = 2;
    private ColoredBall.Color ballColor = ColoredBall.Color.BLUE;

    private Level level;

    @Before
    public void setUp() {
        level = Game.getInstance().getState().getCurrentLevel();
        ball = new ColoredBall(spawnPosition, ballSize, ballColor);
        level.addEntity(ball);
        level.update(0);

        vine = new Vine(new Vec2d(0, 0), Mockito.mock(Character.class));
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
                        ball.getX() + ((Circle) ball.getShape()).getRadius() - 1,
                        ball.getY()));

        ball.getSpeed().x = 10;

        ball.collideWith(wall);
        assertThat(ball.getXSpeed(), lessThan(0.d));
    }

    @Test
    public void testCollideWithWallBlockFromRight() {
        WallBlock wall = new WallBlock(
                new Vec2d(
                        ball.getX() - ((Circle) ball.getShape()).getRadius() - 32 + 1,
                        ball.getY()));

        ball.getSpeed().x = -10;

        ball.collideWith(wall);
        assertThat(ball.getXSpeed(), greaterThan(0.d));
    }

    @Test
    public void testCollideWithWallBlockFromLeftMovingLeft() {
        WallBlock wall = new WallBlock(
                new Vec2d(
                        ball.getX() + ((Circle) ball.getShape()).getRadius() - 1,
                        ball.getY()));

        ball.getSpeed().x = -10;

        ball.collideWith(wall);
        assertThat(ball.getXSpeed(), lessThan(0.d));
    }

    @Test
    public void testCollideWithWallBlockFromRightMovingRight() {
        WallBlock wall = new WallBlock(
                new Vec2d(
                        ball.getX() - ((Circle) ball.getShape()).getRadius() - 32 + 1,
                        ball.getY()));

        ball.getSpeed().x = 10;

        ball.collideWith(wall);
        assertThat(ball.getXSpeed(), greaterThan(0.d));
    }

    @Test
    public void testCollideWithHarpoon() {
        ball.collideWith(vine);

        assertFalse(level.getEntities().contains(ball));
    }

    @Test
    public void testCollideWithShield() {
        Shield shield = Mockito.mock(Shield.class);
        ball.collideWith(shield);

        assertThat(ball.getYSpeed(), lessThan(0.d));
    }

    /*@Test
    public void testCollideWithHarpoonSizeIsZero() {
        AbstractBall ball2 = new AbstractBall(spawnPosition, 0);

        level.removeEntity(ball);
        level.addEntity(ball2);
        level.update(0);

        int size = level.getEntities().size();

        ball2.collideWith(vine);

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
    public void testBallGetColor() {
        List<String> colorNames = new ArrayList<>();

        colorNames.add("blue");
        colorNames.add("green");
        colorNames.add("orange");
        colorNames.add("purple");
        colorNames.add("red");
        colorNames.add("yellow");

        assertArrayEquals(
                ColoredBall.Color.values(),
                colorNames.stream()
                        .map(ColoredBall::getColor).toArray());
    }
}