package ca.uvic.leadlab.obibconnector.models.response;

import ca.uvic.leadlab.obibconnector.models.provider.Provider;

import java.util.List;

public class ListProvidersResponse extends OBIBResponse {

    private List<Provider> providers;

    public List<Provider> getProviders() {
        return providers;
    }

    public void setProviders(List<Provider> providers) {
        this.providers = providers;
    }
}
