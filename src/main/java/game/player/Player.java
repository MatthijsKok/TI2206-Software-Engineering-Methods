package game.player;

import entities.Ball;
import entities.Character;
import entities.Rope;
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
    private static final int BALL_SCORE = 100;

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
     * @param leftKey The keyboard character that makes the player move left.
     * @param rightKey The keyboard character that makes the player move right.
     * @param shootKey The keyboard character that makes the player shoot.
     */
    public Player(String leftKey, String rightKey, String shootKey) {
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.shootKey = shootKey;
    }

    /**
     * Assigns a character instance to this player.
     * @param character the character to assign.
     */
    public void setCharacter(Character character) {
        if (character != null) {
            this.character = character;
            character.addObserver(this);
            character.getRope().addObserver(this);
        }
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
     * Entry point for observations.
     * @param observable The observable that is updated.
     * @param obj The changes that are observed.
     */
    public void update(Observable observable, Object obj) {
        if (character != null && observable instanceof KeyboardInputManager) {
            update((KeyboardInputManager) observable);
        }

        if (observable instanceof Character) {
            updateFromCharacter((HashMap) obj);
        }

        if (observable instanceof Rope) {
            updateFromRope((Ball) obj);
        }
    }

    private void updateFromRope(Ball ball) {
        int ballSize = ball.getSize();
        // the smallest balls have size 0
        score += (ballSize + 1) * BALL_SCORE;
    }

    /**
     * Updates the player according to the information in the HashMap the Character supplied.
     * @param hashMap The hashmap with information about the changed state of the Character object.
     */
    private void updateFromCharacter(HashMap hashMap) {
        if (hashMap.get("dead").equals(true)) {
            if (lives > 0) {
                lives--;
                Game.getInstance().getState().pause();
            }
        }

    }

    /**
     * Handles the input and passes it to the character.
     * @param kim KeyboardInputManager to take input from.
     */
    private void update(KeyboardInputManager kim) {
        if (kim.keyPressed(leftKey) && !kim.keyPressed(rightKey)) {
            character.moveLeft();
        } else if (!kim.keyPressed(leftKey) && kim.keyPressed(rightKey)) {
            character.moveRight();
        } else {
            character.stop();
        }

        character.setShooting(kim.keyPressed(shootKey));
    }

    /**
     * @return The amount of lives the player has left.
     */
    public int getLives() {
        return lives;
    }

    /**
     * @return The current score of the player.
     */
    public int getScore() {
        return score;
    }
}
