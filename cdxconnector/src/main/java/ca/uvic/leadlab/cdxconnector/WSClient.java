package ca.uvic.leadlab.cdxconnector;

import ca.interiorhealth.BizTalkServiceInstance;

import ca.uvic.leadlab.cdxconnector.messages.ListClinicBuilder;
import ca.uvic.leadlab.cdxconnector.messages.ListProviderBuilder;
import ca.uvic.leadlab.cdxconnector.messages.MessageBuilderException;
import ca.uvic.leadlab.cdxconnector.messages.SubmitDocumentBuilder;
import org.hl7.v3.*;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.*;
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
    private String locationId;

    // Certificate
    private String certPath;
    private char[] certPass;

    public static void main(String[] args) {
        try {
            WSClient client = new WSClient("https://servicestest.bccdx.ca",
                    "cdxpostprod-otca", "VMK31", "cdxpostprod-otca",
                    "./certs/LEADlab_Keystore.jks", "LEADlab");

            client.listClinics();
        } catch (ConnectorException e) {
            e.printStackTrace();
        }
    }

    public WSClient(String baseUrl, String username, String password, String locationId, String certPath, String certPass) throws ConnectorException {
        this.baseUrl = baseUrl;
        this.username = username;
        this.password = password;
        this.locationId = locationId;
        this.certPath = certPath;
        this.certPass = certPass.toCharArray();

        setupSSLContext();
    }

    public String submitDocument(String document) throws ConnectorException {
        try {
            RCMRIN000002UV01 request = new SubmitDocumentBuilder(UUID.randomUUID().toString()) // Unique Message ID (GUID)
                    .receiver("CDX") // ID Of receiver
                    .sender(locationId) // ID Of requestor
                    .document(UUID.randomUUID().toString(), document)
                    .build();

            System.out.println("\nSubmit Document Request:\n");
            System.out.println(parseObject(request));

            BizTalkServiceInstance documentService = new BizTalkServiceInstance(new URL(baseUrl + "/CDASubmitService/CDASubmit.svc?WSDL"));
            documentService.setHandlerResolver(handlerResolver(documentService.getServiceName()));
            MCCIIN000002UV01 response = documentService.getCustomBindingITwoWayAsync().submitCDA(request);

            System.out.println("\nSubmit Document Response:\n");
            System.out.println(parseObject(response));

            return parseObject(response);
        } catch (MessageBuilderException | MalformedURLException e) {
            LOGGER.log(Level.SEVERE, "Error submitting document", e);
            throw new ConnectorException("Error submitting document", e);
        }
    }

    public String listClinics() throws ConnectorException {
        try {
            PRPMIN406010UV01 request = new ListClinicBuilder(UUID.randomUUID().toString()) // Unique Message ID (GUID)
                    .sender(locationId) // ID Of requestor
                    .queryById("2.16.840.1.113883.3.277.100.2", locationId)
                    .build();

            System.out.println("\nList Clinics Request:\n");
            System.out.println(parseObject(request));

            ClinicQuery clinicQuery = new ClinicQuery(new URL(baseUrl + "/RegistrySearch/ClinicQuery.svc?WSDL"));
            clinicQuery.setHandlerResolver(handlerResolver(clinicQuery.getServiceName()));
            PRPMIN406110UV01 response = clinicQuery.getCustomBindingPRPMAR400013UV().prpmIN406010UV01(request);

            System.out.println("\nList Clinics Response:\n");
            System.out.println(parseObject(response));

            return parseObject(response);
        } catch (MalformedURLException e) {
            LOGGER.log(Level.SEVERE, "Error listing clinics", e);
            throw new ConnectorException("Error listing clinics", e);
        }
    }

    public String listProviders() throws ConnectorException {
        try {
            PRPMIN306010UV request = new ListProviderBuilder(UUID.randomUUID().toString())
                    .sender(locationId)
                    .queryBysdlcId("2.16.840.1.113883.3.277.100.2", locationId)
                    .build();

            System.out.println("\nList Provider Request:\n");
            System.out.println(parseObject(request));

            ProviderQuery providerQuery = new ProviderQuery(new URL(baseUrl + "/RegistrySearch/ProviderQuery.svc?WSDL"));
            providerQuery.setHandlerResolver(handlerResolver(providerQuery.getServiceName()));
            PRPMIN306011UV response = providerQuery.getCustomBindingPRPMAR300013UV().prpmIN306010UV(request);

            System.out.println("\nList Provider Response:\n");
            System.out.println(parseObject(response));

            return parseObject(response);
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

    private String parseObject(Object obj) throws ConnectorException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);

            return writer.toString();
        } catch (JAXBException e) {
            throw new ConnectorException("Error parsing object", e);
        }
    }

    private void validateObject(Object obj, URL schemaUrl) throws ConnectorException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(schemaUrl);

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setSchema(schema);
            marshaller.marshal(obj, new DefaultHandler());
        } catch (JAXBException | SAXException e) {
            throw new ConnectorException("Error validating object", e);
        }
    }
}
