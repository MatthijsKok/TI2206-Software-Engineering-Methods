package entities.character.bullets;

import bubbletrouble.BubbleTroubleApplicationTest;
import com.sun.javafx.geom.Vec2d;
import entities.balls.ColoredBall;
import entities.character.Gun;
import entities.character.Character;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test suite for the Vine class.
 */
public class VineTest extends BubbleTroubleApplicationTest {

    private Vec2d position1 = new Vec2d(100, 300);
    private Vec2d position2 = new Vec2d(0, 0);
    private Character mockedCharacter = Mockito.mock(Character.class);
    private Gun mockedGun = Mockito.mock(Gun.class);
    private Vine vine1, vine2;


    @Before
    public void setUp() {
        when(mockedCharacter.getGun()).thenReturn(mockedGun);
        vine1 = new Vine(position1, mockedCharacter);
        vine2 = new Vine(position2, mockedCharacter);
    }

    @Test
    public void testMovingAfterInstantiation() {
        assertThat(vine1.getYSpeed(), lessThan(0.d));
    }

    /*@Test
    public void testUpdateYGreaterThanZero() {
        vine1.update(0);
        verify(mockedGun, times(0)).bulletDied();
    }

    @Test
    public void testUpdateYSmallerThanZero() {
        vine2.update(0);
        verify(mockedGun, times(1)).bulletDied();
    }*/

    @Test
    public void testCollisionWithBall() {
        ColoredBall ball = new ColoredBall(new Vec2d(300, 200), 2);
        vine1.collideWith(ball);

        verify(mockedCharacter, times(1)).increaseScore(300);
    }

//    @Test
//    public void testCollisionWithOtherEntity() {
//        WallBlock otherEntity = new WallBlock(new Vec2d(300, 200));
//        vine1.collideWith(otherEntity);
//
//        verify(mockedCharacter, times(0)).vineRemoved();
//    }
}
