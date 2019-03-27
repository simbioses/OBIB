package ca.uvic.leadlab.obibconnector.models.response;

import ca.uvic.leadlab.obibconnector.models.document.ClinicalDocument;

import java.util.List;

public class ListDocumentsResponse extends OBIBResponse {

    private List<ClinicalDocument> documents;

    public List<ClinicalDocument> getDocuments() {
        return documents;
    }

    public void setDocuments(List<ClinicalDocument> documents) {
        this.documents = documents;
    }
}
