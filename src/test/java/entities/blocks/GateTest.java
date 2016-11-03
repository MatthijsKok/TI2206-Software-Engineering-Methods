package entities.blocks;

import com.sun.javafx.geom.Vec2d;
import entities.balls.ColoredBall;
import game.Game;
import level.Level;
import main.BubbleTroubleApplicationTest;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


/**
 * Test suite for the Gate class.
 */
public class GateTest extends BubbleTroubleApplicationTest {

    private final Vec2d spawnPosition = new Vec2d(0, 0);
    private final ColoredBall.Color color = ColoredBall.Color.BLUE;
    private final ColoredBall.Color otherColor = ColoredBall.Color.RED;

    private Level level;
    private Gate gate;

    @Before
    public void setUp() {
        level = Game.getCurrentLevel();
        gate = new Gate(spawnPosition, color);
    }

    @Test
    public void testUpdateBallsLeft() {
        level.addEntity(new ColoredBall(spawnPosition, 0, color));

        gate.update(1);

        assertThat(gate.getYSpeed(), is(0.d));
    }

    @Test
    public void testUpdateNoBallsLeft() {
        level.addEntity(new ColoredBall(spawnPosition, 0, otherColor));

        gate.update(1);

        assertThat(gate.getYSpeed(), greaterThan(0.d));
    }

    @Test
    public void testPassThreshold() {
        level.addEntity(gate);

        level.update(50);
        level.update(50);
        level.update(50);

        assertThat(level.getEntities(), not(contains(gate)));
    }
}
