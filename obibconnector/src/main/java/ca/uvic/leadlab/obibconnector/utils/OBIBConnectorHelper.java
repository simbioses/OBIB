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

    private static Properties setupProperties() {
        String obibPath = null;
        Properties properties = new Properties();
        try {
            // get the properties file location from the JAR Manifest file
            final URL jarUrl = OBIBConnectorHelper.class.getProtectionDomain().getCodeSource().getLocation();
            final JarFile jarFile = new JarFile(jarUrl.getPath());
            obibPath = jarFile.getManifest().getMainAttributes().getValue("Obib-Properties-Path");
            // convert any environment variable
            Pattern p = Pattern.compile("\\$([a-zA-Z_][a-zA-Z0-9_]*)"); // pattern for a valid env. variable name
            Matcher m = p.matcher(obibPath);
            StringBuffer sb = new StringBuffer();
            while (m.find()) {
                String envValue = System.getenv(m.group(1)); // get the value from the env. variable
                if (envValue != null) {
                    m.appendReplacement(sb, envValue); // replace the env. variable for its value
                }
            }
            m.appendTail(sb);
            obibPath = sb.toString();
            // load the properties file
            try (InputStream inputStream = new FileInputStream(obibPath)) {
                properties.load(inputStream);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error: could not load properties from file " + obibPath, e);
            try {
                properties.load(OBIBConnectorHelper.class.getResourceAsStream("/obibconnector.properties")); // used on tests
            } catch (Exception ignore) { /* ignored */ }
        }
        return properties;
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

    public static String getOBIBConnectorVersion() {
        Package objPackage = OBIBConnectorHelper.class.getPackage();
        //String name = objPackage.getSpecificationTitle();
        return objPackage.getImplementationVersion();
    }
}
