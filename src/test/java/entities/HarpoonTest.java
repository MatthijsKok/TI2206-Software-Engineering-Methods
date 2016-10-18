package entities;


import bubbletrouble.BubbleTroubleApplicationTest;
import com.sun.javafx.geom.Vec2d;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test suite for the Harpoon class.
 */
public class HarpoonTest extends BubbleTroubleApplicationTest {

    private Vec2d position1 = new Vec2d(100, 300);
    private Vec2d position2 = new Vec2d(0, 0);
    private Character mockedCharacter = Mockito.mock(Character.class);
    private Harpoon harpoon1, harpoon2;


    @Before
    public void setUp() {
        harpoon1 = new Harpoon(position1, mockedCharacter);
        harpoon2 = new Harpoon(position2, mockedCharacter);
    }

    @Test
    public void testMovingAfterInstantiation() {
        assertThat(harpoon1.getYSpeed(), lessThan(0.d));
    }

    @Test
    public void testUpdateYGreaterThanZero() {
        harpoon1.update(0);
        verify(mockedCharacter, times(0)).harpoonRemoved();
    }

    @Test
    public void testUpdateYSmallerThanZero() {
        harpoon2.update(0);
        verify(mockedCharacter, times(1)).harpoonRemoved();
    }

    @Test
    public void testCollisionWithBall() {
        Ball ball = new Ball(new Vec2d(300, 200), 2);
        harpoon1.collideWith(ball);

        verify(mockedCharacter, times(1)).increaseScore(300);
    }

    @Test
    public void testCollisionWithOtherEntity() {
        WallBlock otherEntity = new WallBlock(new Vec2d(300, 200));
        harpoon1.collideWith(otherEntity);

        verify(mockedCharacter, times(0)).harpoonRemoved();
    }
}
