package entities.character;

/**
 * Class which handles a character's movement.
 */
public class CharacterMovement {

    /**
     * The default run speed of a character.
     */
    private static final double DEFAULT_RUN_SPEED = 230; // px/s
    /**
     * The character this instance handles the movement for.
     */
    private final transient Character character;
    /**
     * The running speed of a character. In pixels per second.
     */
    private transient double runSpeed = DEFAULT_RUN_SPEED;
    /**
     * State of the character, indicates which action a character
     * is performing.
     */
    private transient int direction;

    /**
     * Creates a new CharacterMovement object.
     *
     * @param character Character - The character to handle
     *                  movement for.
     */
    CharacterMovement(final Character character) {
        this.character = character;
    }

    /**
     * Handles the character's movement.
     */
    /* default */ final void update() {
        character.setXSpeed(runSpeed * direction);
    }

    /**
     * Makes the character stop moving.
     */
    public void stop() {
        this.direction = 0;
    }

    /**
     * Makes the character move to the left.
     */
    public void moveLeft() {
        this.direction = -1;
    }

    /**
     * Makes the character move to the right.
     */
    public void moveRight() {
        this.direction = 1;
    }

    /**
     * Increases the speed at which the character runs.
     *
     * @param amount Double - The speed boost.
     */
    public void increaseRunSpeed(final double amount) {
        this.runSpeed += amount;
    }

    /**
     * Getter for runSpeed.
     *
     * @return Double - runSpeed
     */
    public double getRunSpeed() {
        return runSpeed;
    }

    /**
     * @return Boolean - Whether the character is idle or not.
     */
    /* default */ boolean isIdle() {
        return direction == 0;
    }

    /**
     * Getter for the direction.
     *
     * @return Integer - Movement direction.
     */
    /* default */ int getDirection() {
        return direction;
    }
}
