package panes;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import util.Config;
import util.SceneManager;

/**
 * The settings menu.
 */
public class SettingsMenu extends Pane {

    /**
     * The image used in the background of the settings menu.
     */
    private static final Image BACKGROUND_IMAGE =
            new Image("backgrounds/background1.png");
    /**
     * The background image of the settings menu.
     */
    private static final BackgroundImage BACKGROUND =
            new BackgroundImage(
                    BACKGROUND_IMAGE, null, null,
                    BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

    /**
     * Creates a new settings menu.
     * @param stage the stage this menu is drawn on.
     */
    public SettingsMenu(Stage stage) {
        setPrefSize(stage.getWidth(), stage.getHeight());

        getChildren().addAll(
                createBackButton(),
                createBackgroundMusicSlider(),
                createSoundEffectSlider(),
                createPlayerOneInput(),
                createPlayerTwoInput()
        );

        setBackground(new Background(BACKGROUND));
    }

    private Button createBackButton() {
        Button button = new Button();
        button.setText("back");
        button.getStyleClass().add("back");
        button.setOnMouseClicked(e -> SceneManager.goBack());

        return button;
    }

    private Slider createBackgroundMusicSlider() {
        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(100);
        slider.setBlockIncrement(10);

        slider.setOnDragDone(dragEvent -> Config.put("bgVolume", String.valueOf(slider.getValue())));
        return slider;
    }

    private Slider createSoundEffectSlider() {
        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(100);
        slider.setBlockIncrement(10);

        slider.setOnDragDone(dragEvent -> Config.put("sfxVolume", String.valueOf(slider.getValue())));
        return slider;
    }

    private GridPane createPlayerOneInput() {
        GridPane grid = createInputGrid("Player 1", "playerOne");

        grid.setLayoutX(128);
        grid.setLayoutY(200);
        grid.setMaxWidth(352);

        return grid;
    }

    private Node createPlayerTwoInput() {
        GridPane grid = createInputGrid("Player 2", "playerTwo");

        grid.setLayoutX(544);
        grid.setLayoutY(200);
        grid.setMaxWidth(352);

        return grid;
    }

    private GridPane createInputGrid(String label, String configMainKey) {
        GridPane grid = new GridPane();

        Label nameLabel = new Label(label);

        Label leftKeyLabel = new Label("Left");
        Label rightKeyLabel = new Label("Right");
        Label shootKeyLabel = new Label("Shoot");

        TextField leftKeyInput = createKeyInputField(configMainKey + "LeftKey");
        TextField rightKeyInput = createKeyInputField(configMainKey + "RightKey");
        TextField shootKeyInput = createKeyInputField(configMainKey + "ShootKey");

        grid.add(nameLabel, 0, 0, 3, 1);
        grid.add(leftKeyLabel, 0, 1);
        grid.add(leftKeyInput, 0, 2);
        grid.add(rightKeyLabel, 1, 1);
        grid.add(rightKeyInput, 1, 2);
        grid.add(shootKeyLabel, 2, 1);
        grid.add(shootKeyInput, 2, 2);

        return grid;
    }

    private TextField createKeyInputField(String configKey) {
        TextField field = new TextField(Config.get(configKey));

        field.setOnKeyPressed(event -> {
            String code = event.getCode().toString();
            field.textProperty().setValue(code);
            Config.put(configKey, code);
        });

        return field;
    }
}
