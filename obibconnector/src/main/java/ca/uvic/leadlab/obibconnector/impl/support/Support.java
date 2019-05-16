package ca.uvic.leadlab.obibconnector.impl.support;

import ca.uvic.leadlab.obibconnector.facades.Config;
import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;
import ca.uvic.leadlab.obibconnector.facades.support.ISupport;
import ca.uvic.leadlab.obibconnector.models.response.OBIBResponse;
import ca.uvic.leadlab.obibconnector.models.support.ErrorMessage;
import ca.uvic.leadlab.obibconnector.rest.IOscarInformation;
import ca.uvic.leadlab.obibconnector.rest.OBIBRequestException;
import ca.uvic.leadlab.obibconnector.rest.RestClient;

public class Support implements ISupport {

    private final IOscarInformation services;

    public Support(Config conf) {
        this.services = new RestClient(conf.getUrl(), conf.getClinicId());
    }

    @Override
    public void notifyError(String context, String trace) throws OBIBException {
        try {
            OBIBResponse response = services.notifyError(new ErrorMessage(context, trace));

            if (!response.isOK()) {
                throw new OBIBRequestException(response.getMessage(), response.getObibErrors());
            }
        } catch (OBIBRequestException e) {
            throw new OBIBException("Error notifying error.", e);
        }
    }
}
