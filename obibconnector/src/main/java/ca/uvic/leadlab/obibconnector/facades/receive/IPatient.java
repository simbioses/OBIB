package ca.uvic.leadlab.obibconnector.facades.receive;

import java.util.Date;
import java.util.List;

import ca.uvic.leadlab.obibconnector.facades.datatypes.*;

public interface IPatient {

    String getID();

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
}
