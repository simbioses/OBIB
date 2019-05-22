package ca.uvic.leadlab.obibconnector.models.document;

import ca.uvic.leadlab.obibconnector.models.common.Id;

public class ParentDocument {

    private String type;
    private Id id;
    private Id setId;
    private int versionNumber;
    private String mediaType;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Id getSetId() {
        return setId;
    }

    public void setSetId(Id setId) {
        this.setId = setId;
    }

    public int getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(int versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
}
