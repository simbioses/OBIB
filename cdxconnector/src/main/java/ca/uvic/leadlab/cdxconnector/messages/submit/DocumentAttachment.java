package ca.uvic.leadlab.cdxconnector.messages.submit;

import cdasubmit.IntegrityCheckAlgorithm;

import javax.xml.bind.DatatypeConverter;

public class DocumentAttachment {

    private String mediaType;
    private String content;
    private String hash;
    private String hashAlgorithm;
    private String reference;

    public DocumentAttachment() {
    }

    public DocumentAttachment(String mediaType, String content, String hash, String hashAlgorithm, String reference) {
        this.mediaType = mediaType;
        this.content = content;
        this.hash = hash;
        this.hashAlgorithm = hashAlgorithm;
        this.reference = reference;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
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

    public byte[] getHashBinary() {
        return DatatypeConverter.parseBase64Binary(hash);
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getHashAlgorithm() {
        return hashAlgorithm;
    }

    public void setHashAlgorithm(String hashAlgorithm) {
        this.hashAlgorithm = hashAlgorithm;
    }

    public IntegrityCheckAlgorithm getIntegrityCheckAlgorithm() {
        if (hashAlgorithm == null) {
            return null;
        }
        String checkAlgorithm = hashAlgorithm.replace("-", ""); // HACK: WS does not accept "SHA-1"
        return IntegrityCheckAlgorithm.fromValue(checkAlgorithm);
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
