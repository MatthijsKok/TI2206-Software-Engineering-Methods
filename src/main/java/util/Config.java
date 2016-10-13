package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class reads the config file and makes the configuration available to everyone.
 */
public final class Config {

    /**
     * The properties object wherein all config properties will be loaded.
     */
    private static final Properties PROPERTIES;
    /**
     * The file containing all config properties.
     */
    private static final String PROP_FILE_NAME = "src/main/resources/config.properties";

    static {
        Properties fallback = new Properties();
        fallback.put("key", "default");

        PROPERTIES = new Properties(fallback);
        try {
            try (InputStream stream = new FileInputStream(PROP_FILE_NAME)) {
                PROPERTIES.load(stream);
            }
        } catch (IOException ex) {
            System.exit(1);
        }
    }


    private Config() {

    }

    /**
     * Gets the property value corresponding to the key.
     *
     * @param key the key to check.
     * @return the value corresponding to the key.
     */
    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
}
