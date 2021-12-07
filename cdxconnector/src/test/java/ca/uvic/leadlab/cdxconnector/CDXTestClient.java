package ca.uvic.leadlab.cdxconnector;

import ca.uvic.leadlab.cdxconnector.messages.submit.DocumentAttachment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class CDXTestClient {

    private String clinicId;
    private String clinicUsername;
    private String clinicPassword;
    private String clinicCertificate;
    private String clinicCertpassword;

    private final Properties properties = setupProperties();

    private final String serverUrl = properties.getProperty("obib.cdx.server.url");

    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static Properties setupProperties() {
        Properties properties = new Properties();
        // Try to load an external properties file located in the same dir of the jar file
        try (InputStream inputStream = new FileInputStream("./CDXTestClient.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            // Fallback to load the file from the classpath
            try (InputStream inputStream = CDXTestClient.class.getResourceAsStream("/CDXTestClient.properties")) {
                properties.load(inputStream);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return properties;
    }

    private static void help() {
        System.out.println("\n" +
                "Usage example: \n" +
                " java -jar cdxconnector-0.0.1-SNAPSHOT-tests.jar location_id operation [ operation_args ] \n" +
                "where: \n" +
                "  location_id is the clinic 'location id' registered in the CDXTestClient.properties file \n" +
                "  operation is the operation type \n" +
                "  operation_args are optional arguments for an operation. \n\n" +
                "The operations and their respective operation_args are defined as follow: \n" +
                "  submit - submit a document from the current location \n" +
                "    receivers_ids - a list of receiver clinics 'location id' separated by comma ',' \n" +
                "    cda_file_path - the relative path for the cda file to be submitted \n" +
                "    attachment_files_paths - optional list of document's attachment file separated by comma ',' \n" +
                "  status - request the delivery status of a document \n" +
                "    document_id - the id of a submitted document \n" +
                "  list - list the new documents of the current location \n" +
                "  search - search for documents by date \n" +
                "    init_date - initial date of the period in the format yyyy-mm-dd \n" +
                "    end_date - end date of the period in the format yyyy-mm-dd \n" +
                "  get - retrieve a document by its id \n" +
                "    document_id - the id of a document addressed to the current location \n" +
                "  providers - search providers by name \n" +
                "    name - the portion of the providers name \n" +
                "  clinics - search clinics by name \n" +
                "    name - the portion of the clinics name \n");
    }

    private static void checkArgs(String[] args, int number) {
        if (args.length < number) {
            System.out.println("Error calling the CDXTestClient: wrong number of arguments");
            help();
            System.exit(-1);
        }
    }

    private static String loadFile(final String filePath) throws IOException {
        URI fileURI = new File(filePath).toURI();
        Path path = Paths.get(fileURI);
        byte[] fileBytes = Files.readAllBytes(path);
        return new String(fileBytes);
    }

    public static void main(String[] args) {
        try {
            checkArgs(args, 2);

            CDXTestClient client = new CDXTestClient();

            // Configure clinic credentials
            client.configureClinic(args[0]);

            String operation = args[1];
            switch (operation) {
                case "submit":
                    checkArgs(args, 4);

                    // Configure receivers ids
                    String[] receiversIds = args[2].split(",");
                    // Load CDA from file
                    String cda = loadFile(args[3]);
                    // Load attachments from files if there is any
                    List<DocumentAttachment> attachments = null;
                    /*if (args.length == 4) { // TODO
                        attachments = new ArrayList<>();
                        String[] attachmentsPaths = args[3].split(",");
                        for (String path : attachmentsPaths) {
                            attachments.add(new DocumentAttachment(type, content, hash, reference));
                        }
                    }*/
                    client.submitDocument(cda, attachments, receiversIds);
                    break;
                case "status":
                    checkArgs(args, 3);

                    client.documentStatus(args[2]);
                    break;
                case "list":
                    client.listDocuments();
                    break;
                case "search":
                    checkArgs(args, 4);

                    client.searchDocuments(dateFormat.parse(args[2]), dateFormat.parse(args[3]));
                    break;
                case "get":
                    checkArgs(args, 3);

                    client.getDocument(args[2]);
                    break;
                case "providers":
                    checkArgs(args, 3);

                    client.getProviders(args[2]);
                    break;
                case "clinics":
                    checkArgs(args, 3);

                    client.getClinics(args[2]);
                    break;
                default:
                    System.out.println("Error calling the CDXTestClient: operation is not valid.");
                    help();
                    System.exit(-1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private void configureClinic(final String clinicId) throws Exception {
        this.clinicId = clinicId;

        // get the credentials from properties
        this.clinicUsername = properties.getProperty("obib.clinic." + clinicId + ".username");
        this.clinicPassword = properties.getProperty("obib.clinic." + clinicId + ".password");
        this.clinicCertificate = properties.getProperty("obib.clinic." + clinicId + ".certificate");
        this.clinicCertpassword = properties.getProperty("obib.clinic." + clinicId + ".certpassword");
        if (clinicUsername == null || clinicPassword == null || clinicCertificate == null || clinicCertpassword == null) {
            throw new Exception("Clinic '" + clinicId + "' credentials are not configured correctly. \n" +
                    "clinicUsername: " + clinicUsername + "\n" +
                    "clinicPassword: " + clinicPassword + "\n" +
                    "clinicCertificate: " + clinicCertificate + "\n" +
                    "clinicCertpassword: " + clinicCertpassword);
        }

        // get the path of the certificate file
        URL certURL = new File(clinicCertificate).toURI().toURL();
//        if (certURL == null) {
//            throw new IOException("Clinic '" + clinicId + "' certificate file '" + clinicCertificate + "' not found.");
//        }
        this.clinicCertificate = certURL.getFile();
    }

    private WSClientDocument clientDocument() throws ConnectorException {
        return new WSClientDocument(serverUrl, clinicUsername, clinicPassword,
                clinicCertificate, clinicCertpassword);
    }

    private void submitDocument(final String cda, final List<DocumentAttachment> attachments,
                                final String... receiversIds) throws ConnectorException {
        String response = clientDocument().submitDocument(clinicId, cda, attachments, receiversIds);
        System.out.println(TestUtils.prettyXML(response));
    }

    private void documentStatus(final String documentId) throws ConnectorException {
        String response = clientDocument().getDistributionStatus(clinicId, null, documentId, null, null);
        System.out.println(TestUtils.prettyXML(response));
    }

    private void listDocuments() throws ConnectorException {
        String response = clientDocument().listNewDocuments(clinicId);
        System.out.println(TestUtils.prettyXML(response));
    }

    private void searchDocuments(Date initDate, Date endDate) throws ConnectorException {
        DateRange dateRange = new DateRange(initDate, true, endDate, true);
        String response = clientDocument().searchDocuments(clinicId, clinicId, null, dateRange, null);
        System.out.println(TestUtils.prettyXML(response));
    }

    private void getDocument(final String documentId) throws ConnectorException {
        String response = clientDocument().getDocument(clinicId, documentId);
        System.out.println(TestUtils.prettyXML(response));
    }

    private void getProviders(final String name) throws ConnectorException {
        WSClientProvider client = new WSClientProvider(serverUrl, clinicUsername, clinicPassword,
                clinicCertificate, clinicCertpassword);
        String response = client.listProviders(clinicId, null, null, name);
        System.out.println(TestUtils.prettyXML(response));
    }

    private void getClinics(final String name) throws ConnectorException {
        WSClientClinic client = new WSClientClinic(serverUrl, clinicUsername, clinicPassword,
                clinicCertificate, clinicCertpassword);
        String response = client.listClinics(clinicId, null, name, null);
        System.out.println(TestUtils.prettyXML(response));
    }
}
