package ui;

import game.Game;
import game.player.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import level.Level;

/**
 * HeadsUpDisplay is a acronym for Heads Up Display. A common name
 * for the overlay of a game.
 */
class HeadsUpDisplay extends AbstractUIElement {

    /**
     * Height of the time bar.
     */
    private static final int HEIGHT = 24;

    /**
     * Padding around the time bar.
     */
    private static final int MARGIN = 8;

    /**
     * Padding around the time bar.
     */
    private static final int PADDING = 2;

    private double getTimeLeft() {
        final Level level = Game.getInstance().getState().getCurrentLevel();
        return level.getTimeLeft() / level.getDuration();
    }

    private Player getPlayer(final int playerId) {
        return Game.getInstance().getPlayer(playerId);
    }

    /**
     * Selector for the lives of a player with id playerID.
     * @param playerId the id of the player.
     * @return the lives of a player.
     */
    /* default */ int getPlayerLives(final int playerId) {
        return getPlayer(playerId).getLives();
    }

    /**
     * Selector for the score of a player with id playerID.
     * @param playerId the id of the player.
     * @return the score of a player.
     */
    /* default */ int getPlayerScore(final int playerId) {
        return getPlayer(playerId).getScore();
    }

    /**
     * Draws the lives that the first player of a level has left.
     * @param canvas The Canvas to draw on
     * @param graphicsContext The GraphicsContext to draw on
     */
    /* default */ void draw(final Canvas canvas, final GraphicsContext graphicsContext) {
        // Outer time bar
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(
                MARGIN, canvas.getHeight() - HEIGHT - MARGIN,
                canvas.getWidth() - 2 * MARGIN, HEIGHT);

        // Inner time bar
        graphicsContext.setFill(Color.RED);
        graphicsContext.fillRect(
                MARGIN + PADDING, canvas.getHeight() - HEIGHT - MARGIN + PADDING,
                (canvas.getWidth() - 2 * (PADDING + MARGIN)) * getTimeLeft(), HEIGHT - 2 * PADDING);
    }
}
