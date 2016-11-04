package util;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Utility class that parses JSON strings and files.
 */
public final class JSONParser {

    /**
     * Empty constructor.
     */
    private JSONParser() {

    }

    /**
     * Takes the name of a JSON file and returns the content as a JSON object.
     * @param path The path to the file to read.
     * @return The JSONObject resulting from the file content.
     * @throws IOException if the path/file is not found.
     */
    public static JSONObject parseJSONFile(final String path) throws IOException {
        return new JSONObject(readFile(path));
    }

    /**
     * Reads the file from a path.
     * @param path String where the file is.
     * @return a String with all the characters encoded
     * @throws IOException If the path/file is not found.
     */
    private static String readFile(final String path) throws IOException {
        final File file = new File(JSONParser.class.getResource(path).getFile());

        String content = "";

        try (FileReader fileStream = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileStream)) {

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content += line;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }
}
