package ca.uvic.leadlab.obibconnector.facades.datatypes;

public enum RecipientType {

    PRIMARY("PRCP"),
    SECONDARY("TRC");

    public final String label;

    RecipientType(String label) {
        this.label = label;
    }

    public static boolean isPrimary(String label) {
        return PRIMARY.label.equalsIgnoreCase(label);
    }
}
