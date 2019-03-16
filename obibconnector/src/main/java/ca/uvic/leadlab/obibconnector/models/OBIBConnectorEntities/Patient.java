
package ca.uvic.leadlab.obibconnector.models.OBIBConnectorEntities;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Patient implements IPerson {

    @Expose
    private List<Address> address;
    @Expose
    private String dob;
    @Expose
    private String gender;
    @Expose
    private String id;
    @Expose
    private List<Name> name;
    @Expose
    private List<Telecom> telecom;

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Name> getName() {
        return name;
    }

    public void setName(List<Name> name) {
        this.name = name;
    }

    public List<Telecom> getTelecom() {
        return telecom;
    }

    public void setTelecom(List<Telecom> telecom) {
        this.telecom = telecom;
    }

    @Override
    public void setName(Name name) {
        if (this.name == null) {
            this.name = new ArrayList<>();
        }
        this.name.add(name);
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
