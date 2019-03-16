package ca.uvic.leadlab.obibconnector.facade;

public enum NameType {
    LEGAL("L"),
    PSEUDONYM("P"),
    ASSIGNED("A"),
    PROFESSIONAL("C"),
    HEALTHCARE_CARD("HC");

    String label;

    NameType(String label) {
        this.label = label;
    }
}
