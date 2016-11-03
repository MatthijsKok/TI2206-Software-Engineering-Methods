package util;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

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
            final byte[] encoded = Files.readAllBytes(Paths.get(path));
            return new String(encoded, StandardCharsets.UTF_8);
    }
}
