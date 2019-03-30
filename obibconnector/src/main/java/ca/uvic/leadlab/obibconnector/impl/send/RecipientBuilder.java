package ca.uvic.leadlab.obibconnector.impl.send;

import ca.uvic.leadlab.obibconnector.facades.datatypes.RecipientType;
import ca.uvic.leadlab.obibconnector.facades.send.IRecipient;
import ca.uvic.leadlab.obibconnector.facades.send.ISubmitDoc;
import ca.uvic.leadlab.obibconnector.models.document.ReceivedOrganization;
import ca.uvic.leadlab.obibconnector.models.document.Recipient;

public class RecipientBuilder<P extends Recipient> extends PersonBuilder<P, IRecipient> implements IRecipient {

    RecipientBuilder(ISubmitDoc submitDoc, P person) {
        super(submitDoc, person);
        this.person.setTypeCode(RecipientType.SECONDARY.label); // default recipient type code
    }

    @Override
    public IRecipient primary() {
        person.setTypeCode(RecipientType.PRIMARY.label);
        return this;
    }

    @Override
    public IRecipient recipientOrganization(String id, String name) {
        ReceivedOrganization organization = new ReceivedOrganization();
        //TODO organization.setId(id);
        organization.setName(name);
        person.setReceivedOrganization(organization);
        return this;
    }
}
