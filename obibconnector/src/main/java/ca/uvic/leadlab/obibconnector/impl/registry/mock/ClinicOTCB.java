package ca.uvic.leadlab.obibconnector.impl.registry.mock;

import ca.uvic.leadlab.obibconnector.facades.registry.IClinic;
import ca.uvic.leadlab.obibconnector.facades.registry.IProvider;

import java.util.List;

public class ClinicOTCB implements IClinic {

    @Override
    public String getID() {
        return "cdxpostprod-otcb";
    }

    @Override
    public String getName() {
        return "Oscar Test Clinic B";
    }

    @Override
    public String getStreetAddress() {
        return "1818 Main Ave";
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
        return null;
    }

    @Override
    public List<IProvider> getProviders() {
        return null;
    }
}
