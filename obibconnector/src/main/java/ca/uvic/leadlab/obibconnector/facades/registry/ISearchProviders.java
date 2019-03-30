package ca.uvic.leadlab.obibconnector.facades.registry;

import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;

import java.util.List;

public interface ISearchProviders {

    List<IProvider> findByName(String name) throws OBIBException;

    List<IProvider> findByClinicID(String id) throws OBIBException;

    List<IProvider> findByProviderID(String id) throws OBIBException;

}

