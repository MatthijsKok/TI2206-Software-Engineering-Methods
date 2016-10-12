package game;

import game.player.Player;
import game.player.PlayerFactory;
import javafx.animation.AnimationTimer;
import level.Level;
import ui.GameUI;
import util.CanvasManager;
import util.logging.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A class that handles things on a game related level.
 */
public class Game {

    /**
     * The logger access point to which everything will be logged.
     */
    private static final Logger LOGGER = Logger.getInstance();
    /**
     * Defines how many nano seconds there are in one second.
     */
    private static final double NANO_SECONDS_IN_SECOND = 1000000000.0;
    /**
     * Defines the maximum time span a frame can simulate.
     */
    private static final double MAX_FRAME_DURATION = 0.033333333;
    /**
     * The one and only instance of the game object.
     */
    private static Game instance = null;

    /**
     * The state of the game.
     */
    private GameState state;
    /**
     * The UI of the game.
     */
    private GameUI ui;
    /**
     * A list containing all the players that play the game.
     */
    private List<Player> players = new ArrayList<>();
    /**
     * A list containing all the levels in the game.
     */
    private List<Level> levels = new ArrayList<>();
    /**
     * The last time recorded.
     */
    private long lastNanoTime;
    /**
     * The timer that handles the main update loop.
     */
    private AnimationTimer timer;

    /**
     * Creates an empty new game.
     */
    public Game() {
        state = new GameState(this);
        setUpAnimationLoop();
    }

    /**
     * Creates a new instance of a game if there is not one yet created and return that instance.
     *
     * @return a Game instance.
     */
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
            LOGGER.trace("New game instance created.");
        }
        return instance;
    }

    private void setUpAnimationLoop() {
        timer = new AnimationTimer() {
            public void handle(final long currentNanoTime) {
                update();
                draw();
            }
        };
    }

    /**
     * Removes the old players and creates new ones.
     *
     * @param count the amount of players.
     */
    public void setPlayerCount(int count) {
        players.forEach(Player::clearCharacter);
        players.clear();

        for (int i = 0; i < count; i++) {
            this.players.add(PlayerFactory.createPlayer(i));
        }

        ui = new GameUI(this);
    }

    /**
     * @return the amount of players in the game.
     */
    public int getPlayerCount() {
        return players.size();
    }

    /**
     * Gets the player with id id.
     *
     * @param id the id specifying the player.
     * @return The player instance with id id.
     */
    public Player getPlayer(int id) {
        return players.get(id);
    }

    /**
     * Gets the players in the game.
     *
     * @return The list of players in the game.
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Creates new levels from a list of level files.
     * @param levelFiles the list of files to create levels from.
     */
    public void setLevelsFromFiles(List<String> levelFiles) {
        setLevels(levelFiles.stream().map(Level::new).collect(Collectors.toList()));
    }

    /**
     * Sets the game's levels to these levels.
     * @param levels the list of levels.
     */
    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }

    /**
     * @return Returns a list of all levels in the game.
     */
    List<Level> getLevels() {
        return levels;
    }

    /**
     * @return Returns the state of the game.
     */
    public GameState getState() {
        return state;
    }

    /**
     * Loads and starts the first level.
     */
    public void start() {
        LOGGER.info("Starting level...");
        state.getCurrentLevel().load();
        timer.start();
        state.resume();
        LOGGER.info("level started.");
        lastNanoTime = System.nanoTime();
        CanvasManager.getCanvas().setVisible(true);
    }

    /**
     * Stops the game and returns to the main menu.
     */
    void stop() {
        state.reset();
        timer.stop();
        CanvasManager.getCanvas().setVisible(false);
    }

    /**
     * Updates the game.
     */
    private void update() {
        LOGGER.debug("Updating the game...");
        long currentNanoTime = System.nanoTime();

        //gives the time difference in seconds
        double dt = Math.min(
                (currentNanoTime - lastNanoTime) / NANO_SECONDS_IN_SECOND,
                MAX_FRAME_DURATION);
        LOGGER.trace("Time difference since last update: " + dt + " seconds.");

        lastNanoTime = currentNanoTime;

        if (state.isInProgress()) {
            state.getCurrentLevel().update(dt);
        }

        LOGGER.writeLogRecords();
    }

    /**
     * Draws the current level.
     */
    private void draw() {
        LOGGER.debug("Drawing the game...");
        state.getCurrentLevel().draw();

        ui.draw(CanvasManager.getCanvas(), CanvasManager.getContext());
    }
}
