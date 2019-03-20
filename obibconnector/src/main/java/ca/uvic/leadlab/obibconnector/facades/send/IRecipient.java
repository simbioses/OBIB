package ca.uvic.leadlab.obibconnector.facades.send;

public interface IRecipient extends IPerson<IRecipient> {

    IRecipient recipientOrganization(String id, String name);

}
