package ca.uvic.leadlab.obibconnector.facades.datatypes;

public enum PerformerType {

    PRIMARY("PPRF"),
    SECONDARY("SPRF");

    public final String label;

    PerformerType(String label) {
        this.label = label;
    }

    public static boolean isPrimary(String label) {
        return PRIMARY.label.equalsIgnoreCase(label);
    }

    public static PerformerType fromLabel(String label)  {
        for (PerformerType type : PerformerType.values()) {
            if (type.label.equals(label)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No PerformerType enum with label = " + label);
    }
}
