package ca.uvic.leadlab.obibconnector.impl.receive.mock;

import ca.uvic.leadlab.obibconnector.facades.datatypes.Gender;
import ca.uvic.leadlab.obibconnector.facades.receive.IId;
import ca.uvic.leadlab.obibconnector.facades.receive.IPerson;
import ca.uvic.leadlab.obibconnector.facades.receive.ITelco;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonJens implements IPerson {
    @Override
    public List<IId> getIDs() {
        List<IId> result = new ArrayList<IId>();
        result.add(new  IdJens());
        return result;
    }

    @Override
    public String getFirstName() {
        return "Jens";
    }

    @Override
    public String getLastName() {
        return "Weber";
    }

    @Override
    public String getPrefix() {
        return null;
    }

    @Override
    public Gender getGender() {
        return Gender.MALE;
    }

    @Override
    public Date getBirthdate() {
        SimpleDateFormat sdf = new SimpleDateFormat ( "dd-mm-yyyy" ) ;

        Date result = null;

        try {
            sdf.parse("01-01-1900");
        } catch (Exception e) {
            // should never happen
        }

        return result;
    }

    @Override
    public String getStreetAddress() {
        return "3800 Finnerty Rd.";
    }

    @Override
    public String getCity() {
        return "Victoria";
    }

    @Override
    public String getProvince() {
        return "BC";
    }

    @Override
    public String getPostalCode() {
        return "V8P 3A8";
    }

    @Override
    public String getCountry() {
        return "CA";
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
    public String getClinicID() {
        return null;
    }

    @Override
    public String getClinicName() {
        return null;
    }

}
