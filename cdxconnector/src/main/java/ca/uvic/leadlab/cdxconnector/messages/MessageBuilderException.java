package ca.uvic.leadlab.cdxconnector.messages;

public class MessageBuilderException extends Exception {

    public MessageBuilderException(String message) {
        super(message);
    }

    public MessageBuilderException(String message, Throwable cause) {
        super(message, cause);
    }
}
