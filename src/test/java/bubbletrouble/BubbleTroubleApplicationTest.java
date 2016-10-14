package bubbletrouble;

import game.Game;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import level.Level;
import org.testfx.framework.junit.ApplicationTest;
import util.CanvasManager;
import util.StageManager;

import java.util.ArrayList;
import java.util.List;

/**
 * This class serves as a base class for tests which use JavaFX.
 */
public class BubbleTroubleApplicationTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        StageManager.init(stage);

        Canvas canvas = CanvasManager.createCanvas(stage);
        CanvasManager.setCanvas(canvas);

        setUpGame();
    }

    private void setUpGame() {
        List<Level> levelList = new ArrayList<>();
        levelList.add(new Level("levelFileName"));
        Game.getInstance().setLevels(levelList);
        Game.getInstance().setPlayerCount(1);
    }
}
