package entities;
import com.sun.javafx.geom.Vec2d;
import org.json.JSONObject;

/**
 * Created by Sterre on 29-09-16.
 */
public class EntityFactory {

    /**
     * Parses the JSONArray entities in entity objects
     *
     * @param entity
     */
    public static Entity parseJSONString(JSONObject entity) throws Exception {
        System.out.println("---- EntityFactory parser: ----");

        JSONObject attributes = entity.getJSONObject("attributes");

        int x, y, size;

        String type = entity.getString("type");
        x = entity.getInt("x");
        y = entity.getInt("y");

        Entity instance = null;

        switch (type) {
            case "Player":
//                instance = new Player(x, y);
                break;
            case "Ball":
                size = attributes.getInt("size");
//                instance = new Ball(new Vec2d(x, y), size);
                break;
            default:
                throw new Exception("Type: " + type + " is not an entity.");
        }

        //Syso is for testing purposes
        System.out.println("Type: " + entity.getString("type"));
        System.out.println("x:    " + entity.getInt("x"));
        System.out.println("y:    " + entity.getInt("y"));
        try {
            System.out.println("size: " + attributes.getInt("size"));
        } catch (Exception e) {
            System.out.println("no size available");
        }
        System.out.println();
        return instance;
    }
}
