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

    public static RecipientType fromLabel(String label)  {
        for (RecipientType type : RecipientType.values()) {
            if (type.label.equals(label)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No RecipientType enum with label = " + label);
    }
}
