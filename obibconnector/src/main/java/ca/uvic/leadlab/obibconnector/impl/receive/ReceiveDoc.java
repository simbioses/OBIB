package ca.uvic.leadlab.obibconnector.impl.receive;

import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;
import ca.uvic.leadlab.obibconnector.facades.receive.IDocument;
import ca.uvic.leadlab.obibconnector.facades.receive.IReceiveDoc;
import ca.uvic.leadlab.obibconnector.models.queries.SearchDocumentCriteria;
import ca.uvic.leadlab.obibconnector.models.response.ListDocumentsResponse;
import ca.uvic.leadlab.obibconnector.rest.IOscarInformation;
import ca.uvic.leadlab.obibconnector.rest.OBIBRequestException;
import ca.uvic.leadlab.obibconnector.rest.RestClient;

import java.util.List;

public class ReceiveDoc implements IReceiveDoc {

    private final String clinicId;

    public ReceiveDoc(String clinicId) {
        this.clinicId = clinicId;
    }

    @Override
    public List<String> pollNewDocIDs() throws OBIBException {
        try {
            IOscarInformation client = new RestClient(clinicId);
            ListDocumentsResponse response = client.listDocument();

            if (!response.isOK()) {
                throw new OBIBException(response.getMessage());
            }

            return response.getDocumentIds();
        } catch (OBIBRequestException e) {
            throw new OBIBException("Error listing new documents.", e);
        }
    }

    @Override
    public IDocument retrieveDocument(String id) throws OBIBException {
        try {
            IOscarInformation client = new RestClient(clinicId);
            ListDocumentsResponse response = client.getDocument(SearchDocumentCriteria.byDocumentId(id));

            if (!response.isOK()) {
                throw new OBIBException(response.getMessage());
            }

            return new Document(response.getDocument());
        } catch (OBIBRequestException e) {
            throw new OBIBException("Error listing new documents.", e);
        }
    }
}
