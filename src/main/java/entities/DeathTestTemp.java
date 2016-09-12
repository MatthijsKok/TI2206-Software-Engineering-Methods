package entities;

import util.Sprite;

/**
 * Created by dana on 09/09/2016.
 */
public class DeathTestTemp extends Entity {
    private static Sprite SPRITE = new Sprite("DEATHTEST.png");

    private double runSpeed  = 100; // px/s
    private double jumpSpeed = 256; // px/s
    private double gravity   = 400; // px/s^2
    private boolean goLeft = true;

    public DeathTestTemp() {
        this(0, 0);
    }

    public DeathTestTemp(double x, double y) {
        super(x, y);
        sprite = DeathTestTemp.SPRITE;
    }

    public void update(double dt) {
        //Check if ball should go left or right
        if(this.position.x == 32){
            goLeft = false;
        }else if(this.position.x == 960){
            goLeft = true;
        }

        // Set speed
        this.speed.x = 0;
        //go left or right depending on goLeft (boolean)
        if(goLeft) {
            this.speed.x -= runSpeed;
        } else {
            this.speed.x += runSpeed;
        }

        this.speed.y += gravity*dt;

        // Jump
        if (this.position.y >= 512) {
            this.speed.y = -jumpSpeed;
        }

        // Move
        this.position.x += this.speed.x*dt;
        this.position.y += this.speed.y*dt;

        // Left boundary
        if (this.position.x <= 32) {
            this.position.x = 32;
            this.speed.x = Math.max(0, this.speed.x);
        }

        // Right boundary
        if (this.position.x >= 960) {
            this.position.x = 960;
            this.speed.x = Math.min(this.speed.x, 0);
        }

        // Bottom boundary
        if (this.position.y >= 512) {
            this.position.y = 512;
            this.speed.y = Math.min(this.speed.y, 0);
        }
    }
}