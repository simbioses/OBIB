package ca.uvic.leadlab.obibconnector.facade;

public interface ISubmitDoc extends ISubmit {

    enum AttachmentType {
        PDF, IMAGE
    }

    //ISubmitDoc documentType(String type);

    IPersonBuilder patient();

    IParticipantBuilder author();

    IRecipientBuilder recipient();

    //ICustodianBuilder custodian();

    IParticipantBuilder dataEnterer();

    //IParticipantBuilder authenticator();

    IParticipantBuilder participant();

    ISubmitDoc content(String text);

    ISubmitDoc attach(AttachmentType type, Byte[] data);

}
