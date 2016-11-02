package game.player;

import entities.character.Character;
import entities.character.CharacterMovement;
import entities.character.Gun;
import util.KeyboardInputManager;
import util.Pair;
import util.sound.MultiSoundEffect;

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
     * The amount of lives the player can maximally have.
     */
    private static final int MAX_LIVES = 99;

    /**
     * The ID of the player.
     */
    private final int id;

    /**
     * These Strings represent the keyboard characters this player uses.
     */
    private final String leftKey, rightKey, shootKey;

    /**
     * The initial score of a player is zero.
     */
    private int score;

    /**
     * Every player starts with 3 lives.
     */
    private int lives = LIVES_AT_START;

    /**
     * The entities.character this player observes. Changes each level.
     */
    private Character character = null;

    /**
     * Creates a new player instance with the keys.
     *
     * @param leftKey  The keyboard key that makes the character move left.
     * @param rightKey The keyboard key that makes the character move right.
     * @param shootKey The keyboard key that makes the character shoot.
     * @param id The id of the player.
     */
    public Player(int id, String leftKey, String rightKey, String shootKey) {
        this.id = id;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.shootKey = shootKey;
    }

    /**
     * Removes the entities.character instance from this player.
     */
    public void clearCharacter() {
        character = null;
    }

    /**
     * @return The entities.character instance this player controls.
     */
    public Character getCharacter() {
        return character;
    }

    /**
     * Assigns a entities.character instance to this player.
     *
     * @param character the entities.character to assign.
     */
    public void setCharacter(Character character) {
        if (character != null) {
            this.character = character;
            character.setSprites(id);
            character.addObserver(this);
        }
    }

    /**
     * Entry point for observations.
     *
     * @param observable The observable that is updated.
     * @param obj        The changes that are observed.
     */
    @SuppressWarnings("unchecked")
    public void update(Observable observable, Object obj) {
        if (observable instanceof Character) {
            updateFromCharacter((Pair<String, Object>) obj);
        }
    }

    /**
     * Updates the player according to the information in the HashMap the Character supplied.
     *
     * @param pair The pair containing information about the changed state of the Character object.
     */
    private void updateFromCharacter(Pair<String, Object> pair) {
        switch (pair.getL()) {
            case "increaseLives":
                increaseLives((int) pair.getR());
                break;
            case "increaseScore":
                increaseScore((int) pair.getR());
                break;
            default:
                break;
        }
    }

    /**
     * Handles keyboard input and passes it to the character.
     */
    public void updateKeyboardInput() {
        if (character == null) {
            return;
        }

        Gun gun = character.getGun();
        CharacterMovement movement = character.getMovement();

        if (KeyboardInputManager.keyDown(leftKey) && !KeyboardInputManager.keyDown(rightKey)) {
            movement.moveLeft();
        } else if (!KeyboardInputManager.keyDown(leftKey) && KeyboardInputManager.keyDown(rightKey)) {
            movement.moveRight();
        } else {
            movement.stop();
        }

        gun.setShooting(KeyboardInputManager.keyDown(shootKey));
    }

    private void increaseScore(int amount) {
        score += amount;
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
        //Lives cannot be below 0 and cannot be higher than the maximum amount of lives
        lives = Math.max(0, Math.min(lives + amount, MAX_LIVES));

        if (lives == 0) {
            MultiSoundEffect.PLAYER_OUT_OF_LIVES.play(id);
        } else if (amount < 0) {
            MultiSoundEffect.PLAYER_LOSES_LIFE.play(id);
        }
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
    /* default */ static int getLivesAtStart() {
        return LIVES_AT_START;
    }
}
