package ca.uvic.leadlab.cdxconnector;

import java.util.Properties;

public abstract class PropertyUtil {

    public static final Properties properties = setupProperties();

    private static Properties setupProperties() {
        Properties properties = new Properties();
        try {
            properties.load(PropertyUtil.class.getResourceAsStream("/cdxconnector.properties"));
        } catch (Exception e) {
            e.printStackTrace(); // TODO log this exception
        }
        return properties;
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
