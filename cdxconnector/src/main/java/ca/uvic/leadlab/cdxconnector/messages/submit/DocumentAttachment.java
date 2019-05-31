package ca.uvic.leadlab.cdxconnector.messages.submit;

import cdasubmitrequest.*;

public class DocumentAttachment {

    private String type;
    private String content;
    private String hash;
    private String reference;

    public DocumentAttachment() {
    }

    public DocumentAttachment(String type, String content, String hash, String reference) {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
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
