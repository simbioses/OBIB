package ca.uvic.leadlab.obibconnector.utils;

import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AttachmentUtils {

    public static final String HASH_ALGORITHM = "SHA-1";

    public static boolean checkAttachment(byte[] content, String hash) {
        try {
            String contentHash = calculateHash(content);
            return contentHash.equalsIgnoreCase(hash);
        } catch (OBIBException e) {
            return false; // does not fails the process on error
        }
    }

    public static String calculateHash(byte[] content) throws OBIBException {
        try {
            MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
            byte[] hash = md.digest(content);
            return DatatypeConverter.printHexBinary(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new OBIBException("Error calculating attachment hash.", e);
        }
    }
}
