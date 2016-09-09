package entities;

import util.Sprite;

/**
 * Created by dana on 09/09/2016.
 */
public class WallBlock extends Entity {
    private static Sprite SPRITE = new Sprite("WallBlock.png");

    public WallBlock() {
        this(0, 0);
    }

    public WallBlock(double x, double y) {
        super(x, y);
        sprite = WallBlock.SPRITE;
    }
}