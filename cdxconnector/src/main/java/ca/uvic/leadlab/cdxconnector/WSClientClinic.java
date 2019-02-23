package ca.uvic.leadlab.cdxconnector;

import ca.uvic.leadlab.cdxconnector.messages.ListClinicBuilder;
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

    public String listClinics(String locationId) throws ConnectorException {
        try {
            PRPMIN406010UV01 request = new ListClinicBuilder(UUID.randomUUID().toString()) // Unique Message ID (GUID)
                    .sender(locationId) // ID Of requestor
                    .queryById("2.16.840.1.113883.3.277.100.2", locationId)
                    .build();

            WSUtil.logObject(LOGGER, "\nList Clinics Request:\n", request);

            ClinicQuery clinicQuery = new ClinicQuery(new URL(baseUrl + "/RegistrySearch/ClinicQuery.svc?WSDL"));
            clinicQuery.setHandlerResolver(handlerResolver(clinicQuery.getServiceName()));
            PRPMIN406110UV01 response = clinicQuery.getCustomBindingPRPMAR400013UV().prpmIN406010UV01(request);

            WSUtil.logObject(LOGGER, "\nList Clinics Response:\n", response);

            return WSUtil.parseObject(response);
        } catch (MalformedURLException e) {
            LOGGER.log(Level.SEVERE, "Error listing clinics", e);
            throw new ConnectorException("Error listing clinics", e);
        }
    }
}
