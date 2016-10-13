package level;

import entities.AbstractEntity;
import entities.EntityFactory;
import javafx.application.Platform;
import org.json.JSONArray;
import org.json.JSONObject;
import util.JSONParser;

import java.io.IOException;

/**
 * This utility class loads entities into a level.
 */
final class LevelLoader {

    private LevelLoader() {

    }

    /**
     * Loads metadata and entities in a level.
     * @param level The level to load.
     */
    static void load(Level level) {
        try {
            JSONObject json = JSONParser.parseJSONFile(level.getFilename());
            JSONArray entities = json.getJSONArray("entities");

            loadMetaData(level, json);
            loadEntities(level, entities);
        } catch (IOException e) {
            Platform.exit();
        }
    }

    private static void loadMetaData(Level level, JSONObject metaData) {
        // Size
        if (metaData.has("size")) {
            JSONObject size = metaData.getJSONObject("size");
            level.setSize(size.getDouble("width"), size.getDouble("height"));
        }

        // Name
        if (metaData.has("name")) {
            level.setName(metaData.getString("name"));
        }

        // Time
        if (metaData.has("duration")) {
            level.setDuration(metaData.getDouble("duration"));
        }

        if (metaData.has("backgroundImageURI")) {
            level.setBackgroundImage(metaData.getString("backgroundImageURI"));
        }

        if (metaData.has("backgroundMusicURI")) {
            level.setBackgroundMusic(metaData.getString("backgroundMusicURI"));
        }
    }

    private static void loadEntities(Level level, JSONArray entities) {
        for (Object obj : entities) {
            JSONObject json = (JSONObject) obj;

            AbstractEntity entity = EntityFactory.createEntity(json);

            if (entity != null) {
                level.addEntity(entity);
            }
        }
    }
}
