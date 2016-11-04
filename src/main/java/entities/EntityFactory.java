package entities;

import com.sun.javafx.geom.Vec2d;
import entities.balls.AbstractBall;
import entities.balls.ColoredBall;
import entities.blocks.FloorBlock;
import entities.blocks.Gate;
import entities.blocks.SpikeBlock;
import entities.blocks.WallBlock;
import entities.character.Character;
import game.Game;
import game.player.Player;
import graphics.Sprite;
import org.json.JSONObject;

/**
 * The entityFactory class creates entities from JSON objects.
 */
public final class EntityFactory {

    /**
     * Empty constructor.
     */
    private EntityFactory() {
    }

    /**
     * Parses the JSONArray entities in entity objects.
     *
     * @param entity JSONObject representing the entity.
     * @return The created entity.
     */
    public static AbstractEntity createEntity(JSONObject entity) {
        String type = entity.getString("type");
        double x = entity.getDouble("x");
        double y = entity.getDouble("y");
        Vec2d position = new Vec2d(x, y);

        if (entity.has("attributes")) {
            return createEntity(type, position, entity.getJSONObject("attributes"));
        }

        return createEntity(type, position);
    }

    private static AbstractEntity createEntity(String type, Vec2d position, JSONObject attributes) {
        switch (type) {
            case "Ball":
                return createBall(position, attributes);
            case "Gate":
                return createGate(position, attributes);
            case "Floor":
                return createFloor(position, attributes);
            default:
                return null;
        }
    }

    private static AbstractEntity createEntity(String type, Vec2d position) {
        switch (type) {
            case "Player":
                return createCharacter(position);
            case "Wall":
                return createWall(position);
            case "Spike":
                return createSpike(position);
            default:
                return null;
        }
    }

    /**
     * Creates a character.
     *
     * @param position Vec2d the position where the new character will be created.
     * @return a character or else null.
     */
    private static Character createCharacter(Vec2d position) {
        for (Player player : Game.getPlayers()) {
            if (player.getCharacter() == null && player.getLives() > 0) {
                return instantiateCharacter(position, player);
            }
        }
        return null;
    }

    /**
     * Instanciates character.
     *
     * @param position Vec2d Position where the character will be placed.
     * @param player   Player that will play the character.
     * @return A new character linked to a player.
     */
    private static Character instantiateCharacter(Vec2d position, Player player) {
        Character character = new Character(position);
        player.setCharacter(character);
        return character;
    }

    /**
     * Creates a ball.
     *
     * @param position   Vec2d position where the ball will be placed.
     * @param attributes JSONObject attributes the ball has.
     * @return A ball with a size, color and position.
     */
    private static AbstractBall createBall(Vec2d position, JSONObject attributes) {
        int size = attributes.getInt("size");

        if (attributes.has("color")) {
            ColoredBall.Color color = ColoredBall.getColor(attributes.getString("color"));
            return new ColoredBall(position, size, color);
        }

        return new ColoredBall(position, size);
    }

    /**
     * Creates a wall.
     *
     * @param position Vec2d position where the WallBlock will be placed.
     * @return A wallBlock at position.
     */
    private static WallBlock createWall(Vec2d position) {
        return new WallBlock(position);
    }

    /**
     * Creates a gate.
     *
     * @param position   Vec2d position where the Gate will be placed.
     * @param attributes JSONObject attributes the Gate has.
     * @return Gate object.
     */
    private static Gate createGate(Vec2d position, JSONObject attributes) {
        if (attributes.has("color")) {
            ColoredBall.Color color = ColoredBall.getColor(attributes.getString("color"));
            return new Gate(position, color);
        }
        return null;
    }

    /**
     * Creates a floor.
     *
     * @param position Vec2d position where the FloorBlock will be placed.
     * @return FloorBlock object at position.
     */
    private static FloorBlock createFloor(Vec2d position, JSONObject attributes) {

        if (attributes.has("sprite")) {
            Sprite blockSprite = new Sprite("blocks/" + attributes.getString("sprite"));
            return new FloorBlock(position, blockSprite);
        }

        return null;
    }

    /**
     * Creates a floor.
     *
     * @param position Vec2d position where the SpikeBlock will be placed.
     * @return SpikeBlock object at position.
     */
    private static SpikeBlock createSpike(Vec2d position) {
        return new SpikeBlock(position);
    }
}
