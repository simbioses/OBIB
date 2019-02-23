package ca.uvic.leadlab.cdxconnector;

import ca.uvic.leadlab.cdxconnector.messages.ListProviderBuilder;
import org.hl7.v3.PRPMIN306010UV;
import org.hl7.v3.PRPMIN306011UV;
import org.hl7.v3.ProviderQuery;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;
import java.util.logging.Level;

public class WSClientProvider extends WSClient {

    public WSClientProvider(String baseUrl, String username,
                            String password, String certPath, String certPass) throws ConnectorException {
        super(baseUrl, username, password, certPath, certPass);
    }

    public String listProviders(String locationId) throws ConnectorException {
        try {
            PRPMIN306010UV request = new ListProviderBuilder(UUID.randomUUID().toString())
                    .sender(locationId)
                    .queryBysdlcId("2.16.840.1.113883.3.277.100.2", locationId)
                    .build();

            WSUtil.logObject(LOGGER, "\nList Provider Request:\n", request);

            ProviderQuery providerQuery = new ProviderQuery(new URL(baseUrl + "/RegistrySearch/ProviderQuery.svc?WSDL"));
            providerQuery.setHandlerResolver(handlerResolver(providerQuery.getServiceName()));
            PRPMIN306011UV response = providerQuery.getCustomBindingPRPMAR300013UV().prpmIN306010UV(request);

            WSUtil.logObject(LOGGER, "\nList Provider Response:\n", response);

            return WSUtil.parseObject(response);
        } catch (MalformedURLException e) {
            LOGGER.log(Level.SEVERE, "Error listing providers", e);
            throw new ConnectorException("Error listing providers", e);
        }
    }
}
