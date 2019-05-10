package ca.uvic.leadlab.obibconnector.facades.datatypes;

public enum AttachmentType {

    PDF(MediaType.PDF.mediaType),
    RTF(MediaType.RTF.mediaType),
    JPEG(MediaType.JPEG.mediaType),
    PNG(MediaType.PNG.mediaType),
    TIFF(MediaType.TIFF.mediaType);

    public final String mediaType;

    AttachmentType(String mediaType) {
        this.mediaType = mediaType;
    }

    public static AttachmentType fromMediaType(String mediaType) {
        for (AttachmentType value : AttachmentType.values()) {
            if (value.mediaType.equalsIgnoreCase(mediaType)) {
                return value;
            }
        }
        throw new IllegalArgumentException("No AttachmentType enum with mediaType = " + mediaType);
    }
}
