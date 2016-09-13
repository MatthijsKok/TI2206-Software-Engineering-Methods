package game;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private List<Level> levels = new ArrayList<>();
	private Level currentLevel;

    private long lastNanoTime;
	private static Game gameInstance = null;

    protected Game() {
		levels.add(new Level("level.txt"));
	}

    /**
     * Creates a new instance of a game if there is not one yet created and return that instance.
     * @return a Game instance.
     */
    public static Game getInstance() {
        if (gameInstance == null){
            gameInstance = new Game();
        }
        return gameInstance;
    }

    /**
     * Loads and starts the first level
     */
    public void start() {
        lastNanoTime = System.nanoTime();

        currentLevel = levels.get(0);
        currentLevel.start();
    }

    /**
     * Updates the game
     */
	public void update() {
        long currentNanoTime = System.nanoTime();
        double dt = (currentNanoTime - lastNanoTime) / 1000000000.0;
        lastNanoTime = currentNanoTime;
		currentLevel.update(dt);
	}
	
	public void draw() {
		currentLevel.draw();
	}

    /**
     * @return Returns a list of all levels in the game
     */
	public List<Level> getLevels() { return levels; }

    /**
     * @return Return the level curently in play
     */
    public Level getCurrentLevel() {
        return currentLevel;
    }
}