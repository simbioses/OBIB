package ca.uvic.leadlab.obibconnector.impl.receive.mock;

import ca.uvic.leadlab.obibconnector.facades.receive.IId;
import ca.uvic.leadlab.obibconnector.facades.receive.IProvider;

public class ProviderAdeshina implements IProvider{
    @Override
    public String getFirstName() {
        return "Adeshina";
    }

    @Override
    public String getLastName() {
        return "Alani";
    }

    @Override
    public String getPrefix() {
        return "Dr";
    }

    @Override
    public IId[] getIDs() {
        IId[] result = new IId[1];
        result[0] = new IdAdeshina();
        return result;
    }

    @Override
    public String getClinicID() {
        return "34234";
    }

    @Override
    public String getClinicName() {
        return "Lead Clinic";
    }
}
