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
     * Defines how many nano seconds there are in one second.
     */
    private static final double NANO_SECONDS_IN_SECOND = 1000000000.0;

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
     * The logger of the game.
     */
    private static final Logger LOGGER = new Logger();

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
        }
        return gameInstance;
    }

    /**
     * Loads and starts the first level.
     */
    public void start() {
        lastNanoTime = System.nanoTime();

        currentLevel = levels.get(0);
        currentLevel.start();
    }

    /**
     * Updates the game.
     */
    public void update() {
        long currentNanoTime = System.nanoTime();

        //gives the time difference in seconds
        double dt = (currentNanoTime - lastNanoTime) / NANO_SECONDS_IN_SECOND;

        lastNanoTime = currentNanoTime;
        currentLevel.update(dt);

        if (keyboard.keyPressed("R")
                && (levelWon() || levelLost())) {
            getCurrentLevel().restart();
        }

        LOGGER.writeLogRecords();
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
