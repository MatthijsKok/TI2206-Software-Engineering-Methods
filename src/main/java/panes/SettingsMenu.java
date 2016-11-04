package panes;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import panes.elements.MusicSlider;
import util.Config;
import util.SceneManager;
import util.sound.Music;
import util.sound.SoundEffect;

/**
 * The settings menu.
 */
@SuppressWarnings("magicnumber")
public class SettingsMenu extends Pane {

    /**
     * The image used in the background of the settings menu.
     */
    private static final Image BACKGROUND_IMAGE =
            new Image("images/backgrounds/settings.jpg");
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

    /**
     * Create a button to go back.
     * @return button
     */
    private Button createBackButton() {
        Button button = new Button("Back");
        button.setLayoutX(180);
        button.setLayoutY(100);

        button.setOnMouseClicked(e -> SceneManager.goBack());

        return button;
    }

    /**
     * Creates a background music slider, to set the volume of the music.
     * @return sliderPane.
     */
    private Pane createBackgroundMusicSlider() {
        Pane sliderPane = new MusicSlider("Background Music Volume");

        sliderPane.setLayoutX(180);
        sliderPane.setLayoutY(192);
        sliderPane.prefWidth(1024 - 256);

        Slider slider = (Slider) sliderPane.getChildren().get(1);
        slider.setValue(Double.valueOf(Config.get("bgVolume")));
        slider.setPrefWidth(390);

        slider.valueProperty().addListener((obs) -> Music.setMusicVolume(slider.getValue()));

        slider.valueChangingProperty().addListener((obs, wasChanging, isNowChanging) -> {
            if (!isNowChanging) {
                Config.put("bgVolume", String.valueOf(slider.getValue()));
            }
        });

        return sliderPane;
    }

    /**
     * Create a sound effect slider.
     * @return sliderpane.
     */
    private Pane createSoundEffectSlider() {
        Pane sliderPane = new MusicSlider("Sound Effect Volume");

        sliderPane.setLayoutX(180);
        sliderPane.setLayoutY(300);
        sliderPane.prefWidth(1024 - 256);

        Slider slider = (Slider) sliderPane.getChildren().get(1);
        slider.setValue(Double.valueOf(Config.get("sfxVolume")));
        slider.setPrefWidth(390);

        slider.valueChangingProperty().addListener((obs, wasChanging, isNowChanging) -> {
            if (!isNowChanging) {
                double volume = slider.getValue();
                SoundEffect.setSoundEffectsVolume(volume);
                SoundEffect.SPEED_BOOST.play();
                Config.put("sfxVolume", String.valueOf(volume));
            }
        });

        return sliderPane;
    }

    /**
     * Create a GridPane for the input of player one.
     * @return GridPane for player one.
     */
    private GridPane createPlayerOneInput() {

        GridPane grid = createInputGrid("Player 1", "playerOne");

        grid.setLayoutX(180);
        grid.setLayoutY(440);
        grid.setMaxWidth(400);

        return grid;
    }

    /**
     * Create a GridPane for the input of player two.
     * @return GridPane for player two.
     */
    private Node createPlayerTwoInput() {
        GridPane grid = createInputGrid("Player 2", "playerTwo");

        grid.setLayoutX(596);
        grid.setLayoutY(440);
        grid.setMaxWidth(380);

        return grid;
    }

    /**
     * Create an Input Grid.
     * @param label         String with a label.
     * @param configMainKey String with the configMainKey
     * @return GridPane.
     */
    private GridPane createInputGrid(String label, String configMainKey) {
        GridPane grid = new GridPane();

        Label nameLabel = new Label(label);
        nameLabel.getStyleClass().add("player-text");

        Label leftKeyLabel = new Label("Left");
        Label rightKeyLabel = new Label("Right");
        Label shootKeyLabel = new Label("Shoot");

        TextField leftKeyInput = createKeyInputField(configMainKey + "LeftKey");
        TextField rightKeyInput = createKeyInputField(configMainKey + "RightKey");
        TextField shootKeyInput = createKeyInputField(configMainKey + "ShootKey");

        grid.add(nameLabel, 0, 0, 3, 1);
        grid.add(leftKeyLabel, 0, 1);
        grid.add(leftKeyInput, 0, 2);
        grid.add(rightKeyLabel, 2, 1);
        grid.add(rightKeyInput, 2, 2);
        grid.add(shootKeyLabel, 1, 1);
        grid.add(shootKeyInput, 1, 2);

        return grid;
    }

    /**
     * Create a field for the key input.
     * @param configKey String for the configuration.
     * @return A TextField with a configKey.
     */
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
