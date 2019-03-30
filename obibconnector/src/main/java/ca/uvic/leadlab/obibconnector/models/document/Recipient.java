package ca.uvic.leadlab.obibconnector.models.document;

import ca.uvic.leadlab.obibconnector.models.registry.Provider;

public class Recipient extends Provider {

    private ReceivedOrganization receivedOrganization;

    public ReceivedOrganization getReceivedOrganization() {
        return receivedOrganization;
    }

    public void setReceivedOrganization(ReceivedOrganization receivedOrganization) {
        this.receivedOrganization = receivedOrganization;
    }
}
