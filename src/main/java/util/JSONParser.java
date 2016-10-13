package util;



import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Utility class that parses JSON strings and files.
 */
public final class JSONParser {

    private JSONParser() {

    }

    /**
     * Takes the name of a JSON file and returns the content as a JSON object.
     * @param filename The name of the file to read.
     * @return The JSONObject resulting from the file content.
     */
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}
