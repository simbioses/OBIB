package ca.uvic.leadlab.obibconnector.models.document;

public class NonXMLBody {

    private String content; // also utilized as reference for attachment
    private String mediaType;
    private String integrityCheck;
    private String integrityCheckAlgorithm;

    public NonXMLBody() {
    }

    public NonXMLBody(String content, String mediaType) {
        this.content = content;
        this.mediaType = mediaType;
    }

    public NonXMLBody(String content, String mediaType, String integrityCheck, String integrityCheckAlgorithm) {
        this.content = content;
        this.mediaType = mediaType;
        this.integrityCheck = integrityCheck;
        this.integrityCheckAlgorithm = integrityCheckAlgorithm;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getIntegrityCheck() {
        return integrityCheck;
    }

    public void setIntegrityCheck(String integrityCheck) {
        this.integrityCheck = integrityCheck;
    }

    public String getIntegrityCheckAlgorithm() {
        return integrityCheckAlgorithm;
    }

    public void setIntegrityCheckAlgorithm(String integrityCheckAlgorithm) {
        this.integrityCheckAlgorithm = integrityCheckAlgorithm;
    }
}
