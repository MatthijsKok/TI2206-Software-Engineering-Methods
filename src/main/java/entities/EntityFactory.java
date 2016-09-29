package entities;

import game.LevelFactory;
import org.json.JSONObject;

/**
 * Created by Sterre on 29-09-16.
 */
public class EntityFactory {

    public static void main(String[] args) {
//        readJSONFile("level1.json");
        parseJSONString("level1.json");
    }

    public static void parseJSONString (String filename){
        filename = LevelFactory.readJSONFile(filename);

        JSONObject jObject = new JSONObject(filename);
    }


}
