package ca.uvic.leadlab.cdxconnector;

import ca.infoway.messagebuilder.model.ClinicalDocumentBean;
import ca.interiorhealth.BizTalkServiceInstance;

import ca.leadlab.obib.doctr.DocumentTransformer;
import ca.leadlab.obib.doctr.ReferralRequestCreator;
import ca.uvic.leadlab.cdxconnector.messages.ListProviderBuilder;
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
import java.net.URL;
import java.security.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WSClient {

    private final Logger LOGGER = Logger.getLogger(WSClient.class.getName());

    // Base URLs
    //private String baseUrl = "https://servicesdev.bccdx.ca/";
    //private String baseUrl = "https://servicestest.bccdx.ca/";
    //private String baseUrl = "https://services.bccdx.ca/";

    // Clinic credentials
    private String username = "cdxpostprod-otca";
    private String password = "VMK31";
    private String locationId = username;
    private String clinicName = "Oscar Test Clinic A";

    private String certPath = "/home/ocosta/Projects/UVic/LEADLab/source/OBIB/cdxconnector/certs/LEADlab_Keystore.jks";
    private String certPass = "LEADlab";

    public static void main(String[] args) {
        WSClient client = new WSClient();

        ClinicalDocumentBean referralRequest = new ReferralRequestCreator()
                .loincCode("34117-2", "History &amp;Physical Note"  )
                .patientName("Weber", "Jens")
                .patientGender("MALE")
                .patientDOB(2019,2,1)
                .patientId("34234324")
                .patientAddress("5470 Old West Saanich Rd.","Victoria","BC","V9E2A7","CA")
                .patientTelecom("(250)721-8797")
                .effectiveTime(2019,2,1, 12, 30, TimeZone.getTimeZone("GMT-8"))
                .docId(UUID.randomUUID().toString())
                .authorId("2343242")
                .authorAddress("3800 Finnerty Rd", "Victoria", "BC","V8P 5C3", "CA")
                .authorTelecom("(250) 472-8743")
                .authorTime(2019,2,1)
                .authorPersonName("Price","Morgan")
                .custodianOrgazinationId("3234242")
                .custodianOrgazinationName("Ocean Spree Medical")
                .body("Please cure this person!")
                .get();

        DocumentTransformer documentTransformer = new DocumentTransformer();
        String document = documentTransformer.parseDocument(referralRequest);

        // TODO temporary solution to remove non-necessary tags from the XML
        document = document.replaceAll("<realmCode\\s*code=\"((?!CA-BC).)*?\"/>|<templateId((?!assigningAuthorityName).)*?/>|<(inFulfillment|componentOf).*?/>", "");

        client.submitDocument(document);
    }

    private void submitDocument(String document) {
        try {
            RCMRIN000002UV01 request = new SubmitDocumentBuilder(UUID.randomUUID().toString()) // Unique Message ID (GUID)
                    .receiver("CDX") // ID Of receiver
                    .sender(locationId) // ID Of requestor
                    .document(UUID.randomUUID().toString(), document)
                    .build();

            System.out.println("\nSubmit Document Request:\n");
            System.out.println(parseObject(request));

            setupSSLContext(certPath, certPass.toCharArray());

            BizTalkServiceInstance documentService = new BizTalkServiceInstance(new URL("https://servicestest.bccdx.ca/CDASubmitService/CDASubmit.svc?WSDL"));
            documentService.setHandlerResolver(handlerResolver(documentService.getServiceName()));
            MCCIIN000002UV01 response = documentService.getCustomBindingITwoWayAsync().submitCDA(request);

            System.out.println("\nSubmit Document Response:\n");
            System.out.println(parseObject(response));

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error on submitDocument", e);
        }
    }

    private void listClinics() {
        try {
            PRPMIN406010UV01 request = new ListProviderBuilder(UUID.randomUUID().toString()) // Unique Message ID (GUID)
                    .sender(locationId) // ID Of requestor
                    .queryById("2.16.840.1.113883.3.277.100.2", locationId)
                    .build();

            System.out.println("\nList Clinics Request:\n");
            System.out.println(parseObject(request));

            setupSSLContext(certPath, certPass.toCharArray());

            ClinicQuery clinicQuery = new ClinicQuery(new URL("https://servicestest.bccdx.ca/RegistrySearch/ClinicQuery.svc?WSDL"));
            clinicQuery.setHandlerResolver(handlerResolver(clinicQuery.getServiceName()));
            PRPMIN406110UV01 response = clinicQuery.getCustomBindingPRPMAR400013UV().prpmIN406010UV01(request);

            System.out.println("\nList Clinics Response:\n");
            System.out.println(parseObject(response));

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

    private String parseObject(Object obj) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);

            return writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    String validateObject(Object obj, URL schemaUrl) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(schemaUrl);

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setSchema(schema);
            marshaller.marshal(obj, new DefaultHandler());
        } catch (JAXBException | SAXException e) {
            LOGGER.log(Level.SEVERE, "Error validating object.", e);
            return e.getMessage();
        }
        return null;
    }
}
