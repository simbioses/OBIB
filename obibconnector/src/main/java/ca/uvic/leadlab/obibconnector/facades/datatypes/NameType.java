package ca.uvic.leadlab.obibconnector.facades.datatypes;

public enum NameType {
    LEGAL("L"),
    PSEUDONYM("P"),
    ASSIGNED("A"),
    PROFESSIONAL("C"),
    HEALTHCARE_CARD("HC");

    public final String label;

    NameType(String label) {
        this.label = label;
    }

    public static NameType fromLabel(String label) {
        for (NameType type : NameType.values()) {
            if (type.label.equals(label)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No NameType enum with label = " + label);
    }
}
