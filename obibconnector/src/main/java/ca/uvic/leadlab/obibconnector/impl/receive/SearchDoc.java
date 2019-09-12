package ca.uvic.leadlab.obibconnector.impl.receive;

import ca.uvic.leadlab.obibconnector.facades.Config;
import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;
import ca.uvic.leadlab.obibconnector.facades.receive.IDocument;
import ca.uvic.leadlab.obibconnector.facades.receive.ISearchDoc;
import ca.uvic.leadlab.obibconnector.models.document.ClinicalDocument;
import ca.uvic.leadlab.obibconnector.models.queries.DateRange;
import ca.uvic.leadlab.obibconnector.models.queries.SearchDocumentCriteria;
import ca.uvic.leadlab.obibconnector.models.response.ListDocumentsResponse;
import ca.uvic.leadlab.obibconnector.rest.IOscarInformation;
import ca.uvic.leadlab.obibconnector.rest.OBIBRequestException;
import ca.uvic.leadlab.obibconnector.rest.RestClient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchDoc implements ISearchDoc {

    private final IOscarInformation services;

    public SearchDoc(Config conf) {
        this.services = new RestClient(conf.getUrl());
    }

    @Override
    public List<IDocument> searchDocuments() throws OBIBException {
        return searchDocuments(new SearchDocumentCriteria());
    }

    @Override
    public List<IDocument> searchDocumentsByPeriod(Date startDate, Date endDate) throws OBIBException {
        SearchDocumentCriteria criteria = new SearchDocumentCriteria();
        if (startDate != null && endDate != null) {
            criteria.setEffectiveTime(new DateRange(startDate, endDate));
        }
        return searchDocuments(criteria);
    }

    private List<IDocument> searchDocuments(SearchDocumentCriteria criteria) throws OBIBException {
        try {
            ListDocumentsResponse response = services.searchDocument(criteria);

            if (!response.isOK()) {
                throw new OBIBRequestException(response.getMessage(), response.getObibErrors());
            }

            List<IDocument> documents = new ArrayList<>();
            for (ClinicalDocument document : response.getDocuments()) {
                documents.add(new Document(document));
            }
            return documents;
        } catch (OBIBRequestException e) {
            throw new OBIBException("Error searching for documents.", e);
        }
    }

    @Override
    public IDocument searchDocumentById(String documentId) throws OBIBException {
        try {
            ListDocumentsResponse response = services.searchDocument(SearchDocumentCriteria.byDocumentId(documentId));

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
            ListDocumentsResponse response = services.distributionStatus(SearchDocumentCriteria.byDocumentId(documentId));

            if (!response.isOK()) {
                throw new OBIBRequestException(response.getMessage(), response.getObibErrors());
            }

            return new Document(response.getDocument());
        } catch (OBIBRequestException e) {
            throw new OBIBException("Error retrieving document distribution status.", e);
        }
    }

}
