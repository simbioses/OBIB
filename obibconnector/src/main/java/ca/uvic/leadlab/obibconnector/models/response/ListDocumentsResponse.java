package ca.uvic.leadlab.obibconnector.models.response;

import ca.uvic.leadlab.obibconnector.models.document.ClinicalDocument;
import ca.uvic.leadlab.obibconnector.rest.OBIBRequestException;

import java.util.ArrayList;
import java.util.List;

public class ListDocumentsResponse extends OBIBResponse {

    private List<ClinicalDocument> documents = new ArrayList<>();

    public List<String> getDocumentIds() {
        List<String> ids = new ArrayList<>();
        for (ClinicalDocument document : documents) {
            ids.add(document.getDocumentId());
        }
        return ids;
    }

    public ClinicalDocument getDocument() throws OBIBRequestException {
        if (documents.isEmpty()) {
            throw new OBIBRequestException("No documents found.");
        }
        return documents.get(0);
    }

    public List<ClinicalDocument> getDocuments() {
        return documents;
    }

    public void setDocuments(List<ClinicalDocument> documents) {
        this.documents = documents;
    }
}
