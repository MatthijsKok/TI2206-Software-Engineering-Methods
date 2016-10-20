package entities;

import com.sun.javafx.geom.Vec2d;
import entities.balls.AbstractBall;
import entities.balls.ColoredBall;
import game.Game;
import game.player.Player;
import org.json.JSONObject;

/**
 * The entityFactory class creates entities from JSON objects.
 */
public final class EntityFactory {

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

        switch (type) {
            case "Player":
                return createCharacter(position);
            case "Ball":
                return createBall(position, entity.getJSONObject("attributes"));
            case "Wall":
                return createWall(position);
            case "Floor":
                return createFloor(position);
            default:
                return null;
        }
    }

    private static Character createCharacter(Vec2d position) {

        for (Player player : Game.getInstance().getPlayers()) {
            if (player.getCharacter() == null && player.getLives() > 0) {
                return instantiateCharacter(position, player);
            }
        }
        return null;
    }

    private static Character instantiateCharacter(Vec2d position, Player player) {
        Character character = new Character(position);
        character.determineSprite(player.getId());
        player.setCharacter(character);
        character.setPlayer(player);
        return character;
    }

    private static AbstractBall createBall(Vec2d position, JSONObject attributes) {
        int size = attributes.getInt("size");

        if (attributes.has("color")) {
            ColoredBall.Color color = ColoredBall.getColor(attributes.getString("color"));
            return new ColoredBall(position, size, color);
        }

        return new ColoredBall(position, size);
    }

    private static WallBlock createWall(Vec2d position) {
        return new WallBlock(position);
    }

    private static FloorBlock createFloor(Vec2d position) {
        return new FloorBlock(position);
    }
}
