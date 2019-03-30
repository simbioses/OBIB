package ca.uvic.leadlab.obibconnector.facades.exceptions;

public class OBIBException extends Exception {

    public OBIBException(String message) {
        super(message);
    }

    public OBIBException(String message, Throwable cause) {
        super(message, cause);
    }
}
