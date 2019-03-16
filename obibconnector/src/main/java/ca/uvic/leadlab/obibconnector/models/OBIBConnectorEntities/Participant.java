
package ca.uvic.leadlab.obibconnector.models.OBIBConnectorEntities;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Participant implements IPerson {

    @Expose
    private List<Address> address;
    @Expose
    private String id;
    @Expose
    private Name name;
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

    public List<Telecom> getTelecom() {
        return telecom;
    }

    public void setTelecom(List<Telecom> telecom) {
        this.telecom = telecom;
    }

    @Override
    public void setGender(String gender) {
        throw new UnsupportedOperationException("Not implemented by this class");
    }

    @Override
    public void setDob(String date) {
        throw new UnsupportedOperationException("Not implemented by this class");
    }

    @Override
    public void setAddress(Address address) {
        if (this.address == null) {
            this.address = new ArrayList<>();
        }
        this.address.add(address);
    }

    @Override
    public void setTelecom(Telecom telecom) {
        if (this.telecom == null) {
            this.telecom = new ArrayList<>();
        }
        this.telecom.add(telecom);
    }

    @Override
    public void setTime(String time) {
        throw new UnsupportedOperationException("Not implemented by this class");
    }

    @Override
    public void setReceivedOrganization(ReceivedOrganization receivedOrganization) {
        throw new UnsupportedOperationException("Not implemented by this class");
    }
}
