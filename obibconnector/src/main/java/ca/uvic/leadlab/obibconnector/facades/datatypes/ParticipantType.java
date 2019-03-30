package ca.uvic.leadlab.obibconnector.facades.datatypes;

public enum ParticipantType {

    IND("IND"),
    REF("REF");

    public final String label;

    ParticipantType(String label) {
        this.label = label;
    }
}
