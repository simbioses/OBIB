package ca.uvic.leadlab.obibconnector.facade;

public interface ISearchProviders {

IProvider[] findByName(String name);

IProvider[] findByClinicID(String id);

IProvider[] findByProviderID(String id);

}

