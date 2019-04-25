package ca.uvic.leadlab.obibconnector.facades.exceptions;

import ca.uvic.leadlab.obibconnector.rest.OBIBRequestException;

public class OBIBException extends Exception {

    public OBIBException(String message) {
        super(message);
    }

    public OBIBException(String message, Throwable cause) {
        super(concatCause(message, cause), cause);
    }

    private static String concatCause(String message, Throwable cause) {
        String causeMessage = cause.getMessage();
        if (cause instanceof OBIBRequestException && causeMessage != null && !causeMessage.isEmpty()) {
            return message + " " + causeMessage; // concatenates cause only if it is an OBIBRequestException and has message
        }
        return message;
    }
}
