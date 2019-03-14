
package ca.uvic.leadlab.models.OBIBConnectorEntities;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Author {

    @Expose
    private List<Address> address;
    @Expose
    private String id;
    @Expose
    private Name name;
    @Expose
    private List<Telecom> telecom;
    @Expose
    private String time;

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public List<Telecom> getTelecom() {
        return telecom;
    }

    public void setTelecom(List<Telecom> telecom) {
        this.telecom = telecom;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
