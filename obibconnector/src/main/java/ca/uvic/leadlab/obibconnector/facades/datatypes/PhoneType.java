package ca.uvic.leadlab.obibconnector.facades.datatypes;

public enum PhoneType {
    HOME("H"),
    WORK("WP"),
    MOBILE("M");

    public final String label;

    PhoneType(String label) {
        this.label = label;
    }
}
