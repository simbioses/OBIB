package ca.uvic.leadlab.obibconnector.facades.receive;

import ca.uvic.leadlab.obibconnector.facades.registry.IClinic;
import ca.uvic.leadlab.obibconnector.facades.registry.IProvider;

import java.util.Date;

public interface IDistributionStatus {

    String getStatusCode();
    String getStatusName();
    Date getStatusTime();

    IProvider getRecipient();

    IClinic getReceivedOrganization();
}
