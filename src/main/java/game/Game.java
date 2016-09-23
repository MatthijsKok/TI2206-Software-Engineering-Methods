package game;

import util.KeyboardInputManager;
import util.logging.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that handles things on a game related level.
 */
public class Game {

    /**
     * The logger access point to which everything will be logged.
     */
    private static final Logger LOGGER = new Logger();

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
     * Creates a new game.
     */
    protected Game() {
        levels.add(new Level("level.txt"));
    }

    /**
     * Creates a new instance of a game if there is not one yet created and return that instance.
     *
     * @return a Game instance.
     */
    public static Game getInstance() {
        if (gameInstance == null) {
            gameInstance = new Game();
            LOGGER.trace("New game instance created.");
        }
        return gameInstance;
    }

    /**
     * Loads and starts the first level.
     */
    public void start() {
        currentLevel = levels.get(0);
        LOGGER.info("Starting Level...");
        currentLevel.start();
        LOGGER.info("Level started.");
        lastNanoTime = System.nanoTime();
    }

    /**
     * Updates the game.
     */
    public void update() {
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
            LOGGER.info("Restarting game...");
            getCurrentLevel().restart();
        }

        LOGGER.trace("Writing LogRecords...");
        LOGGER.writeLogRecords();
        LOGGER.trace("LogRecords written.");
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
}
