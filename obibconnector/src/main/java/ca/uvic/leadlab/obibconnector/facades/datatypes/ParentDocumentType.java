package ca.uvic.leadlab.obibconnector.facades.datatypes;

public enum ParentDocumentType {

    REPLACE("RPLC"),
    TRANSFORM("XFRM");

    public final String code;

    ParentDocumentType(String code) {
        this.code = code;
    }

    public static DocumentType fromCode(String code) {
        for (DocumentType value : DocumentType.values()) {
            if (value.code.equalsIgnoreCase(code)) {
                return value;
            }
        }
        throw new IllegalArgumentException("No ParentDocumentType enum with code = " + code);
    }
}
