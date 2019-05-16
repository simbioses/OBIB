package ca.uvic.leadlab.obibconnector.facades.support;

import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;

public interface ISupport {

    void notifyError(String context, String trace) throws OBIBException;
}
