package ca.uvic.leadlab.obibconnector.facade;

public interface ISearchClinic {

    IClinic[] findByName(String name);

    IClinic[] findByID(String id);

    IClinic[] findByAddress(String id);
}
