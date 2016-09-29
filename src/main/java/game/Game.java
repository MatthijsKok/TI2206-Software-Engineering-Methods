package game;

import level.Level;
import game.player.Player;
import game.player.PlayerFactory;
import util.KeyboardInputManager;
import util.logging.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that handles things on a game related level.
 */
public class Game {

    /**
     * The list containing the default level files in the game.
     */
    private static final List<String> DEFAULT_LEVELS = new ArrayList<>();

    static {
        DEFAULT_LEVELS.add("level.txt");
    }

    /**
     * The logger access point to which everything will be logged.
     */
    private static final Logger LOGGER = Logger.getInstance();

    /**
     * Defines how many nano seconds there are in one second.
     */
    private static final double NANO_SECONDS_IN_SECOND = 1000000000.0;

    /**
     * Defines the maximum timespan a frame can simulate.
     */
    private static final double MAX_FRAME_DURATION = 0.033333333;

    /**
     * The one and only instance of the game object.
     */
    private static Game gameInstance = null;

    /**
     * A list containing all the players that play the game.
     */
    private List<Player> players = new ArrayList<>();

    /**
     * A list containing all the levels in the game.
     */
    private List<Level> levels = new ArrayList<>();

    /**
     * The current level of the game.
     */
    private Level currentLevel;

    /**
     * The last time recorded.
     */
    private long lastNanoTime;

    /**
     * The keyboard manager of the game.
     */
    private KeyboardInputManager keyboard = KeyboardInputManager.getInstance();

    /**
     * Boolean indicating if the game is in progress.
     */
    private boolean inProgress = true;

    /**
     * Creates a new game with a set of levels.
     * @param levelFiles The set of level files to play.
     * @param players Number of players.
     */
    protected Game(List<String> levelFiles, int players) {
        for (String levelFile : levelFiles) {
            levels.add(new Level(levelFile));
        }

        createPlayers(players);
    }

    /**
     * Creates a new instance of a game if there is not one yet created and return that instance.
     *
     * @return a Game instance.
     */
    public static Game getInstance() {
        if (gameInstance == null) {
            gameInstance = new Game(DEFAULT_LEVELS, 2);
            LOGGER.trace("New game instance created.");
        }
        return gameInstance;
    }

    /**
     * Creates the players.
     * @param players the amount of players.
     */
    private void createPlayers(int players) {
        for (int i = 0; i < players; i++) {
            this.players.add(PlayerFactory.createPlayer(i));
        }
    }

    /**
     * @return the amount of players in the game.
     */
    public int getPlayerCount() {
        return players.size();
    }

    /**
     * Gets the player with id id.
     * @param id the id specifying the player.
     * @return The player instance with id id.
     */
    public Player getPlayer(int id) {
        return players.get(id);
    }

    /**
     * Gets the players in the game.
     * @return The list of players in the game.
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Loads and starts the first level.
     */
    public void start() {
        currentLevel = levels.get(0);
        LOGGER.info("Starting level...");
        currentLevel.start();
        LOGGER.info("level started.");
        lastNanoTime = System.nanoTime();
    }

    /**
     * Updates the game.
     */
    public void update() {
        if (inProgress) {
            long currentNanoTime = System.nanoTime();

            //gives the time difference in seconds
            double dt = Math.min(
                    (currentNanoTime - lastNanoTime) / NANO_SECONDS_IN_SECOND,
                    MAX_FRAME_DURATION);
            LOGGER.trace("Time difference since last update: " + dt + " seconds.");

            lastNanoTime = currentNanoTime;
            currentLevel.update(dt);

            if (keyboard.keyPressed("R")
                    && (levelWon() || levelLost())) {
                LOGGER.info("Restarting level...");
                getCurrentLevel().restart();
            }

            LOGGER.trace("Writing LogRecords...");
            LOGGER.writeLogRecords();
            LOGGER.trace("LogRecords written.");
        }
    }

    /**
     * Draws the current level.
     */
    public void draw() {
        currentLevel.draw();
    }

    /**
     * @return Returns a list of all levels in the game
     */
    public List<Level> getLevels() {
        return levels;
    }

    /**
     * @return Returns the level curently in play
     */
    public Level getCurrentLevel() {
        return currentLevel;
    }

    /**
     * @return Returns whether the current level is won
     */
    public boolean levelWon() {
        return getCurrentLevel().won();
    }

    /**
     * @return Returns whether the current level is lost
     */
    public boolean levelLost() {
        return getCurrentLevel().lost();
    }

    /**
     * Pauses the game.
     */
    public void pause() {
        inProgress = false;
    }

    /**
     * Pauses the game.
     */
    public void resume() {
        inProgress = true;
    }
}
