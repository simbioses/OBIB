package ca.uvic.leadlab.obibconnector.impl.registry.mock;

import ca.uvic.leadlab.obibconnector.facades.receive.ITelco;
import ca.uvic.leadlab.obibconnector.facades.registry.IProvider;

import java.util.List;

public class ProviderPH implements IProvider {

    @Override
    public String getID() {
        return "93195";
    }

    @Override
    public String getFirstName() {
        return "Homer";
    }

    @Override
    public String getLastName() {
        return "Plisihb";
    }

    @Override
    public String getPrefix() {
        return "Dr.";
    }

    @Override
    public String getStreetAddress() {
        return null;
    }

    @Override
    public String getCity() {
        return null;
    }

    @Override
    public String getProvince() {
        return null;
    }

    @Override
    public String getPostalCode() {
        return null;
    }

    @Override
    public String getCountry() {
        return null;
    }

    @Override
    public List<ITelco> getPhones() {
        return null;
    }

    @Override
    public List<ITelco> getEmails() {
        return null;
    }

    @Override
    public String getClinicID() {
        return "cdxpostprod-otca";
    }

    @Override
    public String getClinicName() {
        return "Oscar Test Clinic A";
    }
}
