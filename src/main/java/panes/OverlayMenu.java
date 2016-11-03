package panes;

import com.sun.javafx.geom.Vec2d;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import panes.elements.MarioButton;
import util.Pair;

import java.util.List;

/**
 * OverlayMenu represents a menu that is drawn over
 * the current game.
 */
class OverlayMenu extends Pane {

    /**
     * The padding around the menu.
     */
    private static final Vec2d PADDING = new Vec2d(180, 180);
    /**
     * The space between two menu items.
     */
    private static final int ITEM_HEIGHT = 64;
    /**
     * The background color of the menu.
     */
    private static final Paint BACKGROUND_COLOR = Color.web("#000000", 0.5);

    /**
     * Creates a new OverlayMenu.
     * @param title String with the title of the menu.
     * @param items Pair[] with the names and actions of
     *              the menu items.
     */
    OverlayMenu(String title,
                       List<Pair<String, EventHandler<ActionEvent>>> items) {
        setBackground(new Background(new BackgroundFill(BACKGROUND_COLOR, null, null)));

        getChildren().add(createTitle(title));

        for (int i = 0; i < items.size(); i++) {
            getChildren().add(createItem(items.get(i), i));
        }
    }

    /**
     * Create a Title.
     * @param title String for the game.
     * @return a label.
     */
    private Label createTitle(String title) {
        Label label = new Label(title);
        label.setLayoutX(PADDING.x);
        label.setLayoutY(PADDING.y - ITEM_HEIGHT);

        return label;
    }

    /**
     * Create a MarioButton.
     * @param item  EventHandler.
     * @param index int with the index.
     * @return a button.
     */
    private MarioButton createItem(
            Pair<String, EventHandler<ActionEvent>> item, int index) {
        MarioButton button = new MarioButton(item.getKey(), item.getValue());

        button.setLayoutX(PADDING.x);
        button.setLayoutY(PADDING.y + index * ITEM_HEIGHT);

        return button;
    }
}
