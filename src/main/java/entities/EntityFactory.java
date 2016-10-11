package entities;

import com.sun.javafx.geom.Vec2d;
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
                return createBlock(position);
            default:
                return null;
        }
    }

    private static Character createCharacter(Vec2d position) {
        for (Player player : Game.getInstance().getPlayers()) {
            if (player.getCharacter() == null && player.getLives() > 0) {
                Character character = new Character(position);
                player.setCharacter(character);
                return character;
            }
        }
        return null;
    }

    private static Ball createBall(Vec2d position, JSONObject attributes) {
        int size = attributes.getInt("size");

        if (attributes.has("color")) {
            Ball.Color color = Ball.getColor(attributes.getString("color"));
            return new Ball(position, size, color);
        }

        return new Ball(position, size);
    }

    private static WallBlock createWall(Vec2d position) {
        return new WallBlock(position);
    }

    private static FloorBlock createBlock(Vec2d position) {
        return new FloorBlock(position);
    }
}
