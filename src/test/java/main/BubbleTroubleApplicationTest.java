package main;

import game.Game;
import javafx.stage.Stage;
import level.Level;
import level.LevelTimer;
import org.testfx.framework.junit.ApplicationTest;

import java.util.ArrayList;
import java.util.List;

/**
 * This class serves as a base class for tests which use JavaFX.
 */
public class BubbleTroubleApplicationTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        BubbleTrouble.loadScenes(stage);

        setUpGame();
    }

    private void setUpGame() {
        Level level1 = new Level("src/main/resources/levels/level1.json");
        Level level2 = new Level("src/main/resources/levels/level2.json");

        level1.setTimer(new LevelTimer());
        level2.setTimer(new LevelTimer());

        List<Level> levelList = new ArrayList<>();
        levelList.add(level1);
        levelList.add(level2);
        Game.setLevels(levelList);
        Game.setPlayerCount(1);
    }
}