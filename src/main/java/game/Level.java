package game;

import com.sun.javafx.geom.Vec2d;
import entities.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import ui.HUD;
import ui.UIElement;
import util.GameCanvasManager;
import util.logging.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * The level class represents a level, which is loaded from a file and consists
 * of players, balls, walls and so on.
 */
public class Level {

    /**
     * The logger access point to which everything will be logged.
     */
    private static final Logger LOGGER = new Logger();

    /**
     * The background image of this level.
     */
    private Image background;

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
    public List<Entity> entities = new ArrayList<>();

    /**
     * The entities that will be removed from the level after the update cycle.
     */
    private List<Entity> entitiesToRemove = new ArrayList<>();

    /**
     * The entities that will be added to the level after the update cycle.
     */
    private List<Entity> entitiesToAdd = new ArrayList<>();

    /**
     * All player instances in the level.
     */
    private List<Player> players;

    /**
     * All ui elements active in the level.
     */
    private List<UIElement> uiElements = new ArrayList<>();

    /**
     * The file the level is loaded from.
     */
    private String file;

    /**
     * Creates a new level instance.
     */
    public Level(String levelName, int levelTime, Image bgImage, String music) {
        this.levelName = levelName;
        this.levelTime = levelTime;
        this.background = bgImage;
        this.music = music;
    }

    /**
     * Starts the level.
     */
    public final void start()  throws Exception{
        load();
        setPlayers();

        initUI();
    }

    /**
     * Restarts the level.
     */
    public final void restart()  throws Exception{
        unload();
        load();
        setPlayers();
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
    private void load() throws Exception{
        LOGGER.debug("Loading Level...");

        LevelLoader.parseJSONString("level1.json");

//        // TODO: implement file reading
//        // Set level dimensions
//        setSize(1024, 608);
//
//        // Wall blocks
//        for (int y = 0; y < getHeight(); y += 32) {
//            addEntity(new Wall(0, y));
//            addEntity(new Wall(992, y));
//        }
//
//        // Floor & ceiling blocks
//        for (int x = 0; x < getWidth(); x += 32) {
//            addEntity(new Block(x, 544));    //top floor
//            addEntity(new Block(x, 576));    //lower floor
//            //addEntity(new Block(x, 0));    //ceiling
//        }
//
//        // Player
//        addEntity(new Player(512, 500));
//
//        // Balls
//        addEntity(new Ball(new Vec2d(256, 256), 2));
//        addEntity(new Ball(new Vec2d(512, 256), 2));
//
//        addEntities();
        LOGGER.debug("Level loaded.");
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
        LOGGER.trace("Setting Level size to (" + size.x + "," + size.y + ").");
        size.x = width;
        size.y = height;
    }

    /**
     * Finds the player objects between all instances and stores them in the
     * instance list.
     */
    private void setPlayers() {
        LOGGER.trace("Setting players...");
        players = new ArrayList<>();
        for (Entity entity : entities) {
            if (entity instanceof Player) {
                LOGGER.trace("Setting player: " + entity.toString());
                players.add((Player) entity);
            }
        }
        LOGGER.trace("Players set.");
    }

    /**
     * Initializes the ui elements in a level.
     */
    private void initUI() {
        uiElements.add(new HUD());
    }

    /**
     * Updates the state of all entities in the level.
     * @param dt time difference between now and last update
     */
    final void update(final double dt) {
        LOGGER.debug("Updating Entity's...");
        for (Entity entity : entities) {
            entity.update(dt);
        }
        LOGGER.debug("Updated Entity's");

        removeEntities();
        addEntities();

        LOGGER.debug("Handling collisions...");
        handleCollisions();
        LOGGER.debug("Collisions handled.");
    }

    /**
     * Handles collisions between all entities currently in the level.
     * Both a.collideWith(b) and b.collideWith(a) are called because a
     * only knows what to do with itself and so does b.
     */
    private void handleCollisions() {
        int n = entities.size();
        Entity a, b;
        for (int i = 0; i < n; i++) {
            a = entities.get(i);
            for (int j = i + 1; j < n; j++) {
                b = entities.get(j);
                if (a.intersects(b)) {
                    a.collideWith(b);
                    b.collideWith(a);
                }
            }
        }
    }

    /**
     * Draws all entities and UIElements in the current level.
     */
    public final void draw() {
        LOGGER.debug("Drawing Level...");

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

        // Draw ui elements over entities
        LOGGER.trace("Drawing ui elements...");
        for (UIElement uiElement : uiElements) {
            uiElement.draw();
        }
        LOGGER.trace("ui elements drawn.");
        LOGGER.debug("Level drawn.");
    }

    /**
     * @param i the index of the player
     * @return i'th the player in the current level
     */
    public final Player getPlayer(final int i) {
        return players.get(i);
    }

    /**
     * @return a list of all players in this level
     */
    public final List<Player> getPlayers() {
        return players;
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
    final boolean won() {
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
    final boolean lost() {
        for (Player player : getPlayers()) {
            if (!player.isAlive()) {
                return true;
            }
        }

        return false;
    }
}
