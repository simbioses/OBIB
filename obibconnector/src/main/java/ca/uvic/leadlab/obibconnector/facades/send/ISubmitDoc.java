package ca.uvic.leadlab.obibconnector.facades.send;

import ca.uvic.leadlab.obibconnector.facades.datatypes.*;
import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;

public interface ISubmitDoc extends ISubmit {

    ISubmitDoc newDoc();

    ISubmitDoc updateDoc(String documentId, int versionNumber);

    ISubmitDoc cancelDoc(String documentId, int versionNumber);

    IPatient patient();

    IAuthor author();

    IRecipient recipient();

    //ICustodianBuilder custodian();

    IDataEnterer dataEnterer();

    //IAuthenticator authenticator();

    IParticipant participant();

    ISubmitDoc documentType(DocumentType type);

    IOrder inFulfillmentOf();

    IServiceEvent documentationOf();

    ISubmitDoc content(String text) throws OBIBException;

    ISubmitDoc attach(AttachmentType type, String reference, byte[] data) throws OBIBException;

    ISubmitDoc receiverId(String receiverId);

}
