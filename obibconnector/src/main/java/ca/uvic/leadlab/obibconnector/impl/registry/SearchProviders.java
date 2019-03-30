package ca.uvic.leadlab.obibconnector.impl.registry;

import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;
import ca.uvic.leadlab.obibconnector.facades.registry.IProvider;
import ca.uvic.leadlab.obibconnector.facades.registry.ISearchProviders;
import ca.uvic.leadlab.obibconnector.models.queries.SearchProviderCriteria;
import ca.uvic.leadlab.obibconnector.models.response.ListProvidersResponse;
import ca.uvic.leadlab.obibconnector.rest.IOscarInformation;
import ca.uvic.leadlab.obibconnector.rest.OBIBRequestException;
import ca.uvic.leadlab.obibconnector.rest.RestClient;

import java.util.ArrayList;
import java.util.List;

public class SearchProviders implements ISearchProviders {

    private final String clinicId;

    public SearchProviders(String clinicId) {
        this.clinicId = clinicId;
    }

    @Override
    public List<IProvider> findByName(String name) throws OBIBException {
        return null; // TODO implement
    }

    @Override
    public List<IProvider> findByClinicID(String id) throws OBIBException {
        return null; // TODO implement
    }

    @Override
    public List<IProvider> findByProviderID(String id) throws OBIBException {
        try {
            IOscarInformation client = new RestClient(clinicId);
            ListProvidersResponse response = client.listProviders(SearchProviderCriteria.byProviderId(id));

            if (!response.isOK()) {
                throw new OBIBException(response.getMessage());
            }

            List<IProvider> providers = new ArrayList<>();
            for (ca.uvic.leadlab.obibconnector.models.registry.Provider provider : response.getProviders()){
                providers.add(new Provider(provider));
            }
            return providers;
        } catch (OBIBRequestException e) {
            throw new OBIBException("Error finding providers by id.", e);
        }
    }
}
