package ca.uvic.leadlab.obibconnector.facades.registry;

import ca.uvic.leadlab.obibconnector.facades.receive.*;

public interface ISearchProviders {

IPerson[] findByName(String name);

IPerson[] findByClinicID(String id);

IPerson[] findByProviderID(String id);

}

