package ca.uvic.leadlab.obibconnector.facade;

public enum Gender {
    MALE("M"),
    FEMALE("F");

    public final String label;

    Gender(String label) {
        this.label = label;
    }
}
