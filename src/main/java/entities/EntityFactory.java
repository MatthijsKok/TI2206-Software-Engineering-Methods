package entities;

import game.LevelFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;

/**
 * Created by Sterre on 29-09-16.
 */
public class EntityFactory {

    /**
     * Parses the JSONArray entities in entity objects
     * @param entities
     */
    public static void parseJSONString (JSONArray entities){
        System.out.println("---- EntityFactory parser: ----");
        final int n = entities.length();
        for (int i = 0; i < n; ++i) {
            JSONObject entitie = entities.getJSONObject(i);
            JSONObject attributes = entitie.getJSONObject("attributes");

            //Syso is for testing purposes
            System.out.println("Type: " + entitie.getString("type"));
            System.out.println("x:    " + entitie.getInt("x"));
            System.out.println("y:    " + entitie.getInt("y"));
            try {
                System.out.println("size: " + attributes.getInt("size"));
            }
            catch (Exception e){
                System.out.println("no size available");
            }
            System.out.println();
        }
    }
}
