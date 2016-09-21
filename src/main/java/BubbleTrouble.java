import game.Game;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.GameCanvasManager;
import util.KeyboardInputManager;
import util.logging.LogLevel;
import util.logging.Logger;

/**
 * Bubble Trouble is a game written in JavaFX.
 */
public class BubbleTrouble extends Application {
    /**
     * The logger access point to which everything will be logged.
     */
    private static final Logger LOGGER = new Logger();

    /**
     * Entry method of the whole game.
     * @param args optional arguments to start the game with.
     */
    public static void main(final String[] args) {
        launch(args);
    }

    /**
     * This method opens a new window and starts the game.
     * @param stage the window in which the game will be displayed.
     */
    public final void start(final Stage stage) {
        LOGGER.setLevel(LogLevel.INFO);
        stage.setTitle("Bubble Trouble");
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        KeyboardInputManager.getInstance().addScene(scene);
        root.getChildren().add(GameCanvasManager.getInstance().getCanvas());

        final Game game = Game.getInstance();

        new AnimationTimer() {
            public void handle(final long currentNanoTime) {
                // Update the game
                game.update();

                // And redraw
                game.draw();
            }
        }.start();

        game.start();

        stage.show();
    }
}
