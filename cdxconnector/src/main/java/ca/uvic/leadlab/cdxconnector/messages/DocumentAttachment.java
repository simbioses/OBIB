package ca.uvic.leadlab.cdxconnector.messages;

import org.hl7.v3.MediaType;

public class DocumentAttachment {

    private String type;
    private byte[] content;
    private byte[] hash;
    private String reference;

    public DocumentAttachment() {
    }

    public DocumentAttachment(String type, byte[] content, byte[] hash, String reference) {
        this.type = type;
        this.content = content;
        this.hash = hash;
        this.reference = reference;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public byte[] getHash() {
        return hash;
    }

    public void setHash(byte[] hash) {
        this.hash = hash;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public MediaType getMediaType() {
        return MediaType.fromValue(type);
    }
}
