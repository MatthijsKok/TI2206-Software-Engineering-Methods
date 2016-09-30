package entities;
import com.sun.javafx.geom.Vec2d;
import game.Game;
import game.player.Player;
import org.json.JSONObject;

import java.util.List;

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
    public static Entity createEntity(JSONObject entity) {
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
            case "Block":
                return createBlock(position);
            default:
                return null;
        }
    }

    private static Character createCharacter(Vec2d position) {
        for (Player player : Game.getInstance().getPlayers()) {
            System.out.println(player.getCharacter());
            if (player.getCharacter() == null) {
                Character character = new Character(position.x, position.y);
                player.setCharacter(character);
                return character;
            }
        }
        return null;
    }

    private static Ball createBall(Vec2d postion, JSONObject attributes) {
        return new Ball(postion, attributes.getInt("size"));
    }

    private static Wall createWall(Vec2d position) {
        return new Wall(position.x, position.y);
    }

    private static Block createBlock(Vec2d position) {
        return new Block(position.x, position.y);
    }
}
