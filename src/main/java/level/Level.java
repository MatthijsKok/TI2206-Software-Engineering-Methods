package level;

import com.sun.javafx.geom.Vec2d;
import entities.AbstractEntity;
import entities.Ball;
import entities.Character;
import game.Game;
import game.GameState;
import game.player.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.media.AudioClip;
import util.CollisionManager;
import util.CanvasManager;
import graphics.Sprite;
import util.StageManager;
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
     * The default size of a level.
     */
    private static final Vec2d DEFAULT_SIZE = new Vec2d(1024, 608);

    /**
     * The default duration of a level.
     */
    private static final double DEFAULT_DURATION = 30;

    /**
     * The default background image of a level.
     */
    private static final Sprite DEFAULT_BACKGROUND_IMAGE = new Sprite("background.jpg");

    /**
     * The default background music of a level.
     */
    private static final AudioClip DEFAULT_BACKGROUND_MUSIC = null;

    /**
     * The size of the level.
     */
    private Vec2d size = DEFAULT_SIZE;

    /**
     * Duration of the level.
     */
    private double duration = DEFAULT_DURATION;

    /**
     * Name of the level.
     */
    private String name = "";

    /**
     * The background image of this level.
     */
    private Sprite backgroundImage = DEFAULT_BACKGROUND_IMAGE;

    /**
     * The scale at which the background image is drawn.
     */
    private double backgroundImageScale = 1;

    /**
     * Music of the level.
     */
    private AudioClip backgroundMusic = DEFAULT_BACKGROUND_MUSIC;

    /**
     * Time spend on the level.
     */
    private double timeSpend = 0;
    /**
     * The entities currently active in the level.
     */
    private List<AbstractEntity> entities = new ArrayList<>();
    /**
     * The entities that will be removed from the level after the update cycle.
     */
    private List<AbstractEntity> entitiesToRemove = new ArrayList<>();
    /**
     * The entities that will be added to the level after the update cycle.
     */
    private List<AbstractEntity> entitiesToAdd = new ArrayList<>();

    /**
     * The file the level is loaded from.
     */
    private String filename;

    /**
     * Indicate whether the level is either won, lost or neither.
     */
    private boolean won = false, lost = false;

    /**
     * Creates a new level instance.
     *
     * @param uri the file to load the level from.
     */
    public Level(final String uri) {
        filename = uri;
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
    public void unload() {
        Game.getInstance().getPlayers().forEach(Player::clearCharacter);

        entities = new ArrayList<>();
        entitiesToRemove = new ArrayList<>();
        entitiesToAdd = new ArrayList<>();
    }

    /**
     * Loads a level from a file.
     */
    public void load() {
        LOGGER.debug("Loading Level...");

        LevelLoader.load(this);
        addEntities();
        timeSpend = 0;
        won = false;
        lost = false;

        StageManager.getStage().setTitle(name);

        if (this.backgroundMusic != null) {
            this.backgroundMusic.play();
        }
    }

    /**
     * @return the level width
     */
    public final double getWidth() {
        return size.x;
    }

    /**
     * @return the level height
     */
    public final double getHeight() {
        return size.y;
    }

    /**
     * Set the level size.
     *
     * @param width  the width of the level
     * @param height the height of the level
     */
    final void setSize(final double width, final double height) {
        LOGGER.trace("Setting level size to (" + size.x + "," + size.y + ").");
        size.x = width;
        size.y = height;
    }

    /**
     * @return The name of the file this level is loaded from.
     */
    final String getFilename() {
        return filename;
    }

    /**
     * @return a list of all entities in the current level.
     */
    public final List<AbstractEntity> getEntities() {
        List<AbstractEntity> entities = new ArrayList<>();

        entities.addAll(this.entities);
        entities.addAll(entitiesToAdd);
        entities.removeAll(entitiesToRemove);

        return entities;
    }

    /**
     * Register that an entity has to be added.
     *
     * @param e entity to add
     */
    public final void addEntity(final AbstractEntity e) {
        entitiesToAdd.add(e);
    }

    /**
     * Register that an entity has to be removed.
     *
     * @param e The entity to remove
     * @return true if e is not already removed, false otherwise
     */
    public final boolean removeEntity(final AbstractEntity e) {
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

    private long countBalls() {
        return entities.stream()
                .filter(e -> e instanceof Ball)
                .count();
    }

    /**
     * Really add all entities that need to be removed to the entity list.
     */
    private void addEntities() {
        entities.addAll(entitiesToAdd);
        entitiesToAdd = new ArrayList<>();
    }

    /**
     * Updates the state of all entities in the level.
     *
     * @param timeDifference time difference between now and last update
     */
    public final void update(final double timeDifference) {
        LOGGER.debug("Updating AbstractEntity's...");

        timeSpend += timeDifference;

        if (timeSpend > duration) {
            timeUp();
        }

        for (AbstractEntity entity : entities) {
            entity.update(timeDifference);
            entity.updatePosition(timeDifference);
            entity.updateSprite(timeDifference);
        }

        LOGGER.debug("Updated AbstractEntity's");

        removeEntities();
        addEntities();

        if (countBalls() == 0) {
            win();
        }

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


        final Canvas canvas = CanvasManager.getCanvas();
        backgroundImage.draw(canvas.getWidth() / 2, canvas.getHeight() / 2, backgroundImageScale);

        // Draw entities
        LOGGER.trace("Drawing entities...");
        entities.forEach(AbstractEntity::draw);
    }

    /**
     * Sets the levels name.
     * @param name The name of this level.
     */
    void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the levels duration.
     * @param duration The duration in seconds (must be positive).
     */
    public void setDuration(double duration) {
        if (duration > 0) {
            this.duration = duration;
        }
    }

    /**
     * Sets the levels background image.
     * @param backgroundImage URI of the image file.
     */
    void setBackgroundImage(String backgroundImage) {
        Canvas canvas = CanvasManager.getCanvas();
        if (backgroundImage != null && !backgroundImage.equals("")) {
            this.backgroundImage = new Sprite(backgroundImage);
            this.backgroundImage.setOffsetToCenter();
            this.backgroundImageScale = Math.max(
                    canvas.getWidth() / this.backgroundImage.getWidth(),
                    canvas.getHeight() / this.backgroundImage.getHeight());
        }
    }

    /**
     * Sets the levels background music.
     * @param backgroundMusic The URI of the music file.
     */
    void setBackgroundMusic(String backgroundMusic) {
        if (backgroundMusic != null && !backgroundMusic.equals("")) {
            this.backgroundMusic = new AudioClip(backgroundMusic);
        }
    }

    /**
     * @return The amount of seconds there is left to complete the level.
     */
    public double getTimeLeft() {
        return duration - timeSpend;
    }

    /**
     * @return The total amount of seconds a players has to complete the level.
     */
    public double getDuration() {
        return duration;
    }

    /**
     * @return Boolean indicating whether the level is won.
     */
    public boolean isWon() {
        return won;
    }

    /**
     * @return Boolean indicating whether the level is lost.
     */
    public boolean isLost() {
        return lost;
    }

    /**
     * Win the level.
     */
    private void win() {
        GameState gameState = Game.getInstance().getState();
        gameState.pause();
        won = true;

        if (!gameState.hasNextLevel()) {
            gameState.win();
        }
    }

    /**
     * Lose the level.
     */
    public final void lose() {
        Game.getInstance().getState().pause();
        lost = true;
    }

    /**
     * This method kills each character because the time is up.
     */
    private void timeUp() {
        for (Player player: Game.getInstance().getPlayers()) {
            Character character = player.getCharacter();
            if (character != null) {
                character.die();
            }
        }

        lose();
    }
}
