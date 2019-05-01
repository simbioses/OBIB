package ca.uvic.leadlab.obibconnector.facades.datatypes;

public enum TelcoType {
    HOME("H"),
    WORK("W"),
    MOBILE("M");

    public final String label;

    TelcoType(String label) {
        this.label = label;
    }

    public static TelcoType fromLabel(String label) {
        for (TelcoType type : TelcoType.values()) {
            if (type.label.equals(label)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No TelcoType enum with label = " + label);
    }
}
