package game;
import java.io.*;
import org.json.*;

/**
 * Created by Sterre on 29-09-16.
 */
public class LevelFactory {

    private static LevelFactory obj = null;

    public String readJSONFile () {
        String filename = "src/main/resources/levels/";
        filename = filename + "level1.json";

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            System.out.println("test");
        } catch(Exception e) {
            e.printStackTrace();
        }

        return "test";

    }




}
