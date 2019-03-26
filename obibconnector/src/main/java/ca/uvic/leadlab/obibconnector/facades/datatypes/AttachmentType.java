package ca.uvic.leadlab.obibconnector.facades.datatypes;

public enum AttachmentType {
    PDF("PDF"),
    RTF("RTF");


    public final String label;

    AttachmentType(String label) {
        this.label = label;
    }
}
