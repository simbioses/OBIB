package ca.uvic.leadlab.obibconnector.impl.receive.mock;

import ca.uvic.leadlab.obibconnector.facades.datatypes.Gender;
import ca.uvic.leadlab.obibconnector.facades.receive.IId;
import ca.uvic.leadlab.obibconnector.facades.receive.IPerson;
import ca.uvic.leadlab.obibconnector.facades.receive.ITelco;

import java.text.SimpleDateFormat;

import java.util.Date;

public class PersonJens implements IPerson {
    @Override
    public IId[] getIDs() {
        IId[] result = new IId[1];
        result[0] = new  IdJens();
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
    public ITelco[] getPhones() {
        ITelco[] result = new ITelco[1];
        result[0] = new  TelcoPhone555();
        return result;
    }


    @Override
    public ITelco[] getEmails() {
        ITelco[] result = new ITelco[1];
        result[0] = new  TelcoEmailMe();
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
