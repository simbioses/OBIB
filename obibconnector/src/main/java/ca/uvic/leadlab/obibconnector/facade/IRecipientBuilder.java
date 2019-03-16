package ca.uvic.leadlab.obibconnector.facade;

public interface IRecipientBuilder extends IPersonBuilder<IRecipientBuilder> {

    IRecipientBuilder recipientOrganization(String id, String name);

}
