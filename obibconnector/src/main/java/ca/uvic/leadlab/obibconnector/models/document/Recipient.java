package ca.uvic.leadlab.obibconnector.models.document;

import ca.uvic.leadlab.obibconnector.models.registry.Provider;

public class Recipient extends Provider {

    private String typeCode;
    private ReceivedOrganization receivedOrganization;

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public ReceivedOrganization getReceivedOrganization() {
        return receivedOrganization;
    }

    public void setReceivedOrganization(ReceivedOrganization receivedOrganization) {
        this.receivedOrganization = receivedOrganization;
    }

    public boolean isOrganizationOnly() {
        return getReceivedOrganization() != null && (getName() == null || getName().isEmpty());
    }
}
