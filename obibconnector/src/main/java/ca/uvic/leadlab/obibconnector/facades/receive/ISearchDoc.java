package ca.uvic.leadlab.obibconnector.facades.receive;

import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;

import java.util.Date;
import java.util.List;

public interface ISearchDoc {

    List<IDocument> searchDocuments() throws OBIBException;

    List<IDocument> searchDocumentsByPeriod(Date startDate, Date endDate) throws OBIBException;

    List<IDocument> searchDocumentById(String documentId) throws OBIBException;

    List<IDocument> distributionStatus(String documentId) throws OBIBException;

}
