package game.state;

import game.Game;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test suite for the InProgressState class.
 * Most functionality of this class is tested in other test suites.
 */
public class InProgressStateTest extends AbstractGameStateTest {
    @Test
    public void testConstructor() {
        new InProgressState(Game.getCurrentLevel());
        assertThat(getAnchor().getChildren().size(), is(0));
        Game.setState(new NotStartedState());
    }
}
