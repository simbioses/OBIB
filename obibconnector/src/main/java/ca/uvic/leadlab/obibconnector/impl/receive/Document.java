package ca.uvic.leadlab.obibconnector.impl.receive;

import ca.uvic.leadlab.obibconnector.facades.datatypes.DocumentStatus;
import ca.uvic.leadlab.obibconnector.facades.datatypes.ParticipantFunctionCode;
import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;
import ca.uvic.leadlab.obibconnector.facades.receive.IDistributionStatus;
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
import java.util.logging.Logger;

public class Document implements IDocument {

    private static final Logger LOGGER = Logger.getLogger(Document.class.getName());

    private final ClinicalDocument document;

    private String templateID;
    private String templateName;

    private String documentID;
    private int version;
    private Date effectiveTime;

    private Date receivedTime;

    private String loincCode;
    private String loincCodeDisplayName;

    private String title;

    private String setId;
    private String inFulFillmentOfId;

    private String parentDocumentId;

    private DocumentStatus statusCode;

    private IPatient patient;

    private IProvider author;
    private Date authoringTime;
    private String authorDevice;
    private Date authorDeviceTime;

    private String custodianName;

    private List<IProvider> primaryRecipients = new ArrayList<>();
    private List<IProvider> secondaryRecipients = new ArrayList<>();

    private List<IProvider> participatingProviders = new ArrayList<>();

    private IProvider orderingProvider;
    private IProvider familyProvider;

    private List<IAttachment> attachments = new ArrayList<>();

    private List<IDistributionStatus> distributionStatus = new ArrayList<>();

    private String contents;

    public Document(ClinicalDocument document) throws OBIBException {
        this.document = document;

        if (document.getTemplate() != null) {
            templateID = document.getTemplate().getTemplateId();
            templateName = document.getTemplate().getTemplateName();
        }

        documentID = document.getDocumentId();
        version = document.getVersionNumber();
        effectiveTime = DateFormatter.parseDateTime(document.getEffectiveTime());

        if (document.getLoinc() != null) {
            loincCode = document.getLoinc().getLoincCode();
            try { // try to get the display name from the DocumentType enum.
                DocumentType documentType = DocumentType.fromCode(loincCode);
                loincCodeDisplayName = documentType.label;
            } catch (Exception ignored) { // otherwise, return the value from obib
                loincCodeDisplayName = document.getLoinc().getDisplayName();
            }
        }

        title = document.getTitle();

        if (document.getSetId() != null) {
            setId = document.getSetId().getCode();
        }

        if (document.getOrders() != null && !document.getOrders().isEmpty()) {
            inFulFillmentOfId = document.getOrders().get(0).getIds().get(0).getCode(); // TODO improve this
        }

        if (document.getParentDocuments() != null && !document.getParentDocuments().isEmpty()) {
            parentDocumentId = document.getParentDocuments().get(0).getId().getCode(); // TODO improve this
        }

        if (document.getServiceEvents() != null && !document.getServiceEvents().isEmpty()) {
            ServiceEvent event = document.getLastServiceEvent();
            if (event != null && event.getStatusCode() != null && !event.getStatusCode().isEmpty()) {
                try { // try to get the statusCode name from the DocumentStatus enum.
                    statusCode = DocumentStatus.fromCode(event.getStatusCode());
                } catch (Exception ignored) {
                    statusCode = DocumentStatus.UNRECOGNIZED;
                }
            }
        }
        if (statusCode == null) {
            statusCode = DocumentStatus.COMPLETED;
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
            IProvider provider = new Provider(docRecipient);
            if (!docRecipient.isOrganizationOnly()) { // Do not generate recipient for "organization only recipient"
                if (RecipientType.isPrimary(docRecipient.getTypeCode())) {
                    primaryRecipients.add(provider);
                } else {
                    secondaryRecipients.add(provider);
                }
            }
        }

        for (Participant participant : document.getParticipants()) {
            participatingProviders.add(new Provider(participant));

            if (ParticipantFunctionCode.ORD.name().equalsIgnoreCase(participant.getFunctionCode())) {
                orderingProvider = new Provider(participant);
            }
            if (ParticipantFunctionCode.PCP.name().equalsIgnoreCase(participant.getFunctionCode())) {
                familyProvider = new Provider(participant);
            }
        }


        // TODO return document body
        // if (document.getNonXMLBody() != null && AttachmentType.isPlainText(document.getNonXMLBody().getMediaType())) {
        //    contents = document.getNonXMLBody().getContent();
        // }

        if (document.getAttachments() != null) { // attachments
            for (ca.uvic.leadlab.obibconnector.models.document.Attachment attachment : document.getAttachments()) {
                if (!AttachmentUtils.checkAttachment(attachment.getContent(), attachment.getHash())) {
                    LOGGER.warning("Error validating attachment hash code.");
                }
                attachments.add(new Attachment(attachment.getMediaType(), attachment.getReference(),
                        DatatypeConverter.parseBase64Binary(attachment.getContent())));
            }
        }

        if (document.getDistributionStatuses() != null) {
            for (ca.uvic.leadlab.obibconnector.models.document.DistributionStatus status : document.getDistributionStatuses()) {
                distributionStatus.add(new DistributionStatus(status));
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
        return documentID;
    }

    @Override
    public int getVersion() {
        return version;
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
        return title;
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
        return effectiveTime;
    }

    @Override
    public Date getReceivedTime() {
        return receivedTime;
    }

    void setReceivedTime(Date receivedTime) {
        this.receivedTime = receivedTime;
    }

    @Override
    public String getCustodianName() {
        return custodianName;
    }

    @Override
    public IProvider getPrimaryRecipient() {
        return !primaryRecipients.isEmpty() ? primaryRecipients.get(0) : null;
    }

    @Override
    public List<IProvider> getPrimaryRecipients() {
        return primaryRecipients;
    }

    @Override
    public List<IProvider> getSecondaryRecipients() {
        return secondaryRecipients;
    }

    @Override
    public IProvider getOrderingProvider() {
        return orderingProvider;
    }

    @Override
    public IProvider getFamilyProvider() {
        return familyProvider;
    }

    @Override
    public String getOrderID() {
        return null;
    }

    @Override
    public DocumentStatus getStatusCode() {
        return statusCode;
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
        return parentDocumentId;
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

    @Override
    public List<IDistributionStatus> getDistributionStatus() {
        return distributionStatus;
    }
}