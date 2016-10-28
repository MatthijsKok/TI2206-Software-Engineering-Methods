package game.state;

import entities.balls.AbstractBall;
import entities.character.Character;
import game.Game;
import game.player.Player;
import level.Level;
import level.LevelTimer;
import util.KeyboardInputManager;
import util.SceneManager;
import util.logging.Logger;
import util.sound.Music;
import util.sound.SoundEffect;

import java.io.IOException;

/**
 * State for when the game is in progress.
 */
public class InProgressState implements GameState {

    /**
     * The key which resumes the game.
     */
    private static final String PAUSE_KEY = "P";

    /**
     * The level this state is for.
     */
    private final Level level;
    /**
     * The timer of the level this state is for.
     */
    private final LevelTimer timer;

    /**
     * Creates a new PausedState.
     * @param level Level - The level that is paused.
     */
    public InProgressState(Level level) {
        this.level = level;
        this.timer = level.getTimer();
        SceneManager.removeOverlay();
    }

    @Override
    public void update(double timeDifference) {
        Game.getPlayers().forEach(Player::updateKeyboardInput);

        level.update(timeDifference);

        if (KeyboardInputManager.keyPressed(PAUSE_KEY)) {
            SoundEffect.PAUSE.play();
            Music.pauseMusic();
            Game.setState(new PausedState(level));
        }

        try {
            checkExitConditions();
        } catch (IOException e) {
            Logger.getInstance().fatal(e.getMessage());
            Game.stop();
        }
    }

    private void checkExitConditions() throws IOException {
        if (timer.getTimeLeft() < 0) {
            Game.loseLevel(true);
        }

        long balls = level.getEntities().stream()
                .filter(entity -> entity instanceof AbstractBall)
                .count();

        if (balls == 0) {
            Game.winLevel();
        }

        long deadCharacters = level.getEntities().stream()
                .filter(entity -> entity instanceof Character)
                .filter(entity -> !((Character) entity).isAlive())
                .count();

        if (deadCharacters > 0) {
            Game.loseLevel(false);
        }
    }
}
