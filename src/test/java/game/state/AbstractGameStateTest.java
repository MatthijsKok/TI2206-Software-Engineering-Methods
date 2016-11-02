package game.state;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import main.BubbleTroubleApplicationTest;
import org.junit.Before;
import org.junit.Test;
import panes.elements.MarioButton;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Base test suite for the game state classes.
 */
public abstract class AbstractGameStateTest extends BubbleTroubleApplicationTest {

    private Pane pane;

    @Before
    public void setUp() {
        pane = new Pane();
        GameStateHelper.setAnchor(pane);
    }

    @Test
    public abstract void testConstructor();

    final Pane getAnchor() {
        return pane;
    }

    final void fireButton(final int index) {
        Pane menu = (Pane) getAnchor().getChildren().get(0);
        MarioButton button = (MarioButton) menu.getChildren().get(index + 1);

        button.fire();
    }

    final void testChildAmount(final int amount) {
        Pane menu = (Pane) pane.getChildren().get(0);

        assertThat(menu.getChildren().size(), is(amount));
    }

    final void testTitle(final String title) {
        Pane menu = (Pane) pane.getChildren().get(0);
        Label titleLabel = (Label) menu.getChildren().get(0);

        assertThat(titleLabel.getText(), is(title));
    }
}