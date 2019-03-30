package ca.uvic.leadlab.obibconnector.models.response;

import ca.uvic.leadlab.obibconnector.models.registry.Clinic;

import java.util.ArrayList;
import java.util.List;

public class ListClinicsResponse extends OBIBResponse {

    private List<Clinic> clinics = new ArrayList<>();

    public List<Clinic> getClinics() {
        return clinics;
    }

    public void setClinics(List<Clinic> clinics) {
        this.clinics = clinics;
    }
}
