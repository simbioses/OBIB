package ca.uvic.leadlab.obibconnector.impl.receive.mock;

import ca.uvic.leadlab.obibconnector.facades.datatypes.Gender;
import ca.uvic.leadlab.obibconnector.facades.receive.IId;
import ca.uvic.leadlab.obibconnector.facades.receive.IPerson;
import ca.uvic.leadlab.obibconnector.facades.receive.ITelco;

import java.util.Date;

public class ProviderAdeshina implements IPerson{
    @Override
    public String getFirstName() {
        return "Adeshina";
    }

    @Override
    public String getLastName() {
        return "Alani";
    }

    @Override
    public Gender getGender() {
        return null;
    }

    @Override
    public Date getBirthdate() {
        return null;
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
    public ITelco[] getPhones() {
        return new ITelco[0];
    }

    @Override
    public ITelco[] getEmails() {
        return new ITelco[0];
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
