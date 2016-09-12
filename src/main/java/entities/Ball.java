package entities;

import com.sun.javafx.geom.Vec2d;
import game.Game;
import game.Level;
import util.Sprite;

/**
 * Class that represents the bouncing balls in our game.
 */
public class Ball extends Entity {

    // Sprites for the balls.
    private static final Sprite TINY_BALL_SPRITE = new Sprite("balls/tiny_ball.png", new Vec2d(8, 8));
    private static final Sprite SMALL_BALL_SPRITE = new Sprite("balls/small_ball.png", new Vec2d(16, 16));
    private static final Sprite MEDIUM_BALL_SPRITE = new Sprite("balls/medium_ball.png", new Vec2d(32, 32));
    private static final Sprite LARGE_BALL_SPRITE = new Sprite("balls/large_ball.png", new Vec2d(64, 64));
    private static final Sprite HUGE_BALL_SPRITE = new Sprite("balls/huge_ball.png", new Vec2d(128, 128));

    // Bounce speed for the different ball sizes.
    private static final double TINY_BALL_BOUNCE_SPEED = 225;
    private static final double SMALL_BALL_BOUNCE_SPEED = 300;
    private static final double MEDIUM_BALL_BOUNCE_SPEED = 375;
    private static final double LARGE_BALL_BOUNCE_SPEED = 450;
    private static final double HUGE_BALL_BOUNCE_SPEED = 500;

    private static final double BALL_MOVE_SPEED = 100; // Horizontal move speed
    private static final double gravity = 300; // px/s^2

    private int ballSize; // Integer that represents the ball size from 0 (tiny) to 4 (huge).

    /**
     * Constructor for the bouncing balls in our game.
     * @param position A Vec2D of the starting position of the ball.
     * @param left A boolean that is set to 1 if the ball initially moves left, 0 if right.
     * @param ballSize An integer ranging 0-4 representing the ball size.
     */
    public Ball(Vec2d position, boolean left, int ballSize) {
        this.setPosition(position);
        if (left) {
            this.setSpeed(-BALL_MOVE_SPEED, 0);
        } else {
            this.setSpeed(BALL_MOVE_SPEED, 0);
        }
        this.setBallSize(ballSize);
        switch (ballSize) {
            case 0: sprite = Ball.TINY_BALL_SPRITE;
                break;
            case 1: sprite = Ball.SMALL_BALL_SPRITE;
                break;
            case 2: sprite = Ball.MEDIUM_BALL_SPRITE;
                break;
            case 3: sprite = Ball.LARGE_BALL_SPRITE;
                break;
            case 4: sprite = Ball.HUGE_BALL_SPRITE;
                break;
        }
    }

    /**
     * Sets the ball size. Method only used internally.
     * @param ballSize Integer from 0 (tiny ball) to 4 (huge ball).
     */
    private void setBallSize(int ballSize) {
        this.ballSize = ballSize;
    }

    /**
     * Gets the ball size.
     * @return Integer from 0 (tiny ball) to 4 (huge ball).
     */
    public int getBallSize() { return this.ballSize; }

    /**
     * Removes this ball from the Level and adds two smaller balls on the same position, moving in different directions.
     * If the ball is already at it's smallest, no new balls will be added.
     */
    public void split() {
        Level level = Game.getInstance().getCurrentLevel();
        if (this.ballSize != 0) {
            level.addEntity(new Ball(this.position, true, this.ballSize - 1));
            level.addEntity(new Ball(this.position, false, this.ballSize - 1));
        }
        level.removeEntity(this);
    }

    /**
     * Update the speed and position of the ball.
     * @param dt Time difference from previous update in seconds.
     */
    public void update(double dt) {
        // Left boundary
        if (this.position.x <= 64) {
            this.position.x = 64;
            this.speed.x = BALL_MOVE_SPEED;
        }
        // Right boundary
        if (this.position.x >= 992) {
            this.position.x = 992;
            this.speed.x = -BALL_MOVE_SPEED;
        }
        // Update the gravity to vertical speed.
        this.speed.y += gravity*dt;
        // Bottom boundary
        if (this.position.y >= 544) {
            this.position.y = 544;
            switch (ballSize) {
                case 0: this.speed.y = -TINY_BALL_BOUNCE_SPEED;
                    break;
                case 1: this.speed.y = -SMALL_BALL_BOUNCE_SPEED;
                    break;
                case 2: this.speed.y = -MEDIUM_BALL_BOUNCE_SPEED;
                    break;
                case 3: this.speed.y = -LARGE_BALL_BOUNCE_SPEED;
                    break;
                case 4: this.speed.y = -HUGE_BALL_BOUNCE_SPEED;
                    break;
            }
        }
        // Move
        this.position.x += this.speed.x*dt;
        this.position.y += this.speed.y*dt;
    }
}
