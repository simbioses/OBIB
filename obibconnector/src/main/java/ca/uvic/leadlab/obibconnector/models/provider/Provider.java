package ca.uvic.leadlab.obibconnector.models.provider;

import ca.uvic.leadlab.obibconnector.models.clinic.Clinic;
import ca.uvic.leadlab.obibconnector.models.common.Id;
import ca.uvic.leadlab.obibconnector.models.common.Name;

import java.util.List;

public class Provider {

    private String status;
    private List<Id> id;
    private Name name;
    private List<Clinic> clinics;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Id> getId() {
        return id;
    }

    public void setId(List<Id> id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public List<Clinic> getClinics() {
        return clinics;
    }

    public void setClinics(List<Clinic> clinics) {
        this.clinics = clinics;
    }
}
