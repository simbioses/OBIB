package ca.uvic.leadlab.obibconnector.facades.receive;

import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;

import java.util.List;

public interface IReceiveDoc {

    List<String> pollNewDocIDs() throws OBIBException;

    IDocument retrieveDocument(String messageId) throws OBIBException;
}
