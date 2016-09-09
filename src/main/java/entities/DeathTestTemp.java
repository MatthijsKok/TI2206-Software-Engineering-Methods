package entities;

import util.Sprite;

/**
 * Created by dana on 09/09/2016.
 */
public class DeathTestTemp extends Entity {
    private static Sprite SPRITE = new Sprite("DEATHTEST.png");

    public DeathTestTemp() {
        this(0, 0);
    }

    public DeathTestTemp(double x, double y) {
        super(x, y);
        sprite = DeathTestTemp.SPRITE;
    }
}