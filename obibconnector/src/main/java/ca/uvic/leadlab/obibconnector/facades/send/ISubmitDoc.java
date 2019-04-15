package ca.uvic.leadlab.obibconnector.facades.send;

import ca.uvic.leadlab.obibconnector.facades.datatypes.*;
import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;

public interface ISubmitDoc extends ISubmit {

    ISubmitDoc newDoc();

    IPatient patient();

    IAuthor author();

    IRecipient recipient();

    //ICustodianBuilder custodian();

    IDataEnterer dataEnterer();

    //IAuthenticator authenticator();

    IParticipant participant();

    ISubmitDoc documentType(DocumentType type);

    IOrder inFulfillmentOf();

    ISubmitDoc content(String text);

    ISubmitDoc attach(AttachmentType type, String reference, byte[] data) throws OBIBException;

}
