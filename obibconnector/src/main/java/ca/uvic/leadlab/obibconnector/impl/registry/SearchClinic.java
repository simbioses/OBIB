package ca.uvic.leadlab.obibconnector.impl.registry;

import ca.uvic.leadlab.obibconnector.facades.Config;
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

    private final IOscarInformation services;

    public SearchClinic(Config conf) {
        this.services = new RestClient(conf.getUrl());
    }

    @Override
    public List<IClinic> findByName(String name) throws OBIBException {
        try {
            return listClinics(SearchClinicCriteria.byClinicName(name));
        } catch (OBIBRequestException e) {
            throw new OBIBException("Error finding clinics by name.", e);
        }
    }

    @Override
    public List<IClinic> findByID(String id) throws OBIBException {
        try {
            return listClinics(SearchClinicCriteria.byClinicId(id));
        } catch (OBIBRequestException e) {
            throw new OBIBException("Error finding clinics by id.", e);
        }
    }

    @Override
    public List<IClinic> findByAddress(String address) throws OBIBException {
        try {
            return listClinics(SearchClinicCriteria.byClinicAddress(address));
        } catch (OBIBRequestException e) {
            throw new OBIBException("Error finding clinics by address.", e);
        }
    }

    private List<IClinic> listClinics(SearchClinicCriteria searchClinicCriteria) throws OBIBRequestException {
        ListClinicsResponse response = services.listClinics(searchClinicCriteria);

        if (!response.isOK()) {
            throw new OBIBRequestException(response.getMessage(), response.getObibErrors());
        }

        List<IClinic> clinics = new ArrayList<>();
        for (ca.uvic.leadlab.obibconnector.models.registry.Clinic clinic : response.getClinics()){
            clinics.add(new Clinic(clinic));
        }
        return clinics;
    }
}
