package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class reads the config file and makes the configuration available to everyone.
 */
public final class Config {

    private Config() {

    }

    /**
     * The properties object wherein all config properties will be loaded.
     */
    private static final Properties PROPERTIES = new Properties();

    /**
     * The file containing all config properties.
     */
    private static final String PROP_FILE_NAME = "src/main/resources/config.properties";

    /**
     * Loads the properties file into its properties field.
     * @throws IOException if the properties file cannot be read.
     */
    public static void load() throws IOException {
        InputStream input = new FileInputStream(PROP_FILE_NAME);
        PROPERTIES.load(input);
        input.close();
    }

    /**
     * Gets the property value corresponding to the key.
     * @param key the key to check.
     * @return the value corresponding to the key.
     */
    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
}
