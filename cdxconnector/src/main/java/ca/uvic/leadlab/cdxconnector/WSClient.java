package ca.uvic.leadlab.cdxconnector;

import ca.interiorhealth.BizTalkServiceInstance;
import ca.uvic.leadlab.cdxconnector.messages.*;
import org.hl7.v3.*;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.xml.namespace.QName;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyStore;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WSClient {

    private final Logger LOGGER = Logger.getLogger(WSClient.class.getName());

    // Base URL
    private String baseUrl;

    // Location credentials
    private String username;
    private String password;

    // Certificate
    private String certPath;
    private char[] certPass;

    public static void main(String[] args) {
        try {
            WSClient client = new WSClient("https://servicestest.bccdx.ca",
                    "cdxpostprod-otca", "VMK31",
                    "./certs/LEADlab_Keystore.jks", "LEADlab");

            client.listNewDocuments("cdxpostprod-otca");
        } catch (ConnectorException e) {
            e.printStackTrace();
        }
    }

    public WSClient(String baseUrl, String username, String password,
                    String certPath, String certPass) throws ConnectorException {
        this.baseUrl = baseUrl;
        this.username = username;
        this.password = password;
        this.certPath = certPath;
        this.certPass = certPass.toCharArray();

        setupSSLContext();
    }

    public String submitDocument(String locationId, String document) throws ConnectorException {
        try {
            RCMRIN000002UV01 request = new SubmitDocumentBuilder(UUID.randomUUID().toString()) // Unique Message ID (GUID)
                    .receiver("CDX") // ID Of receiver
                    .sender(locationId) // ID Of requestor
                    .document(UUID.randomUUID().toString(), document)
                    .build();

            WSUtil.logObject(LOGGER, "\nSubmit Document Request:\n", request);

            BizTalkServiceInstance documentService = new BizTalkServiceInstance(new URL(baseUrl + "/CDASubmitService/CDASubmit.svc?WSDL"));
            documentService.setHandlerResolver(handlerResolver(documentService.getServiceName()));
            MCCIIN000002UV01 response = documentService.getCustomBindingITwoWayAsync().submitCDA(request);

            WSUtil.logObject(LOGGER, "\nSubmit Document Response:\n", response);

            return WSUtil.parseObject(response);
        } catch (MessageBuilderException | MalformedURLException e) {
            LOGGER.log(Level.SEVERE, "Error submitting document", e);
            throw new ConnectorException("Error submitting document", e);
        }
    }

    public String listNewDocuments(String locationId) throws ConnectorException {
        try {
            MCCIIN100001UV01 request = new ListNewDocumentsBuilder(UUID.randomUUID().toString())// Unique Message ID (GUID)
                    .receiver("CDX") // ID Of receiver
                    .sender(locationId) // ID Of requestor
                    .build();

            WSUtil.logObject(LOGGER, "\nList New Documents Request:\n", request);

            RCMRAR000003UV01_Service documentService = createCDARequestService();
            RCMRIN000030UV01 response = documentService.getCDARequestEndpoint().mcciIN100001UV01(request);

            WSUtil.logObject(LOGGER, "\nList New Documents Response:\n", response);

            return WSUtil.parseObject(response);
        } catch (MessageBuilderException e) {
            LOGGER.log(Level.SEVERE, "Error listing new documents", e);
            throw new ConnectorException("Error listing new documents", e);
        }
    }

    public String searchDocumentById(String locationId, DocumentQueryParameterBuilder.DocumentType documentType,
                                     String documentId) throws ConnectorException {
        return searchDocument(locationId, new DocumentQueryParameterBuilder()
                .clinicId(locationId)
                .documentType(documentType)
                .documentId(documentId));
    }

    public String searchDocumentByDate(String locationId, DocumentQueryParameterBuilder.DocumentType documentType,
                                       ZonedDateTime lowDate, ZonedDateTime highDate) throws ConnectorException {
        return searchDocument(locationId, new DocumentQueryParameterBuilder()
                .clinicId(locationId)
                .documentType(documentType)
                .documentEffectiveTime(lowDate, true, highDate, true));
    }

    public String searchDocumentByEventTime(String locationId, DocumentQueryParameterBuilder.DocumentType documentType,
                                            ZonedDateTime lowDate, ZonedDateTime highDate) throws ConnectorException {
        return searchDocument(locationId, new DocumentQueryParameterBuilder()
                .clinicId(locationId)
                .documentType(documentType)
                .eventEffectiveTime(lowDate, true, highDate, true));
    }

    private String searchDocument(String locationId,
                                  DocumentQueryParameterBuilder queryParameterBuilder) throws ConnectorException {
        try {
            RCMRIN000029UV01 request = new SearchDocumentBuilder(UUID.randomUUID().toString())// Unique Message ID (GUID)
                    .receiver("CDX") // ID Of receiver
                    .sender(locationId) // ID Of requestor
                    .documentQuery(queryParameterBuilder) // query parameters
                    .build();

            WSUtil.logObject(LOGGER, "\nSearch Document Request:\n", request);

            RCMRAR000003UV01_Service documentService = createCDARequestService();
            RCMRIN000030UV01 response = documentService.getCDARequestEndpoint().rcmrIN000029UV01(request);

            WSUtil.logObject(LOGGER, "\nSearch Document Response:\n", response);

            return WSUtil.parseObject(response);
        } catch (MessageBuilderException e) {
            LOGGER.log(Level.SEVERE, "Error searching documents", e);
            throw new ConnectorException("Error searching documents", e);
        }
    }

    public String getDocument(String locationId, String documentId) throws ConnectorException {
        try {
            RCMRIN000031UV01 request = new GetDocumentBuilder(UUID.randomUUID().toString())// Unique Message ID (GUID)
                    .receiver("CDX") // ID Of receiver
                    .sender(locationId) // ID Of requestor
                    .documentQuery(new DocumentQueryParameterBuilder()
                            .clinicId(locationId)
                            .documentId(documentId)) // query parameters
                    .build();

            WSUtil.logObject(LOGGER, "\nGet Document Request:\n", request);

            RCMRAR000003UV01_Service documentService = createCDARequestService();
            RCMRIN000032UV01 response = documentService.getCDARequestEndpoint().rcmrIN000031UV01(request);

            WSUtil.logObject(LOGGER, "\nGet Document Response:\n", response);

            return WSUtil.parseObject(response);
        } catch (MessageBuilderException e) {
            LOGGER.log(Level.SEVERE, "Error getting documents", e);
            throw new ConnectorException("Error getting documents", e);
        }
    }

    private RCMRAR000003UV01_Service createCDARequestService() throws ConnectorException {
        try {
            RCMRAR000003UV01_Service requestService = new RCMRAR000003UV01_Service(new URL(baseUrl + "/CDArequestService/CDARequest.svc?WSDL"));
            requestService.setHandlerResolver(handlerResolver(requestService.getServiceName()));
            return requestService;
        } catch (MalformedURLException e) {
            throw new ConnectorException("Error creating CDARequestService", e);
        }
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

    private void setupSSLContext() throws ConnectorException {
        try {
            SSLContext context = SSLContext.getInstance("SSLv3");

            KeyManagerFactory factory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());

            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(new FileInputStream(certPath), certPass);

            factory.init(keyStore, certPass);

            context.init(factory.getKeyManagers(), null, null);

            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error initializing ssl context", e);
            throw new ConnectorException("Error initializing ssl context", e);
        }
    }

    private HandlerResolver handlerResolver(QName serviceName) {
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
}
