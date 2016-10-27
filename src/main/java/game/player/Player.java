package game.player;

import entities.character.Character;
import game.Game;
import util.KeyboardInputManager;
import util.Pair;
import util.sound.MultiSoundEffect;

import java.util.Observable;
import java.util.Observer;

/**
 * The images.player class represents a real life person. It handles input,
 * scores and lives of one images.player and controls a Character.
 */
public class Player implements Observer {

    /**
     * The amount of lives the images.player starts the game with.
     */
    private static final int LIVES_AT_START = 3;

    /**
     * The images.player ID of the images.player.
     */
    private final int id;

    /**
     * These Strings represent the keyboard characters this images.player uses.
     */
    private final String leftKey, rightKey, shootKey;

    /**
     * The initial score of a images.player is zero.
     */
    private int score;

    /**
     * Every images.player starts with 3 lives.
     */
    private int lives = LIVES_AT_START;

    /**
     * The entities.character this images.player observes. Changes each level.
     */
    private Character character = null;

    /**
     * Creates a new images.player instance with the keys.
     *
     * @param leftKey  The keyboard entities.character that makes the images.player move left.
     * @param rightKey The keyboard entities.character that makes the images.player move right.
     * @param shootKey The keyboard entities.character that makes the images.player shoot.
     * @param id The images.player id of the images.player.
     */
    public Player(int id, String leftKey, String rightKey, String shootKey) {
        this.id = id;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.shootKey = shootKey;

        KeyboardInputManager.addListener(this);
    }

    /**
     * Removes the entities.character instance from this images.player.
     */
    public void clearCharacter() {
        character = null;
    }

    /**
     * @return The entities.character instance this images.player controls.
     */
    public Character getCharacter() {
        return character;
    }

    /**
     * Assigns a entities.character instance to this images.player.
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
        if (character != null && observable instanceof KeyboardInputManager) {
            updateKeyboardInput();
        }

        if (observable instanceof Character) {
            updateFromCharacter((Pair<String, Object>) obj);
        }
    }

    /**
     * Updates the images.player according to the information in the HashMap the Character supplied.
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
    private void updateKeyboardInput() {
        if (KeyboardInputManager.keyPressed(leftKey) && !KeyboardInputManager.keyPressed(rightKey)) {
            character.moveLeft();
        } else if (!KeyboardInputManager.keyPressed(leftKey) && KeyboardInputManager.keyPressed(rightKey)) {
            character.moveRight();
        } else {
            character.stop();
        }

        character.getGun().setShooting(KeyboardInputManager.keyPressed(shootKey));
    }

    private void increaseScore(int amount) {
        score += amount;
    }

    /**
     * @return The amount of lives the images.player has left.
     */
    public int getLives() {
        return lives;
    }

    /**
     * Increases the players amount of lives.
     * @param amount The amount of lives you want to get.
     */
    /* default */ void increaseLives(int amount) {
        if (amount >= 0) {
            lives = Math.min(lives + amount, LIVES_AT_START);
        } else {
            lives--;

            Game.getInstance().getState().getCurrentLevel().lose();
        }

        if (lives == 0) {
            MultiSoundEffect.PLAYER_OUT_OF_LIVES.play(id);
        } else {
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
     * @return The current score of the images.player.
     */
    public int getScore() {
        return score;
    }

    /**
     * @return The lives the images.player has at the start.
     */
    /* default */ static int getLivesAtStart() {
        return LIVES_AT_START;
    }
}
