package ca.uvic.leadlab.obibconnector.facades.datatypes;

public enum Gender {
    MALE("M"),
    FEMALE("F"),
    UNDIFFERENTIATED("U");

    public final String label;

    Gender(String label) {
        this.label = label;
    }

    public static Gender fromLabel(String label) {
        for (Gender type : Gender.values()) {
            if (type.label.equals(label)) {
                return type;
            }
        }
        return UNDIFFERENTIATED;
    }
}
