package ui;

import game.Game;
import game.GameState;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import level.Level;
import util.logging.Logger;

/**
 * Takes care of the total UI of a game instance.
 */
public class GameUI extends AbstractUIElement {

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getInstance();

    /**
     * The overlay for when the game is won.
     */
    private final GameWonOverlay gameWonOverlay = new GameWonOverlay();
    /**
     * The overlay for when the game is lost.
     */
    private final GameLostOverlay gameLostOverlay = new GameLostOverlay();
    /**
     * The overlay for when a level is won.
     */
    private final LevelWonOverlay levelWonOverlay = new LevelWonOverlay();
    /**
     * The overlay for when a level is lost.
     */
    private final LevelLostOverlay levelLostOverlay = new LevelLostOverlay();
    /**
     * The overlay for when a level is lost by timeout.
     */
    private final LevelTimeUpOverlay timeUpOverlay = new LevelTimeUpOverlay();
    /**
     * The overlay for when the game is paused.
     */
    private final PauseOverlay pauseOverlay = new PauseOverlay();
    /**
     * The hud for when playing the game.
     */
    private HeadsUpDisplay hud;

    /**
     * Creates a new GameUI instance handling the UI of Game game.
     *
     * @param game the game to draw the ui for.
     */
    public GameUI(final Game game) {

        init(game);
    }

    /**
     * Initializes the ui elements in a level.
     *
     * @param game the game to init the hud for.
     */
    private void init(final Game game) {
        switch (game.getPlayerCount()) {
            case 1:
                hud = new SinglePlayerHUD();
                break;
            case 2:
                hud = new MultiPlayerHUD();
                break;
            default:
                hud = new HeadsUpDisplay();
                break;
        }
    }

    /**
     * Draws all ui elements in the game.
     * @param canvas The Canvas to draw on
     * @param graphicsContext The GraphicsContext to draw on
     */
    public void draw(final Canvas canvas, final GraphicsContext graphicsContext) {
        LOGGER.trace("Drawing UI elements...");

        final GameState state = Game.getInstance().getState();
        final Level level = state.getCurrentLevel();

        if (state.isWon()) {
            gameWonOverlay.draw(canvas, graphicsContext);
        } else if (state.isLost()) {
            gameLostOverlay.draw(canvas, graphicsContext);
        } else if (level.isWon()) {
            levelWonOverlay.draw(canvas, graphicsContext);
        } else if (level.isLost()) {
            if (level.getTimeLeft() <= 0) {
                timeUpOverlay.draw(canvas, graphicsContext);
            } else {
                levelLostOverlay.draw(canvas, graphicsContext);
            }
        } else if (state.isInProgress()) {
            hud.draw(canvas, graphicsContext);
        } else {
            pauseOverlay.draw(canvas, graphicsContext);
        }

        LOGGER.trace("UI elements drawn.");
    }
}
