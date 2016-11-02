package panes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder class for the OverlayMenu.
 */
public class OverlayMenuBuilder {

    /**
     * The title of the menu.
     */
    private String title;

    /**
     * The items the menu consists of.
     */
    private final List<Pair<String, EventHandler<ActionEvent>>> items = new ArrayList<>();

    /**
     * Sets the title of the menu.
     * @param title String - The title.
     */
    public final void setTitle(String title) {
        this.title = title;
    }

    /**
     * Adds an item to the menu.
     * @param name String - The name of the item.
     * @param onclick EventHandler - The action that
     *                should happen when the item is clicked.
     */
    public final void addItem(String name, EventHandler<ActionEvent> onclick) {
        items.add(new Pair<>(name, onclick));
    }

    /**
     * Builds the OverlayMenu.
     * @return OverlayMenu - The menu that is just built.
     */
    public final OverlayMenu build() {
        return new OverlayMenu(title, items);
    }
}
