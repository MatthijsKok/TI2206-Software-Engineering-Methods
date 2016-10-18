package entities.behaviour;

import bubbletrouble.BubbleTroubleApplicationTest;
import com.sun.javafx.geom.Vec2d;
import entities.AbstractEntity;
import entities.balls.ColoredBall;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

/**
 * Test suite for the GravityBehaviour class.
 */
public class PhysicsBehaviourTest extends BubbleTroubleApplicationTest {

    private AbstractEntity entity;
    private AbstractPhysicsBehaviour behaviour;

    @Before
    public void setUp() {
        entity = new ColoredBall(new Vec2d(0, 0), 1);
        behaviour = new GravityBehaviour(entity);
    }

    @Test
    public void testApplyGravity() {
        double ySpeed = entity.getYSpeed();
        behaviour.applyPhysics(1);
        assertThat(entity.getYSpeed(), greaterThan(ySpeed));
    }

    @Test
    public void testGetEntity() {
        assertThat(behaviour.getEntity(), is(entity));
    }
}
