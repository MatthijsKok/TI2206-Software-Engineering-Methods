package game.Player;

import entities.Character;
import util.KeyboardInputManager;

import java.util.Observable;
import java.util.Observer;

/**
 * The Player class represents a real life person. It handles input,
 * scores and lives of one player and controls a Character.
 */
public class Player implements Observer {

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
    private int lives = 3;

    /**
     * The character this Player observes. Changes each level.
     */
    private Character character = null;

    /**
     * Creates a new Player instance with the keys.
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
        this.character = character;
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
}
