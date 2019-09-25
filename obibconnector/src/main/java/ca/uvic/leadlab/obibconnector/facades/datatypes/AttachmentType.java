package ca.uvic.leadlab.obibconnector.facades.datatypes;

public enum AttachmentType {

    TEXT("text/plain"),
    PDF("application/pdf"),
    RTF("text/rtf", "application/rtf"),
    JPEG("image/jpeg"),
    PNG("image/png"),
    TIFF("image/tiff");

    public final String mediaType;
    public final String[] additionalTypes; // handle additional media types

    AttachmentType(String mediaType) {
        this.mediaType = mediaType;
        this.additionalTypes = new String[]{};
    }

    AttachmentType(String mediaType, String... additionalTypes) {
        this.mediaType = mediaType;
        this.additionalTypes = additionalTypes;
    }

    public static boolean isPlainText(String mediaType) {
        return TEXT.mediaType.equalsIgnoreCase(mediaType);
    }

    public static AttachmentType fromMediaType(String mediaType) {
        for (AttachmentType value : AttachmentType.values()) {
            if (value.mediaType.equalsIgnoreCase(mediaType)) {
                return value;
            }
            for (String type : value.additionalTypes) {
                if (type.equalsIgnoreCase(mediaType)) {
                    return value;
                }
            }
        }
        throw new IllegalArgumentException("No AttachmentType enum with mediaType = " + mediaType);
    }
}
