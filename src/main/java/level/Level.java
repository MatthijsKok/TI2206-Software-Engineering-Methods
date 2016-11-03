package level;

import com.sun.javafx.geom.Vec2d;
import entities.AbstractEntity;
import game.Game;
import graphics.Background;
import javafx.application.Platform;
import util.StageManager;
import util.logging.Logger;
import util.sound.Music;

import java.io.IOException;
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
     * The default size of a level.
     */
    private static final Vec2d DEFAULT_SIZE = new Vec2d(1024, 608);
    /**
     * The default background image of a level.
     */
    private static final Background DEFAULT_BACKGROUND_IMAGE = new Background("mountains.png");
    /**
     * The default background music of a level.
     */
    private static final String DEFAULT_BACKGROUND_MUSIC = "mario_theme_remix.mp3";
    /**
     * The size of the level.
     */
    private final Vec2d size = DEFAULT_SIZE;
    /**
     * The file the level is loaded from.
     */
    private final String filename;
    /**
     * Name of the level.
     */
    private String name = "";
    /**
     * The background image of this level.
     */
    private Background backgroundImage = DEFAULT_BACKGROUND_IMAGE;
    /**
     * A timer for which handles all time related things for this level.
     */
    private LevelTimer timer;
    /**
     * The object which handles collisions for this level.
     */
    private final CollisionManager collisionManager;
    /**
     * The object which handles entities for this level.
     */
    private final EntityManager entityManager;

    /**
     * Creates a new level instance.
     *
     * @param uri the file to load the level from.
     */
    public Level(final String uri) {
        filename = uri;
        collisionManager = new CollisionManager();
        entityManager = new EntityManager();
    }

    /**
     * Removes all references to entities in this level.
     */
    public void unload() {
        entityManager.clear();
    }

    /**
     * Loads a level from a file.
     * @throws IOException when the file is not found.
     */
    public void load() throws IOException {
        LOGGER.debug("Loading Level...");

        Music.setMusic(DEFAULT_BACKGROUND_MUSIC);

        LevelLoader.load(this, filename);
        if (entityManager.update(0)) {
            collisionManager.setEntities(getEntities());
        }
        timer.reset();

        Game.setTimer(this.timer);

        Platform.runLater(() ->
                StageManager.getStage().setTitle(name));

        Music.startMusic();
    }

    /**
     * Updates the state of all entities in the level.
     * @param timeDifference time difference between now and last update
     */
    public final void update(final double timeDifference) {
        LOGGER.debug("Updating Level");

        timer.update(timeDifference);

        if (entityManager.update(timeDifference)) {
            collisionManager.setEntities(getEntities());
        }

        collisionManager.update();
    }

    /**
     * Draws all entities and UIElements in the current level.
     */
    public final void draw() {
        LOGGER.debug("Drawing level...");

        // Draw background
        LOGGER.trace("Drawing background.");
        backgroundImage.draw();

        entityManager.draw();
    }

    /**
     * @return A list containing all entities in this level.
     */
    public final List<AbstractEntity> getEntities() {
        return entityManager.getEntities();
    }

    /**
     * Adds an entity to the level.
     * @param entity The entity to add.
     */
    public final void addEntity(AbstractEntity entity) {
        entityManager.addEntity(entity);
    }

    /**
     * Removes an entity from the level.
     * @param entity The entity to remove.
     */
    public final void removeEntity(AbstractEntity entity) {
        entityManager.removeEntity(entity);
    }

    /**
     * Set the level size.
     * @param width  the width of the level
     * @param height the height of the level
     */
    /* default */ final void setSize(final double width, final double height) {
        LOGGER.trace("Setting level size to (" + size.x + "," + size.y + ").");
        size.x = width;
        size.y = height;
    }

    /**
     * Sets the level's name.
     * @param name The name of this level.
     */
    /* default */ void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the level's timer.
     * @param timer The timer of this level.
     */
    public void setTimer(LevelTimer timer) {
        this.timer = timer;
    }

    /**
     * @return Timer, the timer of this level.
     */
    public LevelTimer getTimer() {
        return timer;
    }

    /**
     * Sets the levels background image.
     * @param filename URI of the image file.
     */
    /* default */ void setBackgroundImage(String filename) {
        backgroundImage = new Background(filename);
    }

    /**
     * Sorts all entities in the level by depth.
     */
    public void depthSort() {
        entityManager.sort();
    }
}
