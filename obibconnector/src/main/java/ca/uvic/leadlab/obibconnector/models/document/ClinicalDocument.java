package ca.uvic.leadlab.obibconnector.models.document;

import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;
import ca.uvic.leadlab.obibconnector.models.common.Id;
import ca.uvic.leadlab.obibconnector.models.common.Loinc;
import ca.uvic.leadlab.obibconnector.models.common.Template;
import ca.uvic.leadlab.obibconnector.utils.DateFormatter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClinicalDocument {

    private String documentId;
    private Type type;
    private Template template;
    private Loinc loinc;
    private String effectiveTime;
    private String title;
    private int versionNumber;
    private Id setId;
    private Patient patient;
    private List<Author> authors = new ArrayList<>();
    private List<Recipient> recipients = new ArrayList<>();
    private Custodian custodian;
    private DataEnterer dataEnterer;
    private List<Authenticator> authenticators = new ArrayList<>();
    private List<Participant> participants = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private List<ServiceEvent> serviceEvents = new ArrayList<>();
    private List<ParentDocument> parentDocuments = new ArrayList<>();
    private NonXMLBody nonXMLBody;
    private List<Attachment> attachments = new ArrayList<>();
    private List<String> receivers = new ArrayList<>();
    private String cdaXML;
    private List<DistributionStatus> distributionStatuses = new ArrayList<>();

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public Loinc getLoinc() {
        return loinc;
    }

    public void setLoinc(Loinc loinc) {
        this.loinc = loinc;
    }

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(int versionNumber) {
        this.versionNumber = versionNumber;
    }

    public Id getSetId() {
        return setId;
    }

    public void setSetId(Id setId) {
        this.setId = setId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void addAuthor(Author author) {
        if (this.authors == null) {
            this.authors = new ArrayList<>();
        }
        this.authors.add(author);
    }

    public List<Recipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<Recipient> recipients) {
        this.recipients = recipients;
    }

    public void addRecipient(Recipient recipient) {
        if (this.recipients == null) {
            this.recipients = new ArrayList<>();
        }
        this.recipients.add(recipient);
    }

    public Custodian getCustodian() {
        return custodian;
    }

    public void setCustodian(Custodian custodian) {
        this.custodian = custodian;
    }

    public DataEnterer getDataEnterer() {
        return dataEnterer;
    }

    public void setDataEnterer(DataEnterer dataEnterer) {
        this.dataEnterer = dataEnterer;
    }

    public List<Authenticator> getAuthenticators() {
        return authenticators;
    }

    public void setAuthenticators(List<Authenticator> authenticators) {
        this.authenticators = authenticators;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public void addParticipant(Participant participant) {
        if (this.participants == null) {
            this.participants = new ArrayList<>();
        }
        this.participants.add(participant);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        if (this.orders == null) {
            this.orders = new ArrayList<>();
        }
        this.orders.add(order);
    }

    public List<ServiceEvent> getServiceEvents() {
        return serviceEvents;
    }

    public void setServiceEvents(List<ServiceEvent> serviceEvents) {
        this.serviceEvents = serviceEvents;
    }

    public void addServiceEvent(ServiceEvent serviceEvent) {
        if (this.serviceEvents == null) {
            this.serviceEvents = new ArrayList<>();
        }
        this.serviceEvents.add(serviceEvent);
    }

    public ServiceEvent getLastServiceEvent() throws OBIBException {
        ServiceEvent last = null;
        for (ServiceEvent serviceEvent : serviceEvents) {
            if (last == null) {
                last = serviceEvent;
            } else {
                Date lastTime = DateFormatter.parseDateTime(last.getEffectiveTime());
                Date eventTime = DateFormatter.parseDateTime(serviceEvent.getEffectiveTime());
                if (eventTime.equals(lastTime) && serviceEvent.haveStatus()) {
                    last = serviceEvent;
                }
                if (eventTime.after(lastTime)) {
                    last = serviceEvent; // Get the last "Real ServiceEvent"
                }
            }
        }
        return last;
    }

    public List<ParentDocument> getParentDocuments() {
        return parentDocuments;
    }

    public void setParentDocuments(List<ParentDocument> parentDocuments) {
        this.parentDocuments = parentDocuments;
    }

    public void addParentDocument(ParentDocument parentDocument) {
        if (this.parentDocuments == null) {
            this.parentDocuments = new ArrayList<>();
        }
        this.parentDocuments.add(parentDocument);
    }

    public NonXMLBody getNonXMLBody() {
        return nonXMLBody;
    }

    public void setNonXMLBody(NonXMLBody nonXMLBody) {
        this.nonXMLBody = nonXMLBody;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public void addAttachment(Attachment attachment) {
        if (this.attachments == null) {
            this.attachments = new ArrayList<>();
        }
        this.attachments.add(attachment);
    }

    public List<String> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<String> receivers) {
        this.receivers = receivers;
    }

    public void addReceiver(String receiverId) {
        if (this.receivers == null) {
            this.receivers = new ArrayList<>();
        }
        this.receivers.add(receiverId);
    }

    public String getCdaXML() {
        return cdaXML;
    }

    public void setCdaXML(String cdaXML) {
        this.cdaXML = cdaXML;
    }

    public List<DistributionStatus> getDistributionStatuses() {
        return distributionStatuses;
    }

    public void setDistributionStatuses(List<DistributionStatus> distributionStatuses) {
        this.distributionStatuses = distributionStatuses;
    }
}
