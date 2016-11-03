package util;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.UtilityClassTest;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.lang.reflect.InvocationTargetException;

import static junit.framework.TestCase.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

/**
 * Test suite for the SceneManager class.
 */
public class SceneManagerTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        SceneManager.setStage(stage);
    }

    @Test
    public void testGetCurrentSceneNull() {
        assertThat(SceneManager.getCurrentScene(), nullValue());
    }

    @Test
    public void testGoToScene() {
        Platform.runLater(() -> {
            Pane pane = new Pane();
            SceneManager.addScene("scene", pane);

            SceneManager.goToScene("scene");

            Scene scene = SceneManager.getCurrentScene();

            if (scene == null) {
                fail();
            }

            assertThat(
                    scene.getRoot(),
                    is(pane));
        });
    }

    @Test
    public void testGoBack() {
        Platform.runLater(() -> {
            SceneManager.addScene("scene1", new Pane());
            SceneManager.addScene("scene2", new Pane());

            SceneManager.goToScene("scene1");
            SceneManager.goToScene("scene2");
            SceneManager.goBack();

            assertThat(
                    SceneManager.getCurrentScene(),
                    is(SceneManager.getScene("scene1")));
        });
    }

    @Test
    public void testUtilityClass() {
        try {
            UtilityClassTest.assertUtilityClassWellDefined(SceneManager.class);
        } catch (NoSuchMethodException | InstantiationException
                | IllegalAccessException | InvocationTargetException e) {
            fail();
        }
    }
}
