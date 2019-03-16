
package ca.uvic.leadlab.obibconnector.models.OBIBConnectorEntities;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Name {

    @Expose
    private String family;
    @Expose
    private List<String> given;
    @Expose
    private String prefix;
    @Expose
    private String suffix;
    @Expose
    private String use;

    public Name() {
    }

    public Name(String use, String family, String given, String prefix, String suffix) {
        this.use = use;
        this.family = family;
        this.addGiven(given);
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public List<String> getGiven() {
        return given;
    }

    public void setGiven(List<String> given) {
        this.given = given;
    }

    public void addGiven(String given) {
        if (this.given == null) {
            this.given = new ArrayList<>();
        }
        this.given.add(given);
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

}
