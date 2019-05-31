package ca.uvic.leadlab.cdxconnector;

import ca.uvic.leadlab.cdxconnector.messages.registry.ListProviderBuilder;
import org.apache.commons.lang3.StringUtils;
import registrysearch.PRPMIN306010UV;
import registrysearch.PRPMIN306011UV;
import registrysearch.ProviderQuery;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;
import java.util.logging.Level;

public class WSClientProvider extends WSClient {

    public WSClientProvider(String baseUrl, String username,
                            String password, String certPath, String certPass) throws ConnectorException {
        super(baseUrl, username, password, certPath, certPass);
    }

    public String listProviders(String locationId,
                                String clinicId, String providerId, String providerName) throws ConnectorException {
        try {
            ListProviderBuilder builder = new ListProviderBuilder(UUID.randomUUID().toString()).sender(locationId);

            // CONF-CDXPR099: If providerID is present, then providerName and sdlcId SHALL NOT be present.
            if (StringUtils.isNotBlank(providerId)) {
                builder.queryByProviderId("2.16.840.1.113883.3.40.2.11", providerId); // CONF-CDXPR012
            } else {
                if (StringUtils.isNotBlank(providerName)) {
                    builder.queryByProviderName(providerName); // CONF-CDXPR011
                }
                if (StringUtils.isNotBlank(clinicId)) {
                    builder.queryBySdlcId("2.16.840.1.113883.3.277.100.2", clinicId); // CONF-CDXPR013
                }
            }

            PRPMIN306010UV request = builder.build();

            WSUtil.logObject(LOGGER, "\nList Provider Request:\n", request);

            ProviderQuery providerQuery = new ProviderQuery(new URL(baseUrl + "/RegistrySearch/ProviderQuery.svc?WSDL"));
            providerQuery.setHandlerResolver(handlerResolver(providerQuery.getServiceName()));
            PRPMIN306011UV response = providerQuery.getCustomBindingPRPMAR300013UV().prpmIN306010UV(request);

            WSUtil.logObject(LOGGER, "\nList Provider Response:\n", response);

            return WSUtil.parseObject(response, false);
        } catch (MalformedURLException e) {
            LOGGER.log(Level.SEVERE, "Error listing providers", e);
            throw new ConnectorException("Error listing providers", e);
        }
    }
}
