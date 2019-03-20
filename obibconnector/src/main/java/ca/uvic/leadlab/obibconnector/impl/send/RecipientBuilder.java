package ca.uvic.leadlab.obibconnector.impl.send;

import ca.uvic.leadlab.obibconnector.facades.send.IRecipient;
import ca.uvic.leadlab.obibconnector.facades.send.ISubmitDoc;
import ca.uvic.leadlab.obibconnector.models.OBIBConnectorEntities.IPerson;
import ca.uvic.leadlab.obibconnector.models.OBIBConnectorEntities.ReceivedOrganization;

public class RecipientBuilder<P extends IPerson> extends PersonBuilder<P, IRecipient> implements IRecipient {

    RecipientBuilder(ISubmitDoc submitDoc, P person) {
        super(submitDoc, person);
    }

    @Override
    public IRecipient recipientOrganization(String id, String name) {
        person.setReceivedOrganization(new ReceivedOrganization(id, name));
        return this;
    }
}
