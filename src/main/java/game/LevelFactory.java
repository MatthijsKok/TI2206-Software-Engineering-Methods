package game;
import java.io.*;
import java.util.ArrayList;

import entities.EntityFactory;
import org.json.*;

/**
 * Created by Sterre on 29-09-16.
 */
public class LevelFactory {

    public static void main(String[] args) {
        parseJSONString("level1.json");
    }

    public static void parseJSONString (String filename){
        filename = readJSONFile(filename);

        JSONObject jObject = new JSONObject(filename);
        String levelName = jObject.getString("name");
        int levelTime = jObject.getInt("time");
        String bgImage = jObject.getString("defaultBackgroundImageURI");
        String music = jObject.getString("defaultBackgroundMusicURI");

        //syso is just for testing
        System.out.println();
        System.out.println("level name is: "+levelName);
        System.out.println("time is: "+levelTime);
        System.out.println("bg is: "+bgImage);
        System.out.println("music is: "+music);
        System.out.println();

        // entities should be parsed in the EntituFactory
        final JSONArray entities = jObject.getJSONArray("entities");
        EntityFactory.parseJSONString(entities);
    }

    public static String readJSONFile (String filename) {
        filename = "src/main/resources/levels/"+ filename;
        String result = "";

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                line = bufferedReader.readLine();
            }
            result = stringBuilder.toString();
            System.out.println(result);
        }

        catch(Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
