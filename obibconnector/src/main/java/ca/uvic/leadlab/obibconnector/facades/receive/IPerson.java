package ca.uvic.leadlab.obibconnector.facades.receive;

import java.util.Date;
import ca.uvic.leadlab.obibconnector.facades.datatypes.*;

public interface IPerson {

    IId[] getIDs();

    String getFirstName();
    String getLastName();
    String getPrefix();

    Gender getGender();

    Date getBirthdate();

    String getStreetAddress();
    String getCity();
    String getProvince();
    String getPostalCode();
    String getCountry();

    ITelco[] getPhones();

    ITelco[] getEmails();

    String getClinicID(); // empty for patients
    String getClinicName(); // empty for patients

}
