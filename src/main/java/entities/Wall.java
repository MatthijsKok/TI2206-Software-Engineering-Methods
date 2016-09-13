package entities;

import geometry.Rectangle;
import util.Sprite;

/**
 * Created by dana on 09/09/2016.
 */
public class Wall extends Entity {
    private static Sprite SPRITE = new Sprite("wall.png");

    public Wall() {
        this(0, 0);
    }

    public Wall(double x, double y) {
        super(x, y);
        sprite = Wall.SPRITE;
        shape = new Rectangle(sprite.getWidth(), sprite.getHeight());
        shape.setPosition(x, y);
    }

    public double getLeft() {
        return getX();
    }

    public double getRight() {
        return getX() + sprite.getWidth();
    }
}