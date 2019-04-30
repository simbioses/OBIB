package ca.uvic.leadlab.obibconnector.impl.send;

import ca.uvic.leadlab.obibconnector.facades.Config;
import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;
import ca.uvic.leadlab.obibconnector.facades.datatypes.*;
import ca.uvic.leadlab.obibconnector.facades.receive.IDocument;
import ca.uvic.leadlab.obibconnector.facades.send.*;
import ca.uvic.leadlab.obibconnector.impl.ImplHelper;
import ca.uvic.leadlab.obibconnector.impl.receive.Document;
import ca.uvic.leadlab.obibconnector.models.common.Loinc;
import ca.uvic.leadlab.obibconnector.models.document.*;
import ca.uvic.leadlab.obibconnector.models.response.SubmitDocumentResponse;
import ca.uvic.leadlab.obibconnector.rest.IOscarInformation;
import ca.uvic.leadlab.obibconnector.rest.OBIBRequestException;
import ca.uvic.leadlab.obibconnector.rest.RestClient;

import javax.xml.bind.DatatypeConverter;

public class SubmitDoc implements ISubmitDoc {

    private final IOscarInformation services;

    private ClinicalDocument document;

    public SubmitDoc(Config conf) {
        this.services = new RestClient(conf.getUrl(), conf.getClinicId());
    }

    @Override
    public ISubmitDoc newDoc() {
        this.document = new ClinicalDocument();
        return this;
    }

    @Override
    public IPatient patient() {
        Patient patient = new Patient();
        document.setPatient(patient);
        return new PatientBuilder<>(this, patient);
    }

    @Override
    public IAuthor author() {
        Author author = new Author();
        document.addAuthor(author);
        return new AuthorBuilder<>(this, author);
    }

    @Override
    public IRecipient recipient() {
        Recipient recipient = new Recipient();
        document.addRecipient(recipient);
        return new RecipientBuilder<>(this, recipient);
    }

    @Override
    public IDataEnterer dataEnterer() {
        DataEnterer dataEnterer = new DataEnterer();
        document.setDataEnterer(dataEnterer);
        return new DataEntererBuilder<>(this, dataEnterer);
    }

    @Override
    public IParticipant participant() {
        Participant participant = new Participant();
        document.addParticipant(participant);
        return new ParticipantBuilder<>(this, participant);
    }

    @Override
    public ISubmitDoc documentType(DocumentType type) {
        document.setLoinc(new Loinc(type.code, type.label));
        return null;
    }

    @Override
    public IOrder inFulfillmentOf() {
        Order order = new Order();
        document.addOrder(order);
        return new OrderBuilder(this, order);
    }

    @Override
    public ISubmitDoc content(String text) {
        document.setNonXMLBody(new NonXMLBody(text, MediaType.TEXT.mediaType));
        return this;
    }

    @Override
    public ISubmitDoc attach(AttachmentType type, String reference, byte[] data) throws OBIBException {
        document.setNonXMLBody(new NonXMLBody(reference, type.mediaType)); // TODO multiple attachments?
        document.addAttachment(new Attachment(ImplHelper.calculateHash(data),
                type.mediaType,
                DatatypeConverter.printBase64Binary(data),
                reference));
        return this;
    }

    @Override
    public ISubmitDoc receiverId(String receiverId) {
        document.addReceiver(receiverId);
        return this;
    }

    @Override
    public IDocument submit() throws OBIBException {
        try {
            SubmitDocumentResponse response = services.submitCDA(document);

            if (!response.isOK()) {
                throw new OBIBRequestException(response.getMessage());
            }

            return new Document(response.getDocument());
        } catch (OBIBRequestException e) {
            throw new OBIBException("Error submitting document.", e);
        }
    }

    public ClinicalDocument getDocument() {
        return document;
    }
}
