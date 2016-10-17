package game.player;

import entities.Ball;
import entities.Character;
import entities.Harpoon;
import game.Game;
import util.KeyboardInputManager;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 * The player class represents a real life person. It handles input,
 * scores and lives of one player and controls a Character.
 */
public class Player implements Observer {

    /**
     * The amount of lives the player starts the game with.
     */
    private static final int LIVES_AT_START = 3;

    /**
     * Score that is multiplied by the size of the ball, and then added to the score.
     */
    private static final int SCORE_PER_BALL = 100;

    /**
     * The player ID of the player.
     */
    private int id = 0;

    /**
     * These Strings represent the keyboard characters this player uses.
     */
    private final String leftKey, rightKey, shootKey;

    /**
     * The initial score of a player is zero.
     */
    private int score = 0;

    /**
     * Every player starts with 3 lives.
     */
    private int lives = LIVES_AT_START;

    /**
     * The character this player observes. Changes each level.
     */
    private Character character = null;

    /**
     * Creates a new player instance with the keys.
     *
     * @param leftKey  The keyboard character that makes the player move left.
     * @param rightKey The keyboard character that makes the player move right.
     * @param shootKey The keyboard character that makes the player shoot.
     * @param id The player id of the player.
     */
    public Player(int id, String leftKey, String rightKey, String shootKey) {
        this.id = id;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.shootKey = shootKey;

        KeyboardInputManager.addListener(this);
    }

    /**
     * Removes the character instance from this player.
     */
    public void clearCharacter() {
        character = null;
    }

    /**
     * @return The character instance this player controlls.
     */
    public Character getCharacter() {
        return character;
    }

    /**
     * Assigns a character instance to this player.
     *
     * @param character the character to assign.
     */
    public void setCharacter(Character character) {
        if (character != null) {
            this.character = character;
            character.addObserver(this);
            character.getHarpoon().addObserver(this);
        }
    }

    /**
     * Entry point for observations.
     *
     * @param observable The observable that is updated.
     * @param obj        The changes that are observed.
     */
    public void update(Observable observable, Object obj) {
        if (character != null && observable instanceof KeyboardInputManager) {
            updateKeyboardInput();
        }

        if (observable instanceof Character) {
            updateFromCharacter((HashMap) obj);
        }

        if (observable instanceof Harpoon) {
            updateFromRope((Ball) obj);
        }
    }

    private void updateFromRope(Ball ball) {
        int ballSize = ball.getSize();
        // the smallest balls have size 0
        score += (ballSize + 1) * SCORE_PER_BALL;
    }

    /**
     * Updates the player according to the information in the HashMap the Character supplied.
     *
     * @param hashMap The hashmap with information about the changed state of the Character object.
     */
    private void updateFromCharacter(HashMap hashMap) {
        Game game = Game.getInstance();

        if (hashMap.get("dead").equals(true)) {
            if (lives > 0) {
                lives--;
                game.getState().getCurrentLevel().lose();
            }

            boolean lost = true;
            for (Player player : game.getPlayers()) {
                if (player.getLives() > 0) {
                    lost = false;
                    break;
                }
            }

            if (lost) {
                game.getState().lose();
            }
        }

    }

    /**
     * Handles keyboard input and passes it to the character.
     */
    private void updateKeyboardInput() {
        if (KeyboardInputManager.keyPressed(leftKey) && !KeyboardInputManager.keyPressed(rightKey)) {
            character.moveLeft();
        } else if (!KeyboardInputManager.keyPressed(leftKey) && KeyboardInputManager.keyPressed(rightKey)) {
            character.moveRight();
        } else {
            character.stop();
        }

        character.setShooting(KeyboardInputManager.keyPressed(shootKey));
    }

    /**
     * @return The amount of lives the player has left.
     */
    public int getLives() {
        return lives;
    }

    /**
     * Increases the players amount of lives.
     * @param amount The amount of lives you want to get.
     */
    public void increaseLives(int amount) {
        lives = Math.min(lives + amount, LIVES_AT_START);
    }

    /**
     * Resets the players lives.
     */
    public void resetLives() {
        lives = LIVES_AT_START;
    }

    /**
     * @return The current score of the player.
     */
    public int getScore() {
        return score;
    }

    /**
     * @return The lives the player has at the start.
     */
    static int getLivesAtStart() {
        return LIVES_AT_START;
    }

    /**
     * @return The id of the player.
     */
    public int getId() {
        return id;
    }
}
