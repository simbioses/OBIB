package ca.uvic.leadlab.obibconnector.facade;

import ca.uvic.leadlab.obibconnector.models.OBIBConnectorEntities.IPerson;
import ca.uvic.leadlab.obibconnector.models.OBIBConnectorEntities.ReceivedOrganization;

public class RecipientBuilder<P extends IPerson> extends PersonBuilder<P, IRecipientBuilder> implements IRecipientBuilder {

    RecipientBuilder(ISubmitDoc submitDoc, P person) {
        super(submitDoc, person);
    }

    @Override
    public IRecipientBuilder recipientOrganization(String id, String name) {
        person.setReceivedOrganization(new ReceivedOrganization(id, name));
        return this;
    }
}
