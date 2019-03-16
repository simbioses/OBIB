
package ca.uvic.leadlab.obibconnector.models.OBIBConnectorEntities;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Telecom {

    @Expose
    private String type;
    @Expose
    private String use;
    @Expose
    private String value;

    public Telecom() {
    }

    public Telecom(String use, String type, String value) {
        this.use = use;
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
