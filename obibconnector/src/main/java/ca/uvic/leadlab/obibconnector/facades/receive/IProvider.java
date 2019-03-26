package ca.uvic.leadlab.obibconnector.facades.receive;

public interface IProvider {

    String getFirstName();
    String getLastName();
    String getPrefix();
    IId[] getIDs();
    String getClinicID();
    String getClinicName();

}
