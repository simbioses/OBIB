package ca.uvic.leadlab.cdxconnector;

import ca.uvic.leadlab.cdxconnector.messages.exception.MessageBuilderException;

public class ConnectorException extends Exception {

    public ConnectorException(String message) {
        super("CDXConnector - " + message);
    }

    public ConnectorException(String message, Throwable cause) {
        super("CDXConnector - " + concatCause(message, cause), cause);
    }

    private static String concatCause(String message, Throwable cause) {
        return message + " Cause: " + cause.getMessage().replaceAll("[\\t\\n\\r]+"," ");
    }
}
