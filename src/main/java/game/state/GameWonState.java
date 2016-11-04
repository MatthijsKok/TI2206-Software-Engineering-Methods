package game.state;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import panes.OverlayMenu;
import panes.OverlayMenuBuilder;

import java.util.List;

/**
 * State for when the game is won.
 */
public class GameWonState implements GameState {

    /**
     * The position at which the scores are drawn.
     */
    private static final Vec2d SCORE_POSITION = new Vec2d(180, 420);
    /**
     * The height of one score item.
     */
    private static final double SCORE_HEIGHT = 80;

    /**
     * Creates a new GameWonState instance.
     * @param scores List containing the scores of all players.
     */
    public GameWonState(List<Integer> scores) {
        OverlayMenuBuilder builder = new OverlayMenuBuilder();

        builder.setTitle("You won the game!");
        builder.addItem("Main menu",
                event -> GameStateHelper.goToMainMenu());

        OverlayMenu menu = builder.build();

        GameStateHelper.setOverlay(menu);

        Label title = new Label("Score");
        title.setLayoutX(SCORE_POSITION.x);
        title.setLayoutY(SCORE_POSITION.y - SCORE_HEIGHT);
        menu.getChildren().add(title);

        Label label;
        final int players = scores.size();

        for (int i = 0; i < players; i++) {
            label = new Label(
                    String.format("%06d", scores.get(i)),
                    new ImageView(getPlayerImage(i)));

            label.setLayoutX(SCORE_POSITION.x);
            label.setLayoutY(SCORE_POSITION.y + i * SCORE_HEIGHT);
            menu.getChildren().add(label);
        }
    }

    private Image getPlayerImage(final int playerId) {
        switch (playerId) {
            case 0:
                return new Image("images/sprites/mario_mugshot.png");
            case 1:
                return new Image("images/sprites/yoshi_mugshot.png");
            default:
                return null;
        }
    }
}
