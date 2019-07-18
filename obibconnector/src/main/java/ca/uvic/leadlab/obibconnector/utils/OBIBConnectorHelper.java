package ca.uvic.leadlab.obibconnector.utils;

import ca.uvic.leadlab.obibconnector.models.common.Id;

import java.util.List;
import java.util.Properties;

public abstract class OBIBConnectorHelper {

    public static final Properties properties = setupProperties();

    public static final String DEFAULT_CLINIC_ID_TYPE = properties.getProperty("obib.default.clinic.id.type");
    public static final String DEFAULT_PROVIDER_ID_TYPE = properties.getProperty("obib.default.provider.id.type");
    public static final String DEFAULT_PATIENT_ID_TYPE = properties.getProperty("obib.default.patient.id.type");

    private static Properties setupProperties() {
        Properties properties = new Properties();
        try {
            properties.load(OBIBConnectorHelper.class.getResourceAsStream("/obibconnector.properties"));
        } catch (Exception e) {
            e.printStackTrace(); // TODO log this exception
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
                    if (tp.equalsIgnoreCase(id.getType())
                            && id.getCode() != null && !id.getCode().isEmpty()) {
                        return id.getCode();
                    }
                }
            }
        }
        return ""; // If not found, return empty string
    }

    public static String getDefaultClinicId(List<Id> clinicIds) {
        return getIdByType(clinicIds, DEFAULT_CLINIC_ID_TYPE);
    }

    public static String getDefaultProviderId(List<Id> providerIds) {
        return getIdByType(providerIds, DEFAULT_PROVIDER_ID_TYPE);
    }

    public static String getDefaultPatientId(List<Id> patientIds) {
        return getIdByType(patientIds, DEFAULT_PATIENT_ID_TYPE);
    }

    public static String getFirstName(List<String> given) {
        String firstName = "";
        for (String givenName : given) {
            firstName = String.format("%s %s", firstName, givenName); // Concatenates the first name
        }
        return firstName.trim();
    }

}
