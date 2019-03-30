package ca.uvic.leadlab.obibconnector.facades.send;

import ca.uvic.leadlab.obibconnector.facades.datatypes.*;

public interface ISubmitDoc extends ISubmit {

    IPatient patient();

    IAuthor author();

    IRecipient recipient();

    //ICustodianBuilder custodian();

    IDataEnterer dataEnterer();

    //IAuthenticator authenticator();

    IParticipant participant();

    ISubmitDoc documentType(String type);

    ISubmitDoc content(String text);

    ISubmitDoc attach(AttachmentType type, Byte[] data);

}
