package ca.uvic.leadlab.obibconnector.models.common;

public class Id {

    private String code;
    private String type;

    public Id() {
    }

    public Id(String code, String type) {
        this.code = code;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
