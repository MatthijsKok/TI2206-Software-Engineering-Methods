package level;

import entities.AbstractEntity;
import entities.EntityFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import util.JSONParser;
import util.sound.Music;

import java.io.IOException;

/**
 * This utility class loads entities into a level.
 */
final class LevelLoader {

    private LevelLoader() {
    }

    /**
     * Loads metadata and entities in a level.
     * @param level    The level to load.
     * @param filename The file to load the level from.
     * @throws IOException If the level file is not found.
     */
    /* default */ static void load(Level level, String filename) throws IOException {
        JSONObject json = JSONParser.parseJSONFile("/levels/" + filename);
        JSONArray entities = json.getJSONArray("entities");

        loadMetaData(level, json);
        loadEntities(level, entities);
    }

    /**
     * Loads the MeatData.
     * @param level    Level to load the data in.
     * @param metaData JSONObject with the metaData.
     */
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
            level.setTimer(new LevelTimer(metaData.getDouble("duration")));
        } else {
            level.setTimer(new LevelTimer());
        }

        if (metaData.has("backgroundImageURI")) {
            level.setBackgroundImage(metaData.getString("backgroundImageURI"));
        }

        if (metaData.has("backgroundMusicURI")) {
            Music.setMusic(metaData.getString("backgroundMusicURI"));
        }
    }

    /**
     * Loads the entities.
     * @param level    Level to lead the entities in.
     * @param entities SDONArray with entities.
     */
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
