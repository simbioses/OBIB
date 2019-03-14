
package ca.uvic.leadlab.models.OBIBConnectorEntities;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class  ClinicalDocument {

    @Expose
    private List<Authenticator> authenticator;
    @Expose
    private List<Author> author;
    @Expose
    private Custodian custodian;
    @Expose
    private DataEnterer dataEnterer;
    @Expose
    private String effectiveTime;


    @Expose
    private String functionCode;
    @Expose
    private NonXMLBody nonXMLBody;
    @Expose
    private List<Participant> participant;
    @Expose
    private Patient patient;
    @Expose
    private List<Recipient> recipient;

    public List<Authenticator> getAuthenticator() {
        return authenticator;
    }

    public void setAuthenticator(List<Authenticator> authenticator) {
        this.authenticator = authenticator;
    }

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
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

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }



    public NonXMLBody getNonXMLBody() {
        return nonXMLBody;
    }

    public void setNonXMLBody(NonXMLBody nonXMLBody) {
        this.nonXMLBody = nonXMLBody;
    }

    public List<Participant> getParticipant() {
        return participant;
    }

    public void setParticipant(List<Participant> participant) {
        this.participant = participant;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Recipient> getRecipient() {
        return recipient;
    }

    public void setRecipient(List<Recipient> recipient) {
        this.recipient = recipient;
    }

}
