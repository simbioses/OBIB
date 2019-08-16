package ca.uvic.leadlab.cdxconnector;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class PropertyUtil {

    private static final Logger LOGGER = Logger.getLogger(PropertyUtil.class.getName());

    public static final Properties properties = setupProperties();

    private static Properties setupProperties() {
        Properties properties = new Properties();
        try {
            properties.load(PropertyUtil.class.getResourceAsStream("/cdxconnector.properties"));
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error loading cdx connector properties", e);
        }
        return properties;
    }

    public static String getProperty(String key, String defaultValue) {
        String value = properties.getProperty(key);
        return value != null ? value : defaultValue;
    }
}
