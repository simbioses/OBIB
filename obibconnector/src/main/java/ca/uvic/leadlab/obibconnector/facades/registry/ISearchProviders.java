package ca.uvic.leadlab.obibconnector.facades.registry;

import ca.uvic.leadlab.obibconnector.facades.receive.*;

public interface ISearchProviders {

IProvider[] findByName(String name);

IProvider[] findByClinicID(String id);

IProvider[] findByProviderID(String id);

}

