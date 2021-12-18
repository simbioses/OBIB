package ca.uvic.leadlab.obibconnector.impl.registry.mock;

import ca.uvic.leadlab.obibconnector.facades.registry.IClinic;
import ca.uvic.leadlab.obibconnector.facades.registry.IProvider;
import ca.uvic.leadlab.obibconnector.facades.registry.ITemplate;

import java.util.List;

public class ClinicOTCA implements IClinic {

    @Override
    public String getID() {
        return "cdxpostprod-otca";
    }

    @Override
    public String getName() {
        return "Oscar Test Clinic A";
    }

    @Override
    public String getStreetAddress() {
        return "1818 Test Ave";
    }

    @Override
    public String getCity() {
        return "Kelowna";
    }

    @Override
    public String getPostalCode() {
        return "V1V1V1";
    }

    @Override
    public String getProvince() {
        return "British Columbia";
    }

    @Override
    public IProvider getProvider() {
        return new ProviderPL();
    }

    @Override
    public List<IProvider> getProviders() {
        return null;
    }

    @Override
    public List<ITemplate> getTemplates() {
        return null;
    }
}
