package ca.uvic.leadlab.obibconnector.facades.datatypes;

public enum MediaType {

    TEXT("text/plain"),
    PDF("application/pdf"),
    RTF("text/rtf", "application/rtf"),
    JPEG("image/jpeg"),
    PNG("image/png"),
    TIFF("image/tiff");

    public final String[] mediaTypes;

    MediaType(String... mediaTypes) {
        this.mediaTypes = mediaTypes;
    }

    public String getMediaType() {
        return mediaTypes[0];
    }

    public static boolean isPlainText(String mediaType) {
        for (String type : TEXT.mediaTypes) {
            if (type.equalsIgnoreCase(mediaType)) {
                return true;
            }
        }
        return false;
    }

    public MediaType fromMediaType(String mediaType) {
        for (MediaType value : MediaType.values()) {
            for (String type : value.mediaTypes) {
                if (type.equalsIgnoreCase(mediaType)) {
                    return value;
                }
            }
        }
        throw new IllegalArgumentException("No MediaType enum with mediaType = " + mediaType);
    }
}
