package game;
import java.io.*;
import org.json.*;

/**
 * Created by Sterre on 29-09-16.
 */
public class LevelFactory {

    private static LevelFactory obj = null;

    public static void main(String[] args) {
        readJSONFile("level1.json");

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
