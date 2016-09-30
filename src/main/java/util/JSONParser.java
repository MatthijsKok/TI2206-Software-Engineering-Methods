package util;



import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Utility class that parses JSON strings and files.
 */
public class JSONParser {


    public static JSONObject parseJSONFile(String filename) {
        return new JSONObject(fileToString(filename));
    }

    private static String fileToString(String filename) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
