package entities;

import game.LevelFactory;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Sterre on 29-09-16.
 */
public class EntityFactory {

    public static void parseJSONString (JSONArray entities){

        System.out.println("---- EntityFactory parser: ----");

        final int n = entities.length();
        for (int i = 0; i < n; ++i) {
            final JSONObject entitie = entities.getJSONObject(i);

            System.out.println(entitie.getString("type"));
            System.out.println(entitie.getInt("x"));
            System.out.println(entitie.getInt("y"));

//            final JSONArray attributes = entitie.getJSONArray("attributes");
//            final int k = attributes.length();
//            for (int j = 0; j < k; ++j){
//                final JSONObject attribute = attributes.getJSONObject(j);
//
//                System.out.println("size: "+ entitie.getInt("size"));
//            }
            System.out.println();
        }
    }
}
