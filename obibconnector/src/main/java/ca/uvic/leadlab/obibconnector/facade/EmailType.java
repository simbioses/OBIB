package ca.uvic.leadlab.obibconnector.facade;

public enum EmailType {
    HOME("H"),
    WORK("WP");

    String label;

    EmailType(String label) {
        this.label = label;
    }
}
