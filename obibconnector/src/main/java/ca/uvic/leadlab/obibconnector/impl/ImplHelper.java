package ca.uvic.leadlab.obibconnector.impl;

import ca.uvic.leadlab.obibconnector.models.common.Id;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Properties;

public abstract class ImplHelper {

    private static final Properties properties = setupProperties();

    private static final String CHECK_ALGORITHM = properties.getProperty("obib.attachment.check.algorithm");

    private static final String DEFAULT_CLINIC_ID_TYPE = properties.getProperty("obib.default.clinic.id.type");
    private static final String DEFAULT_PROVIDER_ID_TYPE = properties.getProperty("obib.default.provider.id.type");
    private static final String DEFAULT_PATIENT_ID_TYPE = properties.getProperty("obib.default.patient.id.type");

    private static Properties setupProperties() {
        Properties properties = new Properties();
        try {
            properties.load(ImplHelper.class.getResourceAsStream("/obib_impl.properties"));
        } catch (Exception e) {
            e.printStackTrace(); // TODO log this exception
        }
        return properties;
    }

    private static String getIdByType(List<Id> ids, String type) {
        if (ids != null && type != null) {
            for (Id id : ids) {
                if (type.equalsIgnoreCase(id.getType())) {
                    return id.getCode();
                }
            }
        }
        return ""; // TODO what if there is no default id type?
    }

    public static boolean checkAttachment(byte[] content, String hash) {
        if (CHECK_ALGORITHM == null) {
            return true; // does not validate if there is no algorithm specified
        }
        try {
            MessageDigest md = MessageDigest.getInstance(CHECK_ALGORITHM);

            byte[] contentDigest = md.digest(content);
            StringBuilder sb = new StringBuilder();
            for (byte b : contentDigest) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            String contentHash = sb.toString();

            return contentHash.equals(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace(); // TODO log this exception
            return true; // does not fails the process on error
        }
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
}
