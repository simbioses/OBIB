package ca.uvic.leadlab.cdxconnector;

import ca.bccdx.*;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.xml.namespace.QName;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;
import java.io.FileInputStream;
import java.security.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WSClient {

    private final Logger LOGGER = Logger.getLogger(WSClient.class.getName());

    // Base URLs
    //private String baseUrl = "https://servicesdev.bccdx.ca/";
    //private String baseUrl = "https://servicestest.bccdx.ca/";
    //private String baseUrl = "https://services.bccdx.ca/";

    private String username = "cdxpostprod-otca";
    private String password = "VMK31";

    private String locationId = username;
    private String clinicName = "Oscar Test Clinic A";

    private String certPath = "/home/ocosta/Projects/UVic/LEADLab/source/cdxconnector/certs/LEADlab_Keystore.jks";
    private String certPass = "LEADlab";

    public static void main(String[] args) {
        WSClient client = new WSClient();
        client.listProviders();
    }

    private static final WSObjectFactory factory = new WSObjectFactory();

    private void listProviders() {
        try {
            PRPMIN406010UV01 request = createRequest(locationId);
            request.setControlActProcess(createControlActProcess("2.16.840.1.113883.3.277.100.2", locationId));

            setupSSLContext(certPath, certPass.toCharArray());

            ClinicQuery clinicQuery = new ClinicQuery();
            clinicQuery.setHandlerResolver(handlerResolver(clinicQuery.getServiceName()));

            PRPMIN406110UV01 response = clinicQuery.getCustomBindingPRPMAR400013UV().prpmIN406010UV01(request);

            System.out.println(response);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error on listProviders", e);
        }
    }

    private void setupSSLContext(String certPath, char[] certPassword) {
        try {
            SSLContext context = SSLContext.getInstance("SSLv3");

            KeyManagerFactory factory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());

            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(new FileInputStream(certPath), certPassword);

            factory.init(keyStore, certPassword);

            context.init(factory.getKeyManagers(), null, null);

            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error on setupSSLContext", e);
        }
    }

    public HandlerResolver handlerResolver(QName serviceName) {
        return new HandlerResolver() {
            @Override
            public List<Handler> getHandlerChain(PortInfo portInfo) {
                List<Handler> handlerChain = new ArrayList<>();
                handlerChain.add(new AuthenticationHandler(username, password));
                handlerChain.add(new CustomEnvelopHandler(serviceName));
                //handlerChain.add(new LoggingHandler());
                return handlerChain;
            }
        };
    }

    private PRPMIN406010UV01 createRequest(String senderAgentOrganizationIdExtension) {
        // Used by CDX to search for 1 or more providers that match a specific criteria.
        PRPMIN406010UV01 request = factory.createPRPMIN406010UV01();
        request.setITSVersion("XML_1.0");

        // Transmission Wrapper
        request.setId(factory.createII("2.16.840.1.113883.3.277.100.1", UUID.randomUUID().toString())); // Unique Message ID (GUID)
        request.setCreationTime(factory.createTS(ZonedDateTime.now())); // Time of transmission yyyyMMddHHMMss-Z (201209241316-0700)
        request.setVersionCode(factory.createCS("2010Normative"));
        request.setInteractionId(factory.createII("2.16.840.1.113883.1.6", "PRPM_IN306010UV"));
        request.setProcessingCode(factory.createCS("P")); // D = Debugging P = Production T = Training
        request.setProcessingModeCode(factory.createCS("T")); // sending T for current processing
        request.setAcceptAckCode(factory.createCS("AL")); // AL = Always ER = Error/reject only NE = never
        request.getReceiver().add(factory.createMCCIMT000100UV01Receiver(createDevice("CDX"))); // The receiver is always the CDX system
        request.setSender(factory.createMCCIMT000100UV01Sender(createDevice(senderAgentOrganizationIdExtension))); // ID Of requestor

        return request;
    }

    private MCCIMT000100UV01Device createDevice(String agentOrganizationIdExtension) {
        MCCIMT000100UV01Organization representedOrganization = factory.createMCCIMT000100UV01Organization();
        representedOrganization.setClassCode(EntityClassOrganization.ORG);
        representedOrganization.setDeterminerCode(EntityDeterminerSpecific.INSTANCE);
        representedOrganization.getId().add(factory.createII("2.16.840.1.113883.3.277.100.2",
                "CDX Clinic ID",
                agentOrganizationIdExtension));

        MCCIMT000100UV01Agent agent = factory.createMCCIMT000100UV01Agent();
        agent.setClassCode(RoleClassAgent.AGNT);
        agent.setRepresentedOrganization(representedOrganization);

        MCCIMT000100UV01Device device = factory.createMCCIMT000100UV01Device();
        device.setClassCode(EntityClassDevice.DEV);
        device.setDeterminerCode(EntityDeterminerSpecific.INSTANCE);
        device.getId().add(factory.createII(NullFlavor.NA));
        device.setAsAgent(agent);

        return device;
    }

    public PRPMIN406010UV01QUQIMT021001UV01ControlActProcess createControlActProcess(String root, String extension) {
        // Control Act Wrapper - this specifies the search criteria
        PRPMIN406010UV01QUQIMT021001UV01ControlActProcess controlActProcess = factory
                .createPRPMIN406010UV01QUQIMT021001UV01ControlActProcess();
        controlActProcess.setClassCode(ActClassControlAct.CACT);
        controlActProcess.setMoodCode(XActMoodIntentEvent.RQO);

        PRPMMT406010UV01QueryByParameterPayload query = factory.createPRPMMT406010UV01QueryByParameterPayload();
        query.setStatusCode(factory.createCS("new"));
        //TODO query.getOrganizationAddress().add(factory.createPRPMMT406010UV01OrganizationAddress(""));
        query.getOrganizationID().add(factory.createPRPMMT406010UV01OrganizationID(root, extension));
        //TODO query.getOrganizationName().add(factory.createPRPMMT406010UV01OrganizationName(name));
        controlActProcess.setQueryByParameterPayload(query);

        return controlActProcess;
    }
}
