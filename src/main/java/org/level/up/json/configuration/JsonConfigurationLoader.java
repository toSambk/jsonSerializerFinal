package org.level.up.json.configuration;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * Class, which contains methods for loading configuration from file.
 *
 * @author protsko on 19.04.2019
 */
class JsonConfigurationLoader {

    /**
     * Name of configuration file
     */
    private static final String CONFIGURATION_FILE_NAME = "json.properties";

    /**
     * Property key of value, which will be used in searching classes
     */
    private static final String FILE_PATH_SETTING_KEY = "directory.scan";

    static Configuration loadConfiguration() {
        try {
            ClassLoader classLoader = JsonConfiguration.class.getClassLoader();
            // If we couldn't find class, then throw exception
            URI uri = Optional.ofNullable(classLoader.getResource(CONFIGURATION_FILE_NAME))
                    .orElseThrow(() -> new RuntimeException("Couldn't find json.properties file"))
                    .toURI();
            File configurationFile = new File(uri);
            return readConfiguration(configurationFile);

        } catch (IOException | URISyntaxException exc) {
            throw new RuntimeException(exc);
        }
    }

    private static Configuration readConfiguration(File configurationFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(configurationFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] settings = line.split("=");
                // Checks length for exclude empty lines from processing
                if (settings.length == 2 && settings[0].equalsIgnoreCase(FILE_PATH_SETTING_KEY)) {
                    return new Configuration(settings[1]);
                }
            }
        }

        // If we didn't find special property, then throw exception
        throw new RuntimeException("No \"directory.scan\" property in configuration file");
    }

}
