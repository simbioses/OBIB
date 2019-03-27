package ca.uvic.leadlab.obibconnector.models.document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class  ClinicalDocument {

    private String templateId;
    private String id;
    private String code;
    private Date effectiveTime;
    private Patient patient;
    private List<Author> author;
    private List<Recipient> recipient;
    private Custodian custodian;
    private DataEnterer dataEnterer;
    private List<Authenticator> authenticator;
    private List<Participant> participant;
    private NonXMLBody nonXMLBody;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public void addAuthor(Author author) {
        if (this.author == null) {
            this.author = new ArrayList<>();
        }
        this.author.add(author);
    }

    public List<Recipient> getRecipient() {
        return recipient;
    }

    public void setRecipient(List<Recipient> recipient) {
        this.recipient = recipient;
    }

    public void addRecipient(Recipient recipient) {
        if (this.recipient == null) {
            this.recipient = new ArrayList<>();
        }
        this.recipient.add(recipient);
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

    public List<Authenticator> getAuthenticator() {
        return authenticator;
    }

    public void setAuthenticator(List<Authenticator> authenticator) {
        this.authenticator = authenticator;
    }

    public List<Participant> getParticipant() {
        return participant;
    }

    public void setParticipant(List<Participant> participant) {
        this.participant = participant;
    }

    public void addParticipant(Participant participant) {
        if (this.participant == null) {
            this.participant = new ArrayList<>();
        }
        this.participant.add(participant);
    }

    public NonXMLBody getNonXMLBody() {
        return nonXMLBody;
    }

    public void setNonXMLBody(NonXMLBody nonXMLBody) {
        this.nonXMLBody = nonXMLBody;
    }
}
