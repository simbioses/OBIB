package ca.uvic.leadlab.obibconnector.impl;

import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;
import ca.uvic.leadlab.obibconnector.models.common.Id;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public abstract class ImplHelper {

    private static final Properties properties = setupProperties();

    private static final String HASH_ALGORITHM = properties.getProperty("obib.attachment.hash.algorithm");

    public static final String DEFAULT_CLINIC_ID_TYPE = properties.getProperty("obib.default.clinic.id.type");
    public static final String DEFAULT_PROVIDER_ID_TYPE = properties.getProperty("obib.default.provider.id.type");
    public static final String DEFAULT_PATIENT_ID_TYPE = properties.getProperty("obib.default.patient.id.type");

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
        return ids != null && ids.size() > 0 ? ids.get(0).getCode() :  ""; // TODO Return the first ID if there is no default?
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

    public static boolean checkAttachment(String content, String hash) {
        try {
            MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);

            byte[] contentHash = md.digest(DatatypeConverter.parseBase64Binary(content));

            return Arrays.equals(contentHash, DatatypeConverter.parseBase64Binary(hash));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace(); // TODO log this exception
            return true; // does not fails the process on error
        }
    }

    public static String calculateHash(byte[] content) throws OBIBException {
        try {
            MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);

            return DatatypeConverter.printBase64Binary(md.digest(content));
        } catch (NoSuchAlgorithmException e) {
            throw new OBIBException("Error calculating attachment hash.", e);
        }
    }
}
