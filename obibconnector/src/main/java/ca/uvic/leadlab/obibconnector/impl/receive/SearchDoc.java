package ca.uvic.leadlab.obibconnector.impl.receive;

import ca.uvic.leadlab.obibconnector.facades.Config;
import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;
import ca.uvic.leadlab.obibconnector.facades.receive.IDocument;
import ca.uvic.leadlab.obibconnector.facades.receive.ISearchDoc;
import ca.uvic.leadlab.obibconnector.models.document.ClinicalDocument;
import ca.uvic.leadlab.obibconnector.models.queries.SearchDocumentCriteria;
import ca.uvic.leadlab.obibconnector.models.response.ListDocumentsResponse;
import ca.uvic.leadlab.obibconnector.rest.IOscarInformation;
import ca.uvic.leadlab.obibconnector.rest.OBIBRequestException;
import ca.uvic.leadlab.obibconnector.rest.RestClient;

import java.util.ArrayList;
import java.util.List;

public class SearchDoc implements ISearchDoc {

    private final IOscarInformation services;

    public SearchDoc(Config conf) {
        this.services = new RestClient(conf.getUrl(), conf.getClinicId());
    }

    @Override
    public List<IDocument> searchDocumentsByClinic(String clinicId) throws OBIBException {
        try {
            ListDocumentsResponse response = services.searchDocument(SearchDocumentCriteria.byClinicId(clinicId));

            if (!response.isOK()) {
                throw new OBIBRequestException(response.getMessage(), response.getObibErrors());
            }

            List<IDocument> documents = new ArrayList<>();
            for (ClinicalDocument document : response.getDocuments()) {
                documents.add(new Document(document));
            }
            return documents;
        } catch (OBIBRequestException e) {
            throw new OBIBException("Error searching for document by clinic.", e);
        }
    }

    @Override
    public IDocument searchDocumentById(String clinicId, String documentId) throws OBIBException {
        try {
            ListDocumentsResponse response = services.searchDocument(
                    SearchDocumentCriteria.byClinicIdAndDocumentId(clinicId, documentId));

            if (!response.isOK()) {
                throw new OBIBRequestException(response.getMessage(), response.getObibErrors());
            }

            return new Document(response.getDocument());
        } catch (OBIBRequestException e) {
            throw new OBIBException("Error searching for document by id.", e);
        }
    }

    @Override
    public IDocument distributionStatus(String documentId) throws OBIBException {
        try {
            ListDocumentsResponse response = services.distributionStatus(
                    SearchDocumentCriteria.byDocumentId(documentId));

            if (!response.isOK()) {
                throw new OBIBRequestException(response.getMessage(), response.getObibErrors());
            }

            return new Document(response.getDocument());
        } catch (OBIBRequestException e) {
            throw new OBIBException("Error retrieving document distribution status.", e);
        }
    }

}
