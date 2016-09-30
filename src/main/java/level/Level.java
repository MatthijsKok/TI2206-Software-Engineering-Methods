package level;

import com.sun.javafx.geom.Vec2d;
import entities.*;
import entities.Character;
import game.Game;
import game.player.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import ui.HUD;
import ui.UIElement;
import util.CollisionManager;
import util.GameCanvasManager;
import util.logging.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * The level class represents a level, which is loaded from a file and consists
 * of characters, balls, walls and so on.
 */
public class Level {

    /**
     * The logger access point to which everything will be logged.
     */
    private static final Logger LOGGER = Logger.getInstance();

    /**
     * The game instance.
     */
    private static final Game GAME = Game.getInstance();

    /**
     * Duration of the level.
     */
    public double duration = 100;

    /**
     * Time spend on the level.
     */
    public double timeSpend = 0;

    /**
     * The background image of this level.
     */
    public Image background;

    /**
     * Music of the level.
     */
    private String music;

    /**
     * Name of the level
     */
    private String levelName;

    /**
     * Time it takes for the level to end
     */
    private int levelTime;

    /**
     * The size of the level.
     */

    private Vec2d size = new Vec2d(0, 0);

    /**
     * The entities currently active in the level.
     */
    private List<Entity> entities = new ArrayList<>();

    /**
     * The entities that will be removed from the level after the update cycle.
     */
    private List<Entity> entitiesToRemove = new ArrayList<>();

    /**
     * The entities that will be added to the level after the update cycle.
     */
    private List<Entity> entitiesToAdd = new ArrayList<>();

    /**
     * The file the level is loaded from.
     */
    private String file;

    /**
     * Creates a new level instance.
     *
     * @param uri the file to load the level from.
     */
    public Level(final String uri) {
        file = uri;
    }

    /**
     * Restarts the level.
     */
    public final void restart() {
        unload();
        load();
    }

    /**
     * Removes all references to entities in this level.
     */
    private void unload() {
        entities = new ArrayList<>();
        entitiesToRemove = new ArrayList<>();
        entitiesToAdd = new ArrayList<>();
    }

    /**
     * Loads a level from a file.
     */
    private void load() {
        LOGGER.debug("Loading Level...");
        setSize(1024, 608);

        LevelLoader.parseJSONString("level1.json");

        LOGGER.debug("Level loaded.");
        // Floor & ceiling blocks
        for (int x = 0; x < getWidth(); x += 32) {
            addEntity(new Block(x, 544));    //top floor
            addEntity(new Block(x, 576));    //lower floor
            //addEntity(new Block(x, 0));    //ceiling
        }

        // Character
        Character character;
        int characters = 0;

        if (characters < GAME.getPlayerCount()) {
            character = new Character(720, 500);
            addEntity(character);
            GAME.getPlayer(characters).setCharacter(character);
            characters++;
        }

        if (characters < GAME.getPlayerCount()) {
            character = new Character(384, 500);
            addEntity(character);
            GAME.getPlayer(characters).setCharacter(character);
            characters++;
        }

        // Balls
        addEntity(new Ball(new Vec2d(256, 256), 2));
        addEntity(new Ball(new Vec2d(512, 256), 2));

        addEntities();
        LOGGER.debug("level loaded.");
    }

    /**
     * @return the level width
     */
    public final double getWidth() {
        return size.x;
    }

    /**
     * @param width the width to set the levels width to
     */
    public final void setWidth(final double width) {
        setSize(width, getHeight());
    }

    /**
     * @return the level height
     */
    public final double getHeight() {
        return size.y;
    }

    /**
     * @param height the height to set the levels height to
     */
    public final void setHeight(final double height) {
        setSize(getWidth(), height);
    }

    /**
     * Set the level size.
     * @param width the width of the level
     * @param height the height of the level
     */
    public final void setSize(final double width, final double height) {
        LOGGER.trace("Setting level size to (" + size.x + "," + size.y + ").");
        size.x = width;
        size.y = height;
    }

    /**
     * Updates the state of all entities in the level.
     * @param dt time difference between now and last update
     */
    public final void update(final double dt) {
        LOGGER.debug("Updating Entity's...");

        timeSpend += dt;

        for (Entity entity : entities) {
            entity.update(dt);
        }
        LOGGER.debug("Updated Entity's");

        removeEntities();
        addEntities();

        LOGGER.debug("Handling collisions...");
        CollisionManager.handleCollisions();
        LOGGER.debug("Collisions handled.");
    }

    /**
     * Draws all entities and UIElements in the current level.
     */
    public final void draw() {
        LOGGER.debug("Drawing level...");

        // Draw background
        LOGGER.trace("Drawing background.");
        GraphicsContext gc = GameCanvasManager.getInstance().getContext();
        gc.setFill(Color.ALICEBLUE);
        gc.fillRect(0, 0, getWidth(), getHeight());

        // Draw entities
        LOGGER.trace("Drawing entities...");
        for (Entity entity : entities) {
            entity.draw();
        }
        LOGGER.trace("Entities drawn.");
    }

    /**
     * @return a list of all entities in the current level.
     */
    public final List<Entity> getEntities() {
        return entities;
    }

    /**
     * Register that an entity has to be added.
     * @param e entity to add
     */
    public final void addEntity(final Entity e) {
        entitiesToAdd.add(e);
    }

    /**
     * Register that an entity has to be removed.
     *
     * @param e The entity to remove
     * @return true if e is not already removed, false otherwise
     */
    public final boolean removeEntity(final Entity e) {
        if (entities.contains(e) && !entitiesToRemove.contains(e)) {
            entitiesToRemove.add(e);
            return true;
        }

        return false;
    }

    /**
     * Really removes all entities that need to be removed from the entity list.
     */
    private void removeEntities() {
        entities.removeAll(entitiesToRemove);
        entitiesToRemove = new ArrayList<>();
    }

    /**
     * Really add all entities that need to be removed to the entity list.
     */
    private void addEntities() {
        entities.addAll(entitiesToAdd);
        entitiesToAdd = new ArrayList<>();
    }

    /**
     * @return true if all balls are destroyed, false otherwise.
     */
    public final boolean won() {
        for (Entity entity : entities) {
            if (entity instanceof Ball) {
                return false;
            }
        }

        return true;
    }

    /**
     * @return true if a player died, false otherwise.
     */
    public final boolean lost() {
        for (Player player : GAME.getPlayers()) {
            Character character = player.getCharacter();
            if (character != null && !character.isAlive()) {
                return true;
            }
        }

        return false;
    }
}
