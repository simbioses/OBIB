package ca.uvic.leadlab.obibconnector.facades.receive;

import java.util.Date;
import java.util.List;

import ca.uvic.leadlab.obibconnector.facades.datatypes.*;

public interface IPerson {

    List<IId> getIDs();

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

    List<ITelco> getPhones();

    List<ITelco> getEmails();

    String getClinicID(); // empty for patients
    String getClinicName(); // empty for patients

}
