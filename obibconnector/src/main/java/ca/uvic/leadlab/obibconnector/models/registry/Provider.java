package ca.uvic.leadlab.obibconnector.models.registry;

import ca.uvic.leadlab.obibconnector.impl.ImplHelper;
import ca.uvic.leadlab.obibconnector.models.common.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class Provider extends Person {

    private String status;
    private String typeCode;
    private List<Id> ids = new ArrayList<>();
    private Name name;
    private List<Address> addresses = new ArrayList<>();
    private List<Telecom> telecoms = new ArrayList<>();
    private List<Clinic> clinics = new ArrayList<>();

    @JsonIgnore
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public List<Id> getIds() {
        return ids;
    }

    public void setIds(List<Id> ids) {
        this.ids = ids;
    }

    @JsonIgnore
    @Override
    public void setId(String id) {
        if (this.ids == null) {
            this.ids = new ArrayList<>();
        }
        this.ids.add(new Id(id, ImplHelper.DEFAULT_PROVIDER_ID_TYPE));
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

    @JsonIgnore
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

    @JsonIgnore
    @Override
    public void setTelecom(Telecom telecom) {
        if (this.telecoms == null) {
            this.telecoms = new ArrayList<>();
        }
        this.telecoms.add(telecom);
    }

    @JsonIgnore
    public List<Clinic> getClinics() {
        return clinics;
    }

    public void setClinics(List<Clinic> clinics) {
        this.clinics = clinics;
    }

}
