package ca.uvic.leadlab.obibconnector.models.common;

public class Id {

    private String id;
    private String type;

    public Id() {
    }

    public Id(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
