package ca.uvic.leadlab.obibconnector.facades.receive;

public interface IClinic {

    String getID();
    String getName();
    String getAddress();
    String getCity();
    String getPostal();
    String getState();

    IProvider getProvider();
}
