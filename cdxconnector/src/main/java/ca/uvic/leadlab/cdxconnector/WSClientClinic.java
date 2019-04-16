package ca.uvic.leadlab.cdxconnector;

import ca.uvic.leadlab.cdxconnector.messages.ListClinicBuilder;
import org.apache.commons.lang3.StringUtils;
import org.hl7.v3.ClinicQuery;
import org.hl7.v3.PRPMIN406010UV01;
import org.hl7.v3.PRPMIN406110UV01;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;
import java.util.logging.Level;

public class WSClientClinic extends WSClient {

    public WSClientClinic(String baseUrl, String username, String password,
                          String certPath, String certPass) throws ConnectorException {
        super(baseUrl, username, password, certPath, certPass);
    }

    public String listClinics(String locationId,
                              String clinicId, String clinicName, String clinicAddress) throws ConnectorException {
        try {
            // Create the builder with Unique Message ID (GUID) and Requestor ID
            ListClinicBuilder builder = new ListClinicBuilder(UUID.randomUUID().toString()).sender(locationId);

            // CONF-CDXPR62a: If clinicID is present, then organizationName and organizationAddress SHALL NOT be present.
            if (StringUtils.isNotBlank(clinicId)) {
                builder.queryById("2.16.840.1.113883.3.277.100.2", clinicId); // CONF-CDXPR061
            } else {
                if (StringUtils.isNotBlank(clinicName)) {
                    builder.queryByName(clinicName); // CONF-CDXPR060
                }
                if (StringUtils.isNotBlank(clinicAddress)) {
                    builder.queryByAddress(clinicAddress); // CONF- CDXPR062
                }
            }

            PRPMIN406010UV01 request = builder.build();

            WSUtil.logObject(LOGGER, "\nList Clinics Request:\n", request);

            ClinicQuery clinicQuery = new ClinicQuery(new URL(baseUrl + "/RegistrySearch/ClinicQuery.svc?WSDL"));
            clinicQuery.setHandlerResolver(handlerResolver(clinicQuery.getServiceName()));
            PRPMIN406110UV01 response = clinicQuery.getCustomBindingPRPMAR400013UV().prpmIN406010UV01(request);

            WSUtil.logObject(LOGGER, "\nList Clinics Response:\n", response);

            return WSUtil.parseObject(response, false);
        } catch (MalformedURLException e) {
            LOGGER.log(Level.SEVERE, "Error listing clinics", e);
            throw new ConnectorException("Error listing clinics", e);
        }
    }
}
