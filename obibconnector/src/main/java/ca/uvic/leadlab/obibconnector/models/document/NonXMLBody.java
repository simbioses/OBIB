package ca.uvic.leadlab.obibconnector.models.document;

public class NonXMLBody {

    private String content;
    private String mediaType;

    public NonXMLBody() {
    }

    public NonXMLBody(String content, String mediaType) {
        this.content = content;
        this.mediaType = mediaType;
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

}
