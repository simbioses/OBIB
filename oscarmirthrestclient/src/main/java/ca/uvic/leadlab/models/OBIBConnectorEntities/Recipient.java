
package ca.uvic.leadlab.models.OBIBConnectorEntities;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Recipient {

    @Expose
    private List<Address> address;
    @Expose
    private String id;
    @Expose
    private Name name;
    @Expose
    private ReceivedOrganization receivedOrganization;
    @Expose
    private List<Telecom> telecom;

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

    public ReceivedOrganization getReceivedOrganization() {
        return receivedOrganization;
    }

    public void setReceivedOrganization(ReceivedOrganization receivedOrganization) {
        this.receivedOrganization = receivedOrganization;
    }

    public List<Telecom> getTelecom() {
        return telecom;
    }

    public void setTelecom(List<Telecom> telecom) {
        this.telecom = telecom;
    }

}
