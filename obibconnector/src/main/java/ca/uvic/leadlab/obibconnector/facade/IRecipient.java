package ca.uvic.leadlab.obibconnector.facade;

public interface IRecipient extends IPerson<IRecipient> {

    IRecipient recipientOrganization(String id, String name);

}
