package ca.uvic.leadlab.obibconnector.facades.datatypes;

public enum AddressType {
    HOME("H"),
    WORK("WP"),
    TEMPORARY("TEMP"),
    POSTAL_ADDRESS("PST");

    public final String label;

    AddressType(String label) {
        this.label = label;
    }

    public static AddressType fromLabel(String label) {
        for (AddressType type : AddressType.values()) {
            if (type.label.equals(label)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No AddressType enum with label = " + label);
    }
}
