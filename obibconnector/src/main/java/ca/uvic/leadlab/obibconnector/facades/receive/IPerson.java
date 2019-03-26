package ca.uvic.leadlab.obibconnector.facades.receive;

import java.util.Date;
import ca.uvic.leadlab.obibconnector.facades.datatypes.*;

public interface IPerson {

    IId[] getIDs();

    String getFirstName();
    String getLastName();

    Gender getGender();

    Date getBirthdate();

    String getStreetAddress();
    String getCity();
    String getProvince();
    String getPostalCode();
    String getCountry();

    ITelco[] getPhones();

    ITelco[] getEmails();

}
