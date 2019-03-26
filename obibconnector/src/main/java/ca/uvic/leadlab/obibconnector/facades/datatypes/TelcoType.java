package ca.uvic.leadlab.obibconnector.facades.datatypes;

public enum TelcoType {
    HOME("H"),
    WORK("W"),
    MOBILE("M");

    public final String label;

    TelcoType(String label) {
        this.label = label;
    }
}
