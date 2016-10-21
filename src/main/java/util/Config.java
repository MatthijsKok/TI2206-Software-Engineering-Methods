package util;

import util.logging.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class reads the config file and makes the configuration available to everyone.
 */
public final class Config {

    /**
     * The logger access point to which everything will be logged.
     */
    private static final Logger LOGGER = Logger.getInstance();

    /**
     * The properties object wherein all default config properties are defined.
     */
    private static final Properties DEFAULT_PROPERTIES;

    /**
     * The properties object wherein all config properties will be loaded.
     */
    private static final Properties PROPERTIES;

    /**
     * The file containing all config properties.
     */
    private static final String PROPERTIES_FILE_NAME = "properties/settings.properties";

    /**
     * The file containing all default config properties.
     */
    private static final String DEFAULT_PROPERTIES_FILE_NAME = "properties/default_settings.properties";

    static {
        DEFAULT_PROPERTIES = new Properties();
        try (InputStream inputStream = createInputStream(DEFAULT_PROPERTIES_FILE_NAME)) {
            DEFAULT_PROPERTIES.load(inputStream);
            LOGGER.info("Default settings loaded.");
        } catch (IOException e) {
            LOGGER.error("Default settings could not be loaded.");
        }

        // Load Default Properties into Properties, before overriding with config.
        // So that all values that are not defined are standard.
        PROPERTIES = DEFAULT_PROPERTIES;

        try (InputStream inputStream = createInputStream(PROPERTIES_FILE_NAME)) {
            PROPERTIES.load(inputStream);
            LOGGER.info("Settings loaded.");
        } catch (IOException e) {
            LOGGER.warn("Settings could not be loaded. Using default settings instead.");
        }
    }

    private Config() {

    }

    private static InputStream createInputStream(String fileName) throws IOException {
        return new FileInputStream(
                new File(
                        Config.class.getClassLoader().getResource(fileName).getFile()
                )
        );
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

    /**
     * Stores the key and value in the properties file.
     * @param key The Key.
     * @param value The Value.
     */
    public static void put(String key, String value) {
        PROPERTIES.setProperty(key, value);
        try {
            FileWriter fileWriter = new FileWriter(PROPERTIES_FILE_NAME);
            PROPERTIES.store(fileWriter, "Player input properties");
            fileWriter.close();
        } catch (IOException e) {
            LOGGER.error("Could not write to properties file.");
        }
    }
}
