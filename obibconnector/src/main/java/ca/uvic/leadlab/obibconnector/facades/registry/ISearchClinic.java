package ca.uvic.leadlab.obibconnector.facades.registry;

import ca.uvic.leadlab.obibconnector.facades.receive.*;

public interface ISearchClinic {

    IClinic[] findByName(String name);

    IClinic[] findByID(String id);

    IClinic[] findByAddress(String id);
}
