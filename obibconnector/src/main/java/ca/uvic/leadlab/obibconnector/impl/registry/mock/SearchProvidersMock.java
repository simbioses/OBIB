package ca.uvic.leadlab.obibconnector.impl.registry.mock;

import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;
import ca.uvic.leadlab.obibconnector.facades.registry.IProvider;
import ca.uvic.leadlab.obibconnector.facades.registry.ISearchProviders;

import java.util.ArrayList;
import java.util.List;

public class SearchProvidersMock implements ISearchProviders {

    @Override
    public List<IProvider> findByName(String name) throws OBIBException {
        List<IProvider> providers = new ArrayList<>();
        if (name.equalsIgnoreCase("Plisihq")) {
            providers.add(new ProviderPA());
            providers.add(new ProviderPG());
            providers.add(new ProviderPH());
            providers.add(new ProviderPL());
        } else {
            throw new OBIBException("No provider found");
        }
        return providers;
    }

    @Override
    public List<IProvider> findByClinicID(String id) throws OBIBException {
        List<IProvider> providers = new ArrayList<>();
        if (id.equalsIgnoreCase("cdxpostprod-otca")) {
            providers.add(new ProviderPA());
            providers.add(new ProviderPG());
            providers.add(new ProviderPH());
        } else {
            throw new OBIBException("No provider found");
        }
        return providers;
    }

    @Override
    public List<IProvider> findByProviderID(String id) throws OBIBException {
        List<IProvider> providers = new ArrayList<>();
        if (id.equalsIgnoreCase("93190")) {
            providers.add(new ProviderPA());
        } else {
            throw new OBIBException("No provider found");
        }
        return providers;
    }
}
