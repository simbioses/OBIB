package ca.uvic.leadlab.obibconnector.facades.registry;

import java.util.List;

public interface IClinic {

    String getID();
    String getName();

    String getStreetAddress();
    String getCity();
    String getPostalCode();
    String getProvince();

    IProvider getProvider();

    List<IProvider> getProviders();
}
