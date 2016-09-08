package game;

import java.util.ArrayList;

public class Game {
	private ArrayList<Level> levels;
	private Level currentLevel;

    private long lastNanoTime, currentNanoTime;
	private static Game gameInstance = null;

    private Game() {
		levels = new ArrayList<Level>();
		levels.add(new Level());
		currentLevel = levels.get(0);

        lastNanoTime = System.nanoTime();
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
     * Updates the game
     */
	public void update() {
        currentNanoTime = System.nanoTime();
        double dt = (currentNanoTime - lastNanoTime) / 1000000000.0;
        lastNanoTime = currentNanoTime;
		currentLevel.update(dt);
	}
	
	public void draw() {
		currentLevel.draw();
	}

    /**
     * @return Return the level curently in play
     */
    public Level getCurrentLevel() {
        return currentLevel;
    }
}


/*

Image background = new Image("resources/background.jpg");
Image player     = new Image("resources/player.png");
Image wall       = new Image("resources/wall.png");


//double x = 256 + 128 * Math.cos(t);
//double y = 512 - 64;

// background image clears canvas
/*gc.setFill(Color.ALICEBLUE);
gc.fillRect(0, 0, 512, 512);

gc.save();
gc.scale(0.5, 0.5);
gc.drawImage(background, 0, 0);
gc.restore();

for (int i = 0; i < 512/64; i++) {
	gc.drawImage(wall, i*64, 512 - 64);
}

gc.save();
gc.scale(0.2, 0.2);
gc.drawImage(player, (x - player.getWidth()*0.1)/0.2, (y - player.getHeight()*0.2)/0.2);
gc.fillRect(x/0.2, y/0.2, 10, 10);
gc.restore();*/