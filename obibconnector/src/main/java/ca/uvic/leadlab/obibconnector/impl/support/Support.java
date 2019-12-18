package ca.uvic.leadlab.obibconnector.impl.support;

import ca.uvic.leadlab.obibconnector.facades.Config;
import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;
import ca.uvic.leadlab.obibconnector.facades.support.IStatus;
import ca.uvic.leadlab.obibconnector.facades.support.ISupport;
import ca.uvic.leadlab.obibconnector.models.queries.SearchClinicCriteria;
import ca.uvic.leadlab.obibconnector.models.response.ListClinicsResponse;
import ca.uvic.leadlab.obibconnector.models.response.OBIBResponse;
import ca.uvic.leadlab.obibconnector.models.support.ErrorMessage;
import ca.uvic.leadlab.obibconnector.rest.*;
import ca.uvic.leadlab.obibconnector.utils.OBIBConnectorHelper;

public class Support implements ISupport {

    private static final String CDX_CLINIC_ID = OBIBConnectorHelper.getProperty("cdx.clinic.id");

    private final IOscarInformation services;

    public Support(Config conf) {
        this.services = new RestClient(conf.getUrl());
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

    @Override
    public IStatus checkConnectivity() throws OBIBException {
        try {
            // Check connection with CDX by querying the clinic
            ListClinicsResponse response = services.listClinics(SearchClinicCriteria.byClinicId(CDX_CLINIC_ID));
            if (!response.isOK()) {
                throw new OBIBRequestException(response.getMessage(), response.getObibErrors());
            }
            return new Status(response);
        } catch (OBIBRequestException e) {
            throw new OBIBException("Error checking connectivity.", e);
        }
    }
}
