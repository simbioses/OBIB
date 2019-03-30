package ca.uvic.leadlab.obibconnector.impl.registry;

import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;
import ca.uvic.leadlab.obibconnector.facades.registry.IClinic;
import ca.uvic.leadlab.obibconnector.facades.registry.ISearchClinic;
import ca.uvic.leadlab.obibconnector.models.queries.SearchClinicCriteria;
import ca.uvic.leadlab.obibconnector.models.response.ListClinicsResponse;
import ca.uvic.leadlab.obibconnector.rest.IOscarInformation;
import ca.uvic.leadlab.obibconnector.rest.OBIBRequestException;
import ca.uvic.leadlab.obibconnector.rest.RestClient;

import java.util.ArrayList;
import java.util.List;

public class SearchClinic implements ISearchClinic {

    private final String clinicId;

    public SearchClinic(String clinicId) {
        this.clinicId = clinicId;
    }

    @Override
    public List<IClinic> findByName(String name) {
        return new ArrayList<>(); // TODO implement
    }

    @Override
    public List<IClinic> findByID(String id) throws OBIBException {
        try {
            IOscarInformation client = new RestClient(clinicId);
            ListClinicsResponse response = client.listClinics(SearchClinicCriteria.byClinicId(id));

            if (!response.isOK()) {
                throw new OBIBException(response.getMessage());
            }

            List<IClinic> clinics = new ArrayList<>();
            for (ca.uvic.leadlab.obibconnector.models.registry.Clinic clinic : response.getClinics()){
                clinics.add(new Clinic(clinic));
            }
            return clinics;
        } catch (OBIBRequestException e) {
            throw new OBIBException("Error finding clinics by id.", e);
        }
    }

    @Override
    public List<IClinic> findByAddress(String address) {
        return new ArrayList<>(); // TODO implement
    }
}
