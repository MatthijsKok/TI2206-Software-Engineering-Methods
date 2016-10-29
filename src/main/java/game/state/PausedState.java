package game.state;

import game.Game;
import level.Level;
import panes.OverlayMenuBuilder;
import util.sound.Music;

/**
 * State for when the game is paused.
 */
class PausedState implements GameState {

    /**
     * The level this state is for.
     */
    private final Level level;

    /**
     * Creates a new PausedState.
     *
     * @param level Level - The level that is paused.
     */
    /* default */ PausedState(Level level) {
        this.level = level;

        OverlayMenuBuilder builder = new OverlayMenuBuilder();

        builder.setTitle("Game Paused");
        builder.addItem("Resume", event -> resume());
        builder.addItem("Settings", event -> GameStateHelper.goToSettings());
        builder.addItem("Main menu", event -> GameStateHelper.goToMainMenu());

        GameStateHelper.setOverlay(builder.build());
    }

    /**
     * Behaviour that should be executed when the resume button is pressed.
     */
    private void resume() {
        Music.startMusic();
        Game.setState(new InProgressState(level));
    }
}
