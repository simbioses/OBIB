package ca.uvic.leadlab.obibconnector.facades.registry;

import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;

import java.util.List;

public interface ISearchClinic {

    List<IClinic> findByName(String name) throws OBIBException;

    List<IClinic> findByID(String id) throws OBIBException;

    List<IClinic> findByAddress(String address) throws OBIBException;
}
