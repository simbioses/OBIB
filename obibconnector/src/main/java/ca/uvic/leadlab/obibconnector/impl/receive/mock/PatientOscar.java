package ca.uvic.leadlab.obibconnector.impl.receive.mock;

import ca.uvic.leadlab.obibconnector.facades.datatypes.Gender;
import ca.uvic.leadlab.obibconnector.facades.receive.IId;
import ca.uvic.leadlab.obibconnector.facades.receive.IPatient;
import ca.uvic.leadlab.obibconnector.facades.receive.ITelco;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatientOscar implements IPatient {

    @Override
    public String getID() {
        return "45678";
    }

    @Override
    public String getFirstName() {
        return "Oscar";
    }

    @Override
    public String getLastName() {
        return "Costa";
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
            result = sdf.parse("03-03-1903");
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
}
