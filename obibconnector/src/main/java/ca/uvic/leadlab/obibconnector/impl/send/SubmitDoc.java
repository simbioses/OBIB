package ca.uvic.leadlab.obibconnector.impl.send;

import ca.uvic.leadlab.obibconnector.facades.datatypes.*;
import ca.uvic.leadlab.obibconnector.facades.send.IParticipant;
import ca.uvic.leadlab.obibconnector.facades.send.IPerson;
import ca.uvic.leadlab.obibconnector.facades.send.IRecipient;
import ca.uvic.leadlab.obibconnector.facades.send.ISubmitDoc;
import ca.uvic.leadlab.obibconnector.models.document.*;

public class SubmitDoc implements ISubmitDoc {

    final ClinicalDocument document;
    final String clinicId;

    public SubmitDoc(String clinicId) {
        this.document = new ClinicalDocument();
        this.clinicId = clinicId;
    }

    @Override
    public IPerson patient() {
        Patient patient = new Patient();
        document.setPatient(patient);
        return new PersonBuilder<>(this, patient);
    }

    @Override
    public IParticipant author() {
        Author author = new Author();
        document.addAuthor(author);
        return new ParticipantBuilder<>(this, author);
    }

    @Override
    public IRecipient recipient() {
        Recipient recipient = new Recipient();
        document.addRecipient(recipient);
        return new RecipientBuilder<>(this, recipient);
    }

    @Override
    public IParticipant dataEnterer() {
        DataEnterer dataEnterer = new DataEnterer();
        document.setDataEnterer(dataEnterer);
        return new ParticipantBuilder<>(this, dataEnterer);
    }

    @Override
    public IParticipant participant() {
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
    public Object submit() {
        return document; // TODO call REST Client
    }
}
