package ca.uvic.leadlab.obibconnector.models.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Id {

    private String code;
    private String type;

    public Id() {
    }

    public Id(String code, String type) {
        this.code = code;
        this.type = type;
    }

    @JsonIgnore
    public String getConcatenatedID() {
        String concatenatedId = "";
        if (type != null && type.length() > 0) {
            concatenatedId = type + ".";
        }
        if (code != null && code.length() > 0) {
            return concatenatedId + code;
        }
        return null;
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
