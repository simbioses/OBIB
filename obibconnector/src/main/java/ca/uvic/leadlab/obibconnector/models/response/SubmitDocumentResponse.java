package ca.uvic.leadlab.obibconnector.models.response;

public class SubmitDocumentResponse extends OBIBResponse {

    private String documentId;

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
