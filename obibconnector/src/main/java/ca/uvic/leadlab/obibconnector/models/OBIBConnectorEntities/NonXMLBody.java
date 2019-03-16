
package ca.uvic.leadlab.obibconnector.models.OBIBConnectorEntities;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class NonXMLBody {

    @Expose
    private String content;
    @Expose
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
