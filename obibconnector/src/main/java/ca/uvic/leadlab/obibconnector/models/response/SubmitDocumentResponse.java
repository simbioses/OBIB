package ca.uvic.leadlab.obibconnector.models.response;

import ca.uvic.leadlab.obibconnector.models.document.ClinicalDocument;

public class SubmitDocumentResponse extends OBIBResponse {

    private String documentId;
    private ClinicalDocument document;

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public ClinicalDocument getDocument() {
        return document;
    }

    public void setDocument(ClinicalDocument document) {
        this.document = document;
    }
}
