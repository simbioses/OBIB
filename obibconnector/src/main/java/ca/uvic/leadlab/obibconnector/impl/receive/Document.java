package ca.uvic.leadlab.obibconnector.impl.receive;

import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;
import ca.uvic.leadlab.obibconnector.utils.AttachmentUtils;
import ca.uvic.leadlab.obibconnector.utils.DateFormatter;
import ca.uvic.leadlab.obibconnector.facades.datatypes.DocumentType;
import ca.uvic.leadlab.obibconnector.facades.datatypes.RecipientType;
import ca.uvic.leadlab.obibconnector.facades.receive.IAttachment;
import ca.uvic.leadlab.obibconnector.facades.receive.IDocument;
import ca.uvic.leadlab.obibconnector.facades.receive.IPatient;
import ca.uvic.leadlab.obibconnector.facades.registry.IProvider;
import ca.uvic.leadlab.obibconnector.impl.registry.Provider;
import ca.uvic.leadlab.obibconnector.models.document.*;

import javax.xml.bind.DatatypeConverter;
import java.util.*;

public class Document implements IDocument {

    private final ClinicalDocument document;

    private String templateID;
    private String templateName;

    private String loincCode;
    private String loincCodeDisplayName;

    private String setId;
    private String inFulFillmentOfId;

    private IPatient patient;

    private IProvider author;
    private Date authoringTime;
    private String authorDevice;
    private Date authorDeviceTime;

    private String custodianName;

    private IProvider primaryRecipient;
    private List<IProvider> secondaryRecipients = new ArrayList<>();

    private List<IProvider> participatingProviders = new ArrayList<>();

    private List<IAttachment> attachments = new ArrayList<>();

    private String contents;

    public Document(ClinicalDocument document) throws OBIBException {
        this.document = document;

        if (document.getTemplate() != null) {
            templateID = document.getTemplate().getTemplateId();
            templateName = document.getTemplate().getTemplateName();
        }

        if (document.getLoinc() != null) {
            loincCode = document.getLoinc().getLoincCode();
            try { // try to get the display name from the DocumentType enum.
                DocumentType documentType = DocumentType.fromCode(loincCode);
                loincCodeDisplayName = documentType.label;
            } catch (Exception ignored) { // otherwise, return the value from obib
                loincCodeDisplayName = document.getLoinc().getDisplayName();
            }
        }

        if (document.getSetId() != null) {
            setId = document.getSetId().getCode();
        }

        if (document.getOrders() != null && !document.getOrders().isEmpty()) {
            inFulFillmentOfId = document.getOrders().get(0).getId();
        }

        if (document.getPatient() != null) {
            patient = new Patient(document.getPatient());
        }

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

        // TODO return document body
        // if (document.getNonXMLBody() != null && MediaType.isPlainText(document.getNonXMLBody().getMediaType())) {
        //    contents = document.getNonXMLBody().getContent();
        // }

        if (document.getAttachments() != null) { // attachments
            for (ca.uvic.leadlab.obibconnector.models.document.Attachment attachment : document.getAttachments()) {
                if (!AttachmentUtils.checkAttachment(attachment.getContent(), attachment.getHash())) {
                    // TODO log check error
                }
                attachments.add(new Attachment(attachment.getMediaType(), attachment.getReference(),
                        DatatypeConverter.parseBase64Binary(attachment.getContent())));
            }
        }

        contents = document.getCdaXML(); // raw CDA XML
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
    public int getVersion() {
        return document.getVersionNumber();
    }

    @Override
    public String getSetId() {
        return setId;
    }

    @Override
    public String getInFulFillmentOfId() {
        return inFulFillmentOfId;
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
    public Date getEffectiveTime() {
        return document.getEffectiveTime();
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
        return attachments;
    }
}