package ca.uvic.leadlab.obibconnector.facades.datatypes;

public enum MediaType {

    TEXT("text/plain"),
    PDF("application/pdf"),
    RTF("text/rtf"),
    JPEG("image/jpeg"),
    PNG("image/png"),
    TIFF("image/tiff");

    public final String mediaType;

    MediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public static boolean isPlainText(String mediaType) {
        return TEXT.mediaType.equalsIgnoreCase(mediaType);
    }

    public MediaType fromMediaType(String mediaType) {
        for (MediaType value : MediaType.values()) {
            if (value.mediaType.equalsIgnoreCase(mediaType)) {
                return value;
            }
        }
        throw new IllegalArgumentException("No MediaType enum with mediaType = " + mediaType);
    }
}
