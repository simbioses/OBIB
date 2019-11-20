package ca.uvic.leadlab.obibconnector.utils;

import ca.uvic.leadlab.obibconnector.models.common.Id;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class OBIBConnectorHelper {

    private static final Logger LOGGER = Logger.getLogger(OBIBConnectorHelper.class.getName());

    private static final Properties properties = setupProperties();

    private static final String DEFAULT_CLINIC_ID_TYPES = properties.getProperty("obib.default.clinic.id.type");
    private static final String DEFAULT_PROVIDER_ID_TYPES = properties.getProperty("obib.default.provider.id.type");
    private static final String DEFAULT_PATIENT_ID_TYPES = properties.getProperty("obib.default.patient.id.type");

    private static final String CLINIC_LOCATION_ID = properties.getProperty("cdx.clinic.location.id");

    private static Properties setupProperties() {
        String obibPath = null;
        Properties properties = new Properties();

        try { // try to get the properties file location from the JAR Manifest
            final URL jarUrl = OBIBConnectorHelper.class.getProtectionDomain().getCodeSource().getLocation();
            final JarFile jarFile = new JarFile(jarUrl.getPath());
            obibPath = jarFile.getManifest().getMainAttributes().getValue("Obib-Properties-Path");
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Could not load obibconnector.properties path from META-INF/MANIFEST.MF", e);
        }

        try {
            if (obibPath != null) { // path found? load from it
                obibPath = expandEnvironmentVariable(obibPath);
                try (InputStream inputStream = new FileInputStream(obibPath)) { // load the properties file
                    properties.load(inputStream);
                }
            } else { // otherwise, load from classpath
                LOGGER.log(Level.WARNING, "Trying to load obibconnector.properties from classpath.");
                properties.load(OBIBConnectorHelper.class.getResourceAsStream("/obibconnector.properties"));
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Could not load obibconnector.properties", e);
        }

        return properties;
    }

    /**
     * Expand any environment variable present in value. If no environment variable is found, return the value.
     *
     * @param value The String to be expanded.
     * @return String resulted from the environment variables expansion, or the value if there is no environment variable.
     */
    public static String expandEnvironmentVariable(String value) {
        Pattern p = Pattern.compile("\\$([a-zA-Z_][a-zA-Z0-9_]*)"); // pattern for a valid env. variable name
        Matcher m = p.matcher(value);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String envValue = System.getenv(m.group(1)); // get the value from the env. variable
            if (envValue != null) {
                m.appendReplacement(sb, envValue); // replace the env. variable for its value
            }
        }
        m.appendTail(sb);
        return sb.toString();
    }

    public static String getPropertyExpanded(String key) {
        return expandEnvironmentVariable(properties.getProperty(key));
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    private static String getIdByType(List<Id> ids, String type) {
        if (ids != null && type != null) {
            String[] types = type.split(";");
            for (String tp : types) {
                for (Id id : ids) {
                    if (tp.equalsIgnoreCase(id.getType()) && id.getCode() != null && !id.getCode().isEmpty()) {
                        return id.getCode();
                    }
                }
            }
        }
        return ""; // If not found, return empty string
    }

    public static String getDefaultClinicId(List<Id> clinicIds) {
        return getIdByType(clinicIds, DEFAULT_CLINIC_ID_TYPES);
    }

    public static String getDefaultProviderId(List<Id> providerIds) {
        return getIdByType(providerIds, DEFAULT_PROVIDER_ID_TYPES);
    }

    public static String getDefaultPatientId(List<Id> patientIds) {
        return getIdByType(patientIds, DEFAULT_PATIENT_ID_TYPES);
    }

    public static String getFirstName(List<String> given) {
        String firstName = "";
        for (String givenName : given) {
            firstName = String.format("%s %s", firstName, givenName); // Concatenates the first name
        }
        return firstName.trim();
    }

    public static String getDefaultClinicIdTypes() {
        String[] types = DEFAULT_CLINIC_ID_TYPES.split(";");
        if (types.length == 0) {
            throw new IllegalArgumentException("No 'Clinic ID Type' found in obib.properties.");
        }
        return types[0];
    }

    public static String getDefaultProviderIdType() {
        String[] types = DEFAULT_PROVIDER_ID_TYPES.split(";");
        if (types.length == 0) {
            throw new IllegalArgumentException("No 'Provider ID Type' found in obib.properties.");
        }
        return types[0];
    }

    public static String getDefaultPatientIdType() {
        String[] types = DEFAULT_PATIENT_ID_TYPES.split(";");
        if (types.length == 0) {
            throw new IllegalArgumentException("No 'Patient ID Type' found in obib.properties.");
        }
        return types[0];
    }

    public static String getClinicLocationId() {
        return CLINIC_LOCATION_ID;
    }

    public static String getOBIBConnectorVersion() {
        Package objPackage = OBIBConnectorHelper.class.getPackage();
        //String name = objPackage.getSpecificationTitle();
        return objPackage.getImplementationVersion();
    }
}
