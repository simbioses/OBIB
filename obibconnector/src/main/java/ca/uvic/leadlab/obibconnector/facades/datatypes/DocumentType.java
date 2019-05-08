package ca.uvic.leadlab.obibconnector.facades.datatypes;

public enum DocumentType {
    REFERRAL_NOTE("57133-1", "eReferral Note"),
    ADVICE_REQUEST("X10914", "Advice Request"),
    PROGRESS_NOTE("11506-3", "Progress Note"),
    PATIENT_SUMMARY("60591-5", "Patient Summary"),
    CONSULT_NOTE("11488-4", "Consult Note"),
    DISCHARGE_SUMMARY("18842-5", "Discharge Summary"),
    CARE_PLAN("81200-8", "Interdisciplinary Plan of Care"),
    INFO_REQUEST("X10916", "Information Request"),
    NOTE("34109-9", "Note"),
    NOTIFICATION("X10915","General-Purpose Notification");

    public final String label;
    public final String code;

    DocumentType(String code, String label) {
        this.label = label;
        this.code = code;
    }

    public static DocumentType fromCode(String code) {
        for (DocumentType value : DocumentType.values()) {
            if (value.code.equalsIgnoreCase(code)) {
                return value;
            }
        }
        throw new IllegalArgumentException("No DocumentType enum with code = " + code);
    }
}
