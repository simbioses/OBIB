package ca.uvic.leadlab.obibconnector.facade;

import ca.uvic.leadlab.obibconnector.models.OBIBConnectorEntities.*;
import com.google.gson.Gson;

public class SubmitDoc implements ISubmitDoc {

    ClinicalDocument document;

    public SubmitDoc(String clinicId) {
        this.document = new ClinicalDocument();
        this.document.setLocationId(clinicId);
    }

    @Override
    public IPersonBuilder patient() {
        Patient patient = new Patient();
        document.setPatient(patient);
        return new PersonBuilder<>(this, patient);
    }

    @Override
    public IParticipantBuilder author() {
        Author author = new Author();
        document.addAuthor(author);
        return new ParticipantBuilder<>(this, author);
    }

    @Override
    public IRecipientBuilder recipient() {
        Recipient recipient = new Recipient();
        document.addRecipient(recipient);
        return new RecipientBuilder<>(this, recipient);
    }

    @Override
    public IParticipantBuilder dataEnterer() {
        DataEnterer dataEnterer = new DataEnterer();
        document.setDataEnterer(dataEnterer);
        return new ParticipantBuilder<>(this, dataEnterer);
    }

    @Override
    public IParticipantBuilder participant() {
        Participant participant = new Participant();
        document.addParticipant(participant);
        return new ParticipantBuilder<>(this, participant);
    }

    @Override
    public ISubmitDoc content(String text) {
        document.setNonXMLBody(new NonXMLBody(text, "text/plain"));
        return this;
    }

    @Override
    public ISubmitDoc attach(AttachmentType type, Byte[] data) {
        //document.setNonXMLBody(new NonXMLBody(data, type));
        //return this;
        throw new UnsupportedOperationException("Not implemented, yet.");
    }

    @Override
    public String submit() {
        return new Gson().toJson(document); // TODO call REST Client
    }
}
