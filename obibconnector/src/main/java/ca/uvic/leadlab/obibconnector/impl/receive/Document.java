package ca.uvic.leadlab.obibconnector.impl.receive;

import ca.uvic.leadlab.obibconnector.facades.datatypes.DateFormatter;
import ca.uvic.leadlab.obibconnector.facades.datatypes.MediaType;
import ca.uvic.leadlab.obibconnector.facades.datatypes.RecipientType;
import ca.uvic.leadlab.obibconnector.facades.receive.IAttachment;
import ca.uvic.leadlab.obibconnector.facades.receive.IDocument;
import ca.uvic.leadlab.obibconnector.facades.receive.IPatient;
import ca.uvic.leadlab.obibconnector.facades.registry.IProvider;
import ca.uvic.leadlab.obibconnector.impl.registry.Provider;
import ca.uvic.leadlab.obibconnector.models.document.Author;
import ca.uvic.leadlab.obibconnector.models.document.ClinicalDocument;
import ca.uvic.leadlab.obibconnector.models.document.Participant;
import ca.uvic.leadlab.obibconnector.models.document.Recipient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Document implements IDocument {

    private final ClinicalDocument document;

    private String templateID;
    private String templateName;

    private String loincCode;
    private String loincCodeDisplayName;

    private IPatient patient;

    private IProvider author;
    private Date authoringTime;
    private String authorDevice;
    private Date authorDeviceTime;

    private String custodianName;

    private IProvider primaryRecipient;
    private List<IProvider> secondaryRecipients = new ArrayList<>();

    private List<IProvider> participatingProviders = new ArrayList<>();
    private String contents;

    Document(ClinicalDocument document) {
        this.document = document;
        if (document.getTemplate() != null) {
            templateID = document.getTemplate().getTemplateId();
            templateName = document.getTemplate().getTemplateName();
        }
        if (document.getLoinc() != null) {
            loincCode = document.getLoinc().getLoincCode();
            loincCodeDisplayName = document.getLoinc().getDisplayName();
        }
        patient = new Patient(document.getPatient());
        for (Author docAuthor : document.getAuthors()) {
            if (docAuthor.getSoftware() != null) {
                authorDevice = docAuthor.getSoftware().getName();
                authorDeviceTime = DateFormatter.parseDateTime(docAuthor.getTime());
            } else {
                author = new Provider(docAuthor);
                authoringTime = DateFormatter.parseDateTime(docAuthor.getTime());
            }
        }
        if (document.getCustodian() != null) {
            custodianName = document.getCustodian().getName();
        }
        for (Recipient docRecipient : document.getRecipients()) {
            if (RecipientType.isPrimary(docRecipient.getTypeCode())) {
                primaryRecipient = new Provider(docRecipient);
            } else {
                secondaryRecipients.add(new Provider(docRecipient));
            }
        }

        for (Participant participant : document.getParticipants()) {
            participatingProviders.add(new Provider(participant));
        }
        if (document.getNonXMLBody() != null && MediaType.isPlainText(document.getNonXMLBody().getMediaType())) {
            contents = document.getNonXMLBody().getContent();
        }
    }

    @Override
    public String getTemplateID() {
        return templateID;
    }

    @Override
    public String getTemplateName() {
        return templateName;
    }

    @Override
    public String getDocumentID() {
        return document.getDocumentId();
    }

    @Override
    public String getLoincCode() {
        return loincCode;
    }

    @Override
    public String getLoincCodeDisplayName() {
        return loincCodeDisplayName;
    }

    @Override
    public String getTitle() {
        return document.getTitle();
    }

    @Override
    public IPatient getPatient() {
        return patient;
    }

    @Override
    public IProvider getAuthor() {
        return author;
    }

    @Override
    public Date getAuthoringTime() {
        return authoringTime;
    }

    @Override
    public String getAuthorDevice() {
        return authorDevice;
    }

    @Override
    public Date getAuthorDeviceTime() {
        return authorDeviceTime;
    }

    @Override
    public String getCustodianName() {
        return custodianName;
    }

    @Override
    public IProvider getPrimaryRecipient() {
        return primaryRecipient;
    }

    @Override
    public List<IProvider> getSecondaryRecipients() {
        return secondaryRecipients;
    }

    @Override
    public IProvider getOrderingProvider() {
        return null;
    }

    @Override
    public IProvider getFamilyProvider() {
        return null;
    }

    @Override
    public String getOrderID() {
        return null;
    }

    @Override
    public String getStatusCode() {
        return null;
    }

    @Override
    public Date getObservationDate() {
        return null;
    }

    @Override
    public String getProcedureName() {
        return null;
    }

    @Override
    public IProvider getProcedurePerformer() {
        return null;
    }

    @Override
    public String getParentDocumentID() {
        return null;
    }

    @Override
    public String getPatientEncounterID() {
        return null;
    }

    @Override
    public Date getAdmissionDate() {
        return null;
    }

    @Override
    public Date getDischargeDate() {
        return null;
    }

    @Override
    public String getDischargeDisposition() {
        return null;
    }

    @Override
    public List<IProvider> getParticipatingProviders() {
        return participatingProviders;
    }

    @Override
    public String getContents() {
        return contents;
    }

    @Override
    public List<IAttachment> getAttachments() {
        return null;
    }
}