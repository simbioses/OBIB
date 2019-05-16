package ca.uvic.leadlab.obibconnector.rest;

import java.util.Map;

public class OBIBRequestException extends Exception {

    private Map<String, String> obibErrors;

    public OBIBRequestException(String message) {
        super(message);
    }

    public OBIBRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public OBIBRequestException(String message, Map<String, String> obibErrors) {
        super(message + " " + obibErrors);
        this.obibErrors = obibErrors;
    }

    public OBIBRequestException(String message, Map<String, String> obibErrors, Throwable cause) {
        super(message + " " + obibErrors, cause);
        this.obibErrors = obibErrors;
    }

    public Map<String, String> getObibErrors() {
        return obibErrors;
    }
}
