package ca.uvic.leadlab.obibconnector.impl.support;

import ca.uvic.leadlab.obibconnector.facades.support.IStatus;
import ca.uvic.leadlab.obibconnector.models.response.OBIBResponse;
import ca.uvic.leadlab.obibconnector.utils.OBIBConnectorHelper;

public class Status implements IStatus {

    private String obibConnectorVersion;
    private String obibVersion;
    private String status;
    private String message;

    public Status(OBIBResponse response) {
        this.obibConnectorVersion = OBIBConnectorHelper.getOBIBConnectorVersion();
        this.obibVersion = response.getObibVersion();
        this.status = response.getStatus();
        this.message = response.getMessage();
    }

    @Override
    public String getObibConnectorVersion() {
        return obibConnectorVersion;
    }

    @Override
    public String getObibVersion() {
        return obibVersion;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
