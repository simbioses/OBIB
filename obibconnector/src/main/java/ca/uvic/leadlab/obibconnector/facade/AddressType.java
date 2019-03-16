package ca.uvic.leadlab.obibconnector.facade;

public enum AddressType {
    HOME("H"),
    WORK("WP"),
    TEMPORARY("TEMP"),
    POSTAL_ADDRESS("PST");

    String label;

    AddressType(String label) {
        this.label = label;
    }
}
