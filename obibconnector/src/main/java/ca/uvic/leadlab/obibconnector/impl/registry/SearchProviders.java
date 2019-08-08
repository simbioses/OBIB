package ca.uvic.leadlab.obibconnector.impl.registry;

import ca.uvic.leadlab.obibconnector.facades.Config;
import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;
import ca.uvic.leadlab.obibconnector.facades.registry.IProvider;
import ca.uvic.leadlab.obibconnector.facades.registry.ISearchProviders;
import ca.uvic.leadlab.obibconnector.models.queries.SearchClinicCriteria;
import ca.uvic.leadlab.obibconnector.models.queries.SearchProviderCriteria;
import ca.uvic.leadlab.obibconnector.models.response.ListClinicsResponse;
import ca.uvic.leadlab.obibconnector.models.response.ListProvidersResponse;
import ca.uvic.leadlab.obibconnector.rest.IOscarInformation;
import ca.uvic.leadlab.obibconnector.rest.OBIBRequestException;
import ca.uvic.leadlab.obibconnector.rest.RestClient;

import java.util.ArrayList;
import java.util.List;

public class SearchProviders implements ISearchProviders {

    private final IOscarInformation services;

    public SearchProviders(Config conf) {
        this.services = new RestClient(conf.getUrl(), conf.getClinicId(),
                conf.getClinicPassword(), conf.getKeystorePassword());
    }

    @Override
    public List<IProvider> findByName(String name) throws OBIBException {
        try {
            return listProviders(SearchProviderCriteria.byProviderName(name));
        } catch (OBIBRequestException e) {
            throw new OBIBException("Error finding providers by name.", e);
        }
    }

    @Override
    public List<IProvider> findByClinicID(String id) throws OBIBException {
        try {
            /*
            CDX return: "Provider clinic ID cannot be the only parameter. Please use Clinic Search instead."
            return listProviders(SearchProviderCriteria.byClinicId(id));
            */

            ListClinicsResponse response = services.listClinics(SearchClinicCriteria.byClinicId(id)); // Using clinic search

            if (!response.isOK()) {
                throw new OBIBRequestException(response.getMessage(), response.getObibErrors());
            }

            List<IProvider> providers = new ArrayList<>();
            if (response.getClinics() != null && !response.getClinics().isEmpty()) {
                for (ca.uvic.leadlab.obibconnector.models.registry.Provider provider : response.getClinics().get(0).getProviders()){
                    providers.add(new Provider(provider));
                }
            }
            return providers;
        } catch (OBIBRequestException e) {
            throw new OBIBException("Error finding providers by clinic id.", e);
        }
    }

    @Override
    public List<IProvider> findByProviderID(String id) throws OBIBException {
        try {
            return listProviders(SearchProviderCriteria.byProviderId(id));
        } catch (OBIBRequestException e) {
            throw new OBIBException("Error finding providers by id.", e);
        }
    }

    private List<IProvider> listProviders(SearchProviderCriteria searchProviderCriteria) throws OBIBRequestException {
        ListProvidersResponse response = services.listProviders(searchProviderCriteria);

        if (!response.isOK()) {
            throw new OBIBRequestException(response.getMessage(), response.getObibErrors());
        }

        List<IProvider> providers = new ArrayList<>();
        for (ca.uvic.leadlab.obibconnector.models.registry.Provider provider : response.getProviders()){
            providers.add(new Provider(provider));
        }
        return providers;
    }
}
