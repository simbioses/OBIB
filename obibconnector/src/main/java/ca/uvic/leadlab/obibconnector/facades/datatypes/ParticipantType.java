package ca.uvic.leadlab.obibconnector.facades.datatypes;

public enum ParticipantType {

    IND("IND"),
    REF("REF");

    public final String label;

    ParticipantType(String label) {
        this.label = label;
    }

    public static ParticipantType fromLabel(String label) {
        for (ParticipantType type : ParticipantType.values()) {
            if (type.label.equals(label)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No ParticipantType enum with label = " + label);
    }
}
