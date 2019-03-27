package ca.uvic.leadlab.obibconnector.models.document;

import ca.uvic.leadlab.obibconnector.models.common.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Recipient implements IPerson {

    private List<Id> id;
    private List<Address> address;
    private List<Telecom> telecom;
    private Name name;
    private ReceivedOrganization receivedOrganization;

    public List<Id> getId() {
        return id;
    }

    public void setId(List<Id> id) {
        this.id = id;
    }

    @Override
    public void addId(String id) {
        if (this.id == null) {
            this.id = new ArrayList<>();
        }
        this.id.add(new Id(id, null));
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    @Override
    public void addAddress(Address address) {
        if (this.address == null) {
            this.address = new ArrayList<>();
        }
        this.address.add(address);
    }

    public List<Telecom> getTelecom() {
        return telecom;
    }

    public void setTelecom(List<Telecom> telecom) {
        this.telecom = telecom;
    }

    @Override
    public void addTelecom(Telecom telecom) {
        if (this.telecom == null) {
            this.telecom = new ArrayList<>();
        }
        this.telecom.add(telecom);
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    @Override
    public void addName(Name name) {
        setName(name);
    }

    public ReceivedOrganization getReceivedOrganization() {
        return receivedOrganization;
    }

    @Override
    public void setReceivedOrganization(ReceivedOrganization receivedOrganization) {
        this.receivedOrganization = receivedOrganization;
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
    public void setTime(Date time) {
        throw new UnsupportedOperationException("Not implemented by this class");
    }
}
