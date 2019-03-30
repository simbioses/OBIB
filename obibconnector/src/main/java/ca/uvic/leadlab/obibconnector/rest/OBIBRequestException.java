package ca.uvic.leadlab.obibconnector.rest;

public class OBIBRequestException extends Exception {

    public OBIBRequestException(String message) {
        super(message);
    }

    public OBIBRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
