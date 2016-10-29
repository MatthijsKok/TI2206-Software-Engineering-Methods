package game.state;

import entities.balls.AbstractBall;
import entities.character.Character;
import game.Game;
import game.player.Player;
import javafx.animation.AnimationTimer;
import level.Level;
import level.LevelTimer;
import util.KeyboardInputManager;
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
     * Defines how many nano seconds there are in one second.
     */
    private static final double NANO_SECONDS_IN_SECOND = 1000000000.0;
    /**
     * Defines the maximum time span a frame can simulate.
     */
    private static final double MAX_FRAME_DURATION = 0.033333333;

    /**
     * The last time recorded.
     */
    private long lastNanoTime;
    /**
     * The timer that handles the main update loop.
     */
    private final AnimationTimer timer;

    /**
     * The level this state is for.
     */
    private final Level level;
    /**
     * The timer of the level this state is for.
     */
    private final LevelTimer levelTimer;

    /**
     * Creates a new PausedState.
     * @param level Level - The level that is paused.
     */
    public InProgressState(Level level) {
        this.level = level;
        this.levelTimer = level.getTimer();
        GameStateHelper.setOverlay(null);

        timer = new AnimationTimer() {
            @Override
            public void handle(final long now) {
                update(now);
                lastNanoTime = now;
            }
        };

        timer.start();
    }

    private void update(final long now) {
        final double timeDifference = Math.min(
                (now - lastNanoTime) / NANO_SECONDS_IN_SECOND,
                MAX_FRAME_DURATION);

        Game.getPlayers().forEach(Player::updateKeyboardInput);

        level.update(timeDifference);

        Game.draw();

        if (KeyboardInputManager.keyPressed(PAUSE_KEY)) {
            SoundEffect.PAUSE.play();
            exit();
            Game.setState(new PausedState(level));
        }

        // Clear the keyboard
        KeyboardInputManager.update();

        try {
            checkExitConditions();
        } catch (IOException e) {
            Logger.getInstance().fatal(e.getMessage());
            Game.stop();
        }
    }

    private void checkExitConditions() throws IOException {
        if (levelTimer.getTimeLeft() < 0) {
            exit();
            Game.loseLevel(true);
        }

        long balls = level.getEntities().stream()
                .filter(entity -> entity instanceof AbstractBall)
                .count();

        if (balls == 0) {
            exit();
            Game.winLevel();
        }

        long deadCharacters = level.getEntities().stream()
                .filter(entity -> entity instanceof Character)
                .filter(entity -> !((Character) entity).isAlive())
                .count();

        if (deadCharacters > 0) {
            exit();
            Game.loseLevel(false);
        }
    }

    private void exit() {
        Music.pauseMusic();
        timer.stop();
    }
}
