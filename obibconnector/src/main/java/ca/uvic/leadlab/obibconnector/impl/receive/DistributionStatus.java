package ca.uvic.leadlab.obibconnector.impl.receive;

import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;
import ca.uvic.leadlab.obibconnector.facades.receive.IDistributionStatus;
import ca.uvic.leadlab.obibconnector.facades.registry.IClinic;
import ca.uvic.leadlab.obibconnector.facades.registry.IProvider;
import ca.uvic.leadlab.obibconnector.impl.registry.Clinic;
import ca.uvic.leadlab.obibconnector.impl.registry.Provider;
import ca.uvic.leadlab.obibconnector.utils.DateFormatter;

import java.util.Date;

public class DistributionStatus implements IDistributionStatus {

    private final ca.uvic.leadlab.obibconnector.models.document.DistributionStatus distributionStatus;

    private String statusCode;
    private String statusName;
    private Date statusTime;

    private IProvider recipient;

    private IClinic receivedOrganization;

    DistributionStatus(ca.uvic.leadlab.obibconnector.models.document.DistributionStatus distributionStatus) throws OBIBException  {
        this.distributionStatus = distributionStatus;

        statusCode = distributionStatus.getStatusCode();
        statusName = distributionStatus.getStatusName();
        if (distributionStatus.getStatusTime() != null && !distributionStatus.getStatusTime().isEmpty()) {
            statusTime = DateFormatter.parseDateTime(distributionStatus.getStatusTime());
        }

        if (distributionStatus.getRecipient() != null) {
            recipient = new Provider(distributionStatus.getRecipient());

            if (distributionStatus.getRecipient().getReceivedOrganization() != null) {
                receivedOrganization = new Clinic(distributionStatus.getRecipient().getReceivedOrganization());
            }
        }
    }

    @Override
    public String getStatusCode() {
        return statusCode;
    }

    @Override
    public String getStatusName() {
        return statusName;
    }

    @Override
    public Date getStatusTime() {
        return statusTime;
    }

    @Override
    public IProvider getRecipient() {
        return recipient;
    }

    @Override
    public IClinic getReceivedOrganization() {
        return receivedOrganization;
    }
}
