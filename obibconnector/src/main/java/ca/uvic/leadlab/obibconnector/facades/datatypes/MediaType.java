package ca.uvic.leadlab.obibconnector.facades.datatypes;

public enum MediaType {

    TEXT("text/plain");

    public final String type;

    MediaType(String type) {
        this.type = type;
    }

    public static boolean isPlainText(String type) {
        return TEXT.type.equalsIgnoreCase(type);
    }
}
