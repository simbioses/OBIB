package ca.uvic.leadlab.obibconnector.facades.send;

import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;

public interface ISubmit {

    String submit() throws OBIBException;
}
