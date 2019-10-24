package ca.uvic.leadlab.obibconnector.facades.datatypes;

public enum DocumentStatus {
    COMPLETED("completed"),
    SIGNED("signed"),
    ACTIVE("active"),
    APPENDED("appended"),
    CORRECTED("corrected"),
    UPDATED("updated"),
    ABORTED("aborted"),
    CANCELLED("cancelled"),
    NEW("new"),
    NORMAL("normal"),
    HELD("held"),
    SUSPENDED("suspended"),
    NULLIFIED("nullified"),
    OBSOLETE("obsolete"),
    UNRECOGNIZED("unrecognized");

    public final String code;

    DocumentStatus(String code) {
        this.code = code;
    }

    public static DocumentStatus fromCode(String code) {
        for (DocumentStatus value : DocumentStatus.values()) {
            if (value.code.equalsIgnoreCase(code)) {
                return value;
            }
        }
        throw new IllegalArgumentException("No DocumentStatus enum with code = " + code);
    }
}
