package game;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;

public class Game {
	private ArrayList<Level> levels;
	private Level currentLevel;
	
	public Game() {
		levels = new ArrayList<Level>();
		levels.add(new Level());
		currentLevel = levels.get(0);
	}
	
	public void update(double timeDifference) {
		currentLevel.update(timeDifference);
	}
	
	public void draw(GraphicsContext gc) {
		currentLevel.draw(gc);
	}
}


/*

        Image background = new Image("resources/background.jpg");
        Image player     = new Image("resources/player.png");
        Image wall       = new Image("resources/wall.png");
        

/*for (Entity entity : entities) {
	entity.update(t);
}

for (Entity entity : entities) {
	entity.draw(gc);
}*/




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