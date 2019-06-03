package ca.uvic.leadlab.obibconnector.facades.receive;

import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;

import java.util.Date;
import java.util.List;

public interface ISearchDoc {

    List<IDocument> searchDocumentsByClinic(String clinicId) throws OBIBException;

    List<IDocument> searchDocumentsByClinic(String clinicId, Date startDate, Date endDate) throws OBIBException;

    IDocument searchDocumentById(String clinicId, String documentId) throws OBIBException;

    IDocument distributionStatus(String documentId) throws OBIBException;

}
