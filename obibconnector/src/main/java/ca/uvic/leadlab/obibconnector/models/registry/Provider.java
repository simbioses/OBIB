package ca.uvic.leadlab.obibconnector.models.registry;

import ca.uvic.leadlab.obibconnector.utils.OBIBConnectorHelper;
import ca.uvic.leadlab.obibconnector.models.common.*;

import java.util.ArrayList;
import java.util.List;

public class Provider extends Person {

    private String status;
    private List<Id> ids = new ArrayList<>();
    private Name name;
    private List<Address> addresses = new ArrayList<>();
    private List<Telecom> telecoms = new ArrayList<>();
    private List<Clinic> clinics = new ArrayList<>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Id> getIds() {
        return ids;
    }

    public void setIds(List<Id> ids) {
        this.ids = ids;
    }

    @Override
    public void setId(String id) {
        if (this.ids == null) {
            this.ids = new ArrayList<>();
        }
        this.ids.add(new Id(id, OBIBConnectorHelper.getDefaultProviderIdType()));
    }

    public Name getName() {
        return name;
    }

    @Override
    public void setName(Name name) {
        this.name = name;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public void setAddress(Address address) {
        if (this.addresses == null) {
            this.addresses = new ArrayList<>();
        }
        this.addresses.add(address);
    }

    public List<Telecom> getTelecoms() {
        return telecoms;
    }

    public void setTelecoms(List<Telecom> telecoms) {
        this.telecoms = telecoms;
    }

    @Override
    public void setTelecom(Telecom telecom) {
        if (this.telecoms == null) {
            this.telecoms = new ArrayList<>();
        }
        this.telecoms.add(telecom);
    }

    public List<Clinic> getClinics() {
        return clinics;
    }

    public void setClinics(List<Clinic> clinics) {
        this.clinics = clinics;
    }

}
