package ca.uvic.leadlab.obibconnector.impl.receive.mock;

import ca.uvic.leadlab.obibconnector.facades.receive.IId;
import ca.uvic.leadlab.obibconnector.facades.registry.IProvider;
import ca.uvic.leadlab.obibconnector.facades.receive.ITelco;

import java.util.ArrayList;
import java.util.List;

public class ProviderAdeshina implements IProvider {
    @Override
    public String getFirstName() {
        return "Adeshina";
    }

    @Override
    public String getLastName() {
        return "Alani";
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
        List<ITelco> result = new ArrayList<ITelco>();
        result.add(new  TelcoPhone555());
        return result;
    }


    @Override
    public List<ITelco> getEmails() {
        List<ITelco> result = new ArrayList<ITelco>();
        result.add(new  TelcoEmailMe());
        return result;
    }

    @Override
    public String getPrefix() {
        return "Dr";
    }

    @Override
    public List<IId> getIDs() {
        List<IId> result = new ArrayList<IId>();
        result.add(new IdAdeshina());
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
