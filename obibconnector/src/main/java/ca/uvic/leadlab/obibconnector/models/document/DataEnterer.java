package ca.uvic.leadlab.obibconnector.models.document;

import ca.uvic.leadlab.obibconnector.models.common.Address;
import ca.uvic.leadlab.obibconnector.models.common.IPerson;
import ca.uvic.leadlab.obibconnector.models.common.Name;
import ca.uvic.leadlab.obibconnector.models.common.Telecom;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataEnterer implements IPerson {

    private List<Address> address;
    private String id;
    private Name name;
    private List<Telecom> telecom;
    private Date time;

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void addId(String id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void addName(Name name) {
        this.name = name;
    }

    public List<Telecom> getTelecom() {
        return telecom;
    }

    public void setTelecom(List<Telecom> telecom) {
        this.telecom = telecom;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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
    public void addAddress(Address address) {
        if (this.address == null) {
            this.address = new ArrayList<>();
        }
        this.address.add(address);
    }

    @Override
    public void addTelecom(Telecom telecom) {
        if (this.telecom == null) {
            this.telecom = new ArrayList<>();
        }
        this.telecom.add(telecom);
    }

    @Override
    public void setReceivedOrganization(ReceivedOrganization receivedOrganization) {
        throw new UnsupportedOperationException("Not implemented by this class");
    }
}
