package ca.uvic.leadlab.obibconnector.models.response;

public class SubmitDocumentResponse extends OBIBResponse {

    private String documentId;
    private String document;

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
}
