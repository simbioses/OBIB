package ca.uvic.leadlab.obibconnector.facades.send;

import ca.uvic.leadlab.obibconnector.facades.datatypes.*;

public interface ISubmitDoc extends ISubmit {


    //ISubmitDoc documentType(String type);

    IPerson patient();

    IParticipant author();

    IRecipient recipient();

    //ICustodianBuilder custodian();

    IParticipant dataEnterer();

    //IParticipant authenticator();

    IParticipant participant();

    ISubmitDoc content(String text);

    ISubmitDoc attach(AttachmentType type, Byte[] data);

}
