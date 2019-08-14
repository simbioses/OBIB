package ca.uvic.leadlab.obibconnector.facades.exceptions;

import ca.uvic.leadlab.obibconnector.rest.OBIBRequestException;

import java.util.Map;
import java.util.Properties;

public class OBIBException extends Exception {

    private static final Properties properties = setupProperties();

    private String obibMessage;
    private Map<String, String> obibErrors;

    private static Properties setupProperties() {
        Properties properties = new Properties();
        try {
            properties.load(OBIBException.class.getResourceAsStream("/obibmessages.properties"));
        } catch (Exception e) {
            e.printStackTrace(); // TODO log this exception
        }
        return properties;
    }

    public OBIBException(String message) {
        super(message);
    }

    public OBIBException(String message, Throwable cause) {
        super(concatCause(message, cause), cause);

        if (cause instanceof OBIBRequestException) {
            this.obibMessage = cause.getMessage();
            this.obibErrors = ((OBIBRequestException) cause).getObibErrors();
        }
    }

    private static String concatCause(String message, Throwable cause) {
        String causeMessage = cause.getMessage();
        if (cause instanceof OBIBRequestException && causeMessage != null && !causeMessage.isEmpty()) {
            return message + " Cause: " + causeMessage; // concatenates cause only if it is an OBIBRequestException and has message
        }
        return message;
    }

    public String getObibMessage() {
        StringBuilder message = new StringBuilder();
        if (obibMessage != null && !obibMessage.isEmpty()) {
            message.append(obibMessage);
        }
        if (obibErrors != null && !obibErrors.isEmpty()) {
            message.append(" Details: ");
            for (String key : obibErrors.keySet()) {
                String value = properties.getProperty(key);
                message.append(value != null ? value : obibErrors.get(key)).append("; ");
            }
        }
        return message.toString();
    }
}
