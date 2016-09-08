package entities;

import com.sun.javafx.geom.Vec2d;
import game.Game;
import util.KeyboardInputManager;
import util.Sprite;

/**
 * Rope class, controlling the rope in the game.
 */
public class Rope extends Entity {

    /**
     * Key used for shooting. Will be SPACE if not defined otherwise.
     */
    public String shootKey = "SPACE";

    /**
     * Sprite of the rope.
     */
    private static Sprite SPRITE = new Sprite("arrow.png", new Vec2d(30, 64));

    /**
     * Keyboard manager used for gathering user input.
     */
    private static KeyboardInputManager keyboard = KeyboardInputManager.getInstance();

    /**
     * Constant upward speed of the rope in px/s.
     */
    final private double ARROWSPEED = 10; // px/s

    /**
     * Boolean indicating if the rope is still traveling towards the top of the screen.
     */
    private boolean traveling = false;

    /**
     * Creates a new rope
     * @param x the x postiton of the rope
     * @param y the y position of the rope
     */
    public Rope(double x, double y) {
        super(x, y);
        sprite = Rope.SPRITE;
        visible = false;
    }

    /**
     * Updates the state of the rope.
     * @param dt delta time
     */
    public void update(double dt) {

        //if the arrow is not traveling, and the shoot key is pressed...
        if (!traveling) {
            if (keyboard.keyPressed(shootKey)) {
                //make the rope visible...
                visible = true;

                //set its traveling state to true...
                traveling = true;

                //and set the position of the rope to the position of the player
                position.y = Game.getInstance().getCurrentLevel().getPlayer().position.y;
                position.x = Game.getInstance().getCurrentLevel().getPlayer().position.x;
            }
        }

        // while the rope is traveling...
        if (traveling) {

            // move the arrow upwards with the speed of the arrow...
            position.y -= ARROWSPEED;

            // and if the rope has reached the top of the screen, change its traveling state to false.
            if(position.y <= 0) {
                traveling = false;
                visible = false;
        }
    }

}
}