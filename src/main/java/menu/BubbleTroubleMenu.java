package menu;

import game.Game;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import util.Sprite;
import util.StageManager;

import java.util.ArrayList;
import java.util.List;

import static javafx.scene.layout.BackgroundRepeat.REPEAT;
import static javafx.scene.layout.BackgroundSize.DEFAULT;
import static javax.accessibility.AccessibleRole.CANVAS;

/**
 * The menu.BubbleTroubleMenu class is an javafx element which displays the main game menu.
 */
@SuppressWarnings("checkstyle:magicnumber")
public class BubbleTroubleMenu extends javafx.scene.layout.Pane {



    /**
     * The list containing the default level files in the game.
     */
    private static final List<String> DEFAULT_LEVELS = new ArrayList<>();
    static {
        DEFAULT_LEVELS.add("src/main/resources/levels/level1.json");
        DEFAULT_LEVELS.add("src/main/resources/levels/level2.json");
    }

    /**
     * Create a new menu element with all sub nodes.
     */
    public BubbleTroubleMenu() {
        getChildren().add(createTitle());
        getChildren().add(createSinglePlayerButton());
        getChildren().add(createMultiPlayerButton());
        getChildren().add(createSettingsButton());
        getChildren().add(createQuitButton());

//        Scene scene = new Scene(grid, 300, 275);
//        scene.getStylesheets().add
//                (BubbleTroubleMenu.class.getResource("layOut.css").toExternalForm());
//

        Image image = new Image("background.jpg");
        BackgroundImage background = new BackgroundImage(image, REPEAT, REPEAT, null, DEFAULT);

    }

    private BackgroundImage createBackgroundImage() {
        Image image = new Image("background.jpg");
        BackgroundImage background = new BackgroundImage(image, REPEAT, REPEAT, null, DEFAULT);
        return background;
    }

    private Text createTitle() {
        Text text = new Text("Bubble Trouble");
        text.setX(32);
        text.setY(32);
        return text;
    }

    private Button createSinglePlayerButton() {
        Button button = new Button("Start single player game.");
        button.setLayoutX(32);
        button.setLayoutY(128);

        button.setOnMouseClicked(e -> {
            Game game = Game.getInstance();
            game.setPlayerCount(1);
            game.setLevels(DEFAULT_LEVELS);
            game.start();
        });

        return button;
    }

    private Button createMultiPlayerButton() {
        Button button = new Button("Start multi player game.");
        button.setLayoutX(32);
        button.setLayoutY(256);

        button.setOnMouseClicked(e -> {
            Game game = Game.getInstance();
            game.setPlayerCount(2);
            game.setLevels(DEFAULT_LEVELS);
            game.start();
        });

        return button;
    }

    private Button createSettingsButton() {
        Stage stage = StageManager.getStage();
        Button button = new Button("Settings");
        button.setLayoutX(stage.getWidth() - 64);
        button.setLayoutY(stage.getHeight() - 64);
        return button;
    }

    private Button createQuitButton() {
        Stage stage = StageManager.getStage();
        Button button = new Button("Quit");
        button.setLayoutX(64);
        button.setLayoutY(stage.getHeight() - 64);

        button.setOnMouseClicked(e -> Platform.exit());

        return button;
    }
}
