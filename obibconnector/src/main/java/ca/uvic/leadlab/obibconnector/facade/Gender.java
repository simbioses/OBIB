package ca.uvic.leadlab.obibconnector.facade;

public enum Gender {
    MALE("M"),
    FEMALE("F");

    String label;

    Gender(String label) {
        this.label = label;
    }
}
