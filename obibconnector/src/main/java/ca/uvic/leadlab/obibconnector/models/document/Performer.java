package ca.uvic.leadlab.obibconnector.models.document;

import ca.uvic.leadlab.obibconnector.models.registry.Provider;

public class Performer extends Provider {

    private String typeCode;
    private String code;

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
