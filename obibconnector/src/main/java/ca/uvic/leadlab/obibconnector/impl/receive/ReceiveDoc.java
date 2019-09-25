package ca.uvic.leadlab.obibconnector.impl.receive;

import ca.uvic.leadlab.obibconnector.facades.Config;
import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;
import ca.uvic.leadlab.obibconnector.facades.receive.IDocument;
import ca.uvic.leadlab.obibconnector.facades.receive.IReceiveDoc;
import ca.uvic.leadlab.obibconnector.models.queries.SearchDocumentCriteria;
import ca.uvic.leadlab.obibconnector.models.response.ListDocumentsResponse;
import ca.uvic.leadlab.obibconnector.rest.IOscarInformation;
import ca.uvic.leadlab.obibconnector.rest.OBIBRequestException;
import ca.uvic.leadlab.obibconnector.rest.RestClient;

import java.util.Date;
import java.util.List;

public class ReceiveDoc implements IReceiveDoc {

    private final IOscarInformation services;

    public ReceiveDoc(Config conf) {
        this.services = new RestClient(conf.getUrl());
    }

    @Override
    public List<String> pollNewDocIDs() throws OBIBException {
        try {
            ListDocumentsResponse response = services.listDocument();

            if (!response.isOK()) {
                throw new OBIBRequestException(response.getMessage(), response.getObibErrors());
            }

            return response.getDocumentIds();
        } catch (OBIBRequestException e) {
            throw new OBIBException("Error listing new documents.", e);
        }
    }

    @Override
    public IDocument retrieveDocument(String id) throws OBIBException {
        try {
            ListDocumentsResponse response = services.getDocument(SearchDocumentCriteria.byDocumentId(id));

            if (!response.isOK()) {
                throw new OBIBRequestException(response.getMessage(), response.getObibErrors());
            }

            if (response.getDocument() == null) {
                throw new OBIBRequestException("Could not retrieve document with ID " + id + ".");
            }

            Document document = new Document(response.getDocument());
            document.setReceivedTime(new Date());
            return document;
        } catch (OBIBRequestException e) {
            throw new OBIBException("Error retrieving document.", e);
        }
    }
}
