package ca.uvic.leadlab.obibconnector.models.document;

import ca.uvic.leadlab.obibconnector.models.common.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {

    private List<Id> ids = new ArrayList<>();
    private List<Name> names = new ArrayList<>();
    private List<Address> addresses = new ArrayList<>();
    private List<Telecom> telecoms = new ArrayList<>();
    private String genderCode;
    private String birthday;

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
        this.ids.add(new Id(id, null));
    }

    public List<Name> getNames() {
        return names;
    }

    public void setNames(List<Name> names) {
        this.names = names;
    }

    @JsonIgnore
    @Override
    public void setName(Name name) {
        if (this.names == null) {
            this.names = new ArrayList<>();
        }
        this.names.add(name);
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

    public String getGenderCode() {
        return genderCode;
    }

    public void setGenderCode(String genderCode) {
        this.genderCode = genderCode;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
