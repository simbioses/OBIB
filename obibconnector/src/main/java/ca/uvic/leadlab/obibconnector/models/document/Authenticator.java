package ca.uvic.leadlab.obibconnector.models.document;

import ca.uvic.leadlab.obibconnector.models.common.Address;
import ca.uvic.leadlab.obibconnector.models.common.Name;
import ca.uvic.leadlab.obibconnector.models.common.Telecom;

import java.util.List;

public class Authenticator {

    private List<Address> address;
    private String id;
    private Name name;
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

    public List<Telecom> getTelecom() {
        return telecom;
    }

    public void setTelecom(List<Telecom> telecom) {
        this.telecom = telecom;
    }

}
