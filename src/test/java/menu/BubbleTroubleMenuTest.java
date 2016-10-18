package menu;

import bubbletrouble.BubbleTroubleApplicationTest;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.*;

/**
 * Test class for BubbleTroubleMenu
 */
public class BubbleTroubleMenuTest extends BubbleTroubleApplicationTest {

    private Button singlePlayerButton;
    private BubbleTroubleMenu menu;

    @Override
    public void start(Stage stage) {
        menu = new BubbleTroubleMenu();
    }

    @Before
    public void setUp() {
        // it slaat nergens op
        singlePlayerButton = lookup("buttonSP").queryFirst();
    }

    @After
    public void tearDown() throws TimeoutException {
        release(new MouseButton[] {});
    }

    @Test
    public void testOnClickSinglePlayerButton() {
//        Node singlePlayerButton = menu.getChildren().get(0);
//        singlePlayerButton.onC
//        assertTrue(menu.getChildren().get(0) instanceof Button);
    }

    @Test
    public void testSinglePlayerButton() {
        final String errMsg = "This button does not exist";
        assertNotNull(errMsg, menu.getChildren().get(0));
        assertTrue(menu.getChildren().get(0) instanceof Button);
    }

    @Test
    public void testMultiPlayerButton() {
        final String errMsg = "This button does not exist";
        assertNotNull(errMsg, menu.getChildren().get(1));
        assertTrue(menu.getChildren().get(1) instanceof Button);
    }

    @Test
    public void testSettingsButton() {
        final String errMsg = "This button does not exist";
        assertNotNull(errMsg, menu.getChildren().get(2));
        assertTrue(menu.getChildren().get(2) instanceof Button);
    }

    @Test
    public void testQuitButton() {
        final String errMsg = "This button does not exist";
        assertNotNull(errMsg, menu.getChildren().get(3));
        assertTrue(menu.getChildren().get(3) instanceof Button);
    }
}
