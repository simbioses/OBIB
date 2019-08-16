package ca.uvic.leadlab.obibconnector.utils;

import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class AttachmentUtils {

    public static final String HASH_ALGORITHM = "SHA-1";

    public static boolean checkAttachment(String content, String hash) {
        try {
            MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);

            byte[] contentHash = md.digest(DatatypeConverter.parseBase64Binary(content));

            return Arrays.equals(contentHash, DatatypeConverter.parseBase64Binary(hash));
        } catch (NoSuchAlgorithmException e) {
            return false; // does not fails the process on error
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
