package game;

import java.io.*;

import com.sun.javafx.geom.Vec2d;
import entities.*;
import javafx.scene.image.Image;
import org.json.*;

/**
 * Created by Sterre on 29-09-16.
 */
public class LevelLoader {

//    /**
//     * main is temporarly here for testing purposes
//     *
//     * @param args
//     */
//    public static void main(String[] args) throws Exception {
//        parseJSONString("level1.json");
//    }



    /**
     * Parses a JSONString in level elements
     *
     * @param filename
     */
    public static void parseJSONString(String filename) throws Exception {
        filename = readJSONFile(filename);

        JSONObject jObject = new JSONObject(filename);
        String levelName = jObject.getString("name");
        int levelTime = jObject.getInt("time");
        String bgImageURI = jObject.getString("defaultBackgroundImageURI");
        Image bgImage = new Image(bgImageURI);
        String music = jObject.getString("defaultBackgroundMusicURI");

        //syso is just for testing
        System.out.println();
        System.out.println("level: " + levelName);
        System.out.println("time:  " + levelTime);
        System.out.println("bg:    " + bgImage);
        System.out.println("music: " + music);
        System.out.println();

 //       Level level = new Level("JSONLevel.txt")

        // entities should be parsed in the EntityFactory
        JSONArray entities = jObject.getJSONArray("entities");
        int n = entities.length();
        Entity entity;
        for (int i = 0; i < n; ++i) {
            entity = EntityFactory.parseJSONString(entities.getJSONObject(i));
 //           level.addEntity(entity);
        }
    }

    /**
     * this method reads a JSONFile. For each level there is a JSON file
     *
     * @param filename is the name of the JSON file that should be read.
     * @return a String that can be parsed with the parseJSONString.
     */
    public static String readJSONFile(String filename) {
        filename = "src/main/resources/levels/" + filename;
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
