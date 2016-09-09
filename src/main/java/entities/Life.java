package entities;

import util.Sprite;

/**
 * Created by dana on 09/09/2016.
 */
public class Life extends Entity {
    private static Sprite SPRITE = new Sprite("life.png");

    public Life() {
        this(0, 0);
    }

    public Life(double x, double y) {
        super(x, y);
        sprite = Life.SPRITE;
    }
}
