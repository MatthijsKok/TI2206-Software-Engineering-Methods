package entities.character.bullets;

import entities.blocks.WallBlock;
import main.BubbleTroubleApplicationTest;
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
    private Character mockedCharacter = Mockito.mock(Character.class);
    private Gun mockedGun = Mockito.mock(Gun.class);
    private Vine vine1;


    @Before
    public void setUp() {
        when(mockedCharacter.getGun()).thenReturn(mockedGun);
        vine1 = new Vine(position1, mockedCharacter);
    }

    @Test
    public void testMovingAfterInstantiation() {
        assertThat(vine1.getYSpeed(), lessThan(0.d));
    }

    @Test
    public void testCollisionWithBall() {
        ColoredBall ball = new ColoredBall(new Vec2d(300, 200), 2);
        vine1.collideWith(ball);

        verify(mockedCharacter, times(1)).increaseScore(300);
    }

    @Test
    public void testCollisionWithBlock() {
        WallBlock block = Mockito.mock(WallBlock.class);
        vine1.collideWith(block);

        verify(mockedGun, times(1)).bulletDied();
    }
}
