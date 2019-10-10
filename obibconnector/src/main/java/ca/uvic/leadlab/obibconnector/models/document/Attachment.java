package ca.uvic.leadlab.obibconnector.models.document;

public class Attachment {

    private String hash;
    private String mediaType;
    private byte[] content;
    private String reference;

    public Attachment() {
    }

    public Attachment(String hash, String mediaType, byte[] content, String reference) {
        this.hash = hash;
        this.mediaType = mediaType;
        this.content = content;
        this.reference = reference;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
