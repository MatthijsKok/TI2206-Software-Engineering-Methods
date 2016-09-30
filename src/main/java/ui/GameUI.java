package ui;

import game.Game;
import game.GameState;
import level.Level;
import util.logging.Logger;

/**
 * Takes care of the total UI of a game instance.
 */
public class GameUI extends UIElement {

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getInstance();

    /**
     * The game to draw the ui for.
     */
    private Game game;
    /**
     * The overlay for when a level is won.
     */
    private LevelWonOverlay levelWonOverlay = new LevelWonOverlay();
    /**
     * The overlay for when a level is lost.
     */
    private LevelLostOverlay levelLostOverlay = new LevelLostOverlay();
    /**
     * The overlay for when the game is paused.
     */
    private PauseOverlay pauseOverlay = new PauseOverlay();
    /**
     * The hud for when playing the game.
     */
    private HUD hud;

    /**
     * Creates a new GameUI instance handling the UI of Game game.
     *
     * @param game the game to draw the ui for.
     */
    public GameUI(Game game) {
        this.game = game;

        init();
    }

    /**
     * Initializes the ui elements in a level.
     */
    private void init() {
        switch (game.getPlayerCount()) {
            case 1:
                hud = new SinglePlayerHUD();
                break;
            case 2:
                hud = new MultiPlayerHUD();
                break;
            default:
                hud = new HUD();
        }
    }

    /**
     * Draws all ui elements in the game.
     */
    public void draw() {
        LOGGER.trace("Drawing UI elements...");

        GameState state = game.getState();
        Level level = state.getCurrentLevel();

        if (level.won()) {
            levelWonOverlay.draw();
        } else if (level.lost()) {
            levelLostOverlay.draw();
        } else if (state.isInProgress()) {
            hud.draw();
        } else {
            pauseOverlay.draw();
        }

        LOGGER.trace("UI elements drawn.");
    }
}
