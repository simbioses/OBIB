package ca.uvic.leadlab.obibconnector.facades.datatypes;

public enum AttachmentType {

    PDF(MediaType.PDF.mediaTypes),
    RTF(MediaType.RTF.mediaTypes),
    JPEG(MediaType.JPEG.mediaTypes),
    PNG(MediaType.PNG.mediaTypes),
    TIFF(MediaType.TIFF.mediaTypes);

    public final String[] mediaTypes;

    AttachmentType(String... mediaTypes) {
        this.mediaTypes = mediaTypes;
    }

    public String getMediaType() {
        return mediaTypes[0];
    }

    public static AttachmentType fromMediaType(String mediaType) {
        for (AttachmentType value : AttachmentType.values()) {
            for (String type : value.mediaTypes) {
                if (type.equalsIgnoreCase(mediaType)) {
                    return value;
                }
            }
        }
        throw new IllegalArgumentException("No AttachmentType enum with mediaType = " + mediaType);
    }
}
