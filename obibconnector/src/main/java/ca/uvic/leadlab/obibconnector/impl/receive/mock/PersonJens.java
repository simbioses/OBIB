package ca.uvic.leadlab.obibconnector.impl.receive.mock;

import ca.uvic.leadlab.obibconnector.facades.datatypes.EmailType;
import ca.uvic.leadlab.obibconnector.facades.datatypes.Gender;
import ca.uvic.leadlab.obibconnector.facades.datatypes.PhoneType;
import ca.uvic.leadlab.obibconnector.facades.receive.IPerson;
import java.text.SimpleDateFormat;

import java.util.Date;

public class PersonJens implements IPerson {
    @Override
    public String getID() {
        return "1234";
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
    public PhoneType getPhoneType() {
        return PhoneType.HOME;
    }

    @Override
    public String getPhoneNumber() {
        return "250-555-5642";
    }

    @Override
    public EmailType getEmailType() {
        return EmailType.HOME;
    }

    @Override
    public String getEmailAddress() {
        return "jens@me.org";
    }
}
