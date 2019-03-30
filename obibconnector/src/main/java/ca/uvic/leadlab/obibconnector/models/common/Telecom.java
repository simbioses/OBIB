package ca.uvic.leadlab.obibconnector.models.common;

public class Telecom {

    private String use;
    private String type;
    private String value;

    public Telecom() {
    }

    public Telecom(String use, String type, String value) {
        this.use = use;
        this.type = type;
        this.value = value;
    }

    public boolean isPhone() {
        return "tel".equalsIgnoreCase(type);
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
