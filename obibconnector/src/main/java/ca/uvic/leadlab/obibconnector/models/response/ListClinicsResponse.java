package ca.uvic.leadlab.obibconnector.models.response;

import ca.uvic.leadlab.obibconnector.models.clinic.Clinic;

import java.util.List;

public class ListClinicsResponse extends OBIBResponse {

    private List<Clinic> clinics;

    public List<Clinic> getClinics() {
        return clinics;
    }

    public void setClinics(List<Clinic> clinics) {
        this.clinics = clinics;
    }
}
