package ca.uvic.leadlab.obibconnector.facades.registry;

import ca.uvic.leadlab.obibconnector.facades.receive.ITelco;

import java.util.List;

public interface IProvider {

    String getID();

    String getFirstName();
    String getLastName();
    String getPrefix();

    String getStreetAddress();
    String getCity();
    String getProvince();
    String getPostalCode();
    String getCountry();

    List<ITelco> getPhones();

    List<ITelco> getEmails();

    String getClinicID();
    String getClinicName();

    List<IClinic> getClinics();
}
