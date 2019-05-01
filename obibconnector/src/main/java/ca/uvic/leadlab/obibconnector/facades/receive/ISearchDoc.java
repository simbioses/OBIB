package ca.uvic.leadlab.obibconnector.facades.receive;

import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;

import java.util.List;

public interface ISearchDoc {

    List<IDocument> searchDocumentsByClinic(String clinicId) throws OBIBException;

    IDocument searchDocumentById(String documentId) throws OBIBException;

}
