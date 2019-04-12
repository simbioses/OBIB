package ca.uvic.leadlab.obibconnector.models.response;

import ca.uvic.leadlab.obibconnector.models.document.Attachment;
import ca.uvic.leadlab.obibconnector.models.document.ClinicalDocument;

import java.util.ArrayList;
import java.util.List;

public class ListDocumentsResponse extends OBIBResponse {

    private List<ClinicalDocument> documents = new ArrayList<>();
    private List<Attachment> attachments = new ArrayList<>();

    public List<String> getDocumentIds() {
        List<String> ids = new ArrayList<>();
        for (ClinicalDocument document : documents) {
            ids.add(document.getDocumentId());
        }
        return ids;
    }

    public ClinicalDocument getDocument() {
        if (documents.size() != 1) {
            // TODO throw exception?
        }
        return documents.get(0);
    }

    public List<ClinicalDocument> getDocuments() {
        return documents;
    }

    public void setDocuments(List<ClinicalDocument> documents) {
        this.documents = documents;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }
}
