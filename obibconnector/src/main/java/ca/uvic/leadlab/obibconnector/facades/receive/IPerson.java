package ca.uvic.leadlab.obibconnector.facades.receive;

import java.util.Date;
import ca.uvic.leadlab.obibconnector.facades.datatypes.*;

public interface IPerson {

    String getID();

    String getFirstName();
    String getLastName();

    Gender getGender();

    Date getBirthdate();

    String getStreetAddress();
    String getCity();
    String getProvince();
    String getPostalCode();
    String getCountry();

    PhoneType getPhoneType();
    String getPhoneNumber();

    PhoneType getEmailType();
    String getEmailAddress();

}
