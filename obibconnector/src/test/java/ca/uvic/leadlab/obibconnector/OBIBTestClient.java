package ca.uvic.leadlab.obibconnector;

import ca.uvic.leadlab.obibconnector.facades.Config;
import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;
import ca.uvic.leadlab.obibconnector.facades.receive.IDocument;
import ca.uvic.leadlab.obibconnector.facades.receive.IReceiveDoc;
import ca.uvic.leadlab.obibconnector.facades.receive.ISearchDoc;
import ca.uvic.leadlab.obibconnector.facades.registry.IClinic;
import ca.uvic.leadlab.obibconnector.facades.registry.IProvider;
import ca.uvic.leadlab.obibconnector.facades.registry.ISearchClinic;
import ca.uvic.leadlab.obibconnector.facades.registry.ISearchProviders;
import ca.uvic.leadlab.obibconnector.facades.send.ISubmitDoc;
import ca.uvic.leadlab.obibconnector.impl.receive.ReceiveDoc;
import ca.uvic.leadlab.obibconnector.impl.receive.SearchDoc;
import ca.uvic.leadlab.obibconnector.impl.registry.SearchClinic;
import ca.uvic.leadlab.obibconnector.impl.registry.SearchProviders;
import ca.uvic.leadlab.obibconnector.impl.send.SubmitDoc;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OBIBTestClient {

    private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private final static ObjectMapper MAPPER = new ObjectMapper();

    private static String loadFile(final String filePath) throws IOException {
        URI fileURI = new File(filePath).toURI();
        Path path = Paths.get(fileURI);
        byte[] fileBytes = Files.readAllBytes(path);
        return new String(fileBytes);
    }

    private static void help() {
        System.out.println("\n" +
                "Usage example: \n" +
                " java -jar obibconnector-0.0.1-SNAPSHOT-tests.jar obib_url operation [ operation_args ] \n" +
                "where: \n" +
                "  obib_url is the OBIB server url \n" +
                "  operation is the operation type \n" +
                "  operation_args are optional arguments for an operation. \n\n" +
                "The operations and their respective operation_args are defined as follow: \n" +
                "  submit - submit a document from the current location \n" +
                "    json_file_path - the relative path for the json file to be submitted \n" +
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
            System.out.println("Error calling the OBIBTestClient: wrong number of arguments");
            help();
            System.exit(-1);
        }
    }

    public static void main(String[] args) {
        try {
            checkArgs(args, 2);

            OBIBTestClient client = new OBIBTestClient();

            // Implement the obibconnector's Config
            final String obibUrl = args[0];
            Config config = new Config() {
                @Override
                public String getUrl() {
                    return obibUrl;
                }
            };

            // Call the operation
            String operation = args[1];
            switch (operation) {
                case "submit":
                    checkArgs(args, 4);
                    // Load CDA from file
                    String jsonDoc = loadFile(args[2]);
                    // Load attachments from files if there is any
                    String attachmentsPaths = loadFile(args[3]);
                    List<File> attachments = null;
                    client.submitDocument(config, jsonDoc, attachments);
                    break;
                case "status":
                    checkArgs(args, 3);
                    client.documentStatus(config, args[2]);
                    break;
                case "list":
                    client.listDocuments(config);
                    break;
                case "search":
                    checkArgs(args, 4);
                    client.searchDocuments(config, DATE_FORMAT.parse(args[2]), DATE_FORMAT.parse(args[3]));
                    break;
                case "get":
                    checkArgs(args, 3);
                    client.getDocument(config, args[2]);
                    break;
                case "providers":
                    checkArgs(args, 3);
                    client.listProviders(config, args[2]);
                    break;
                case "clinics":
                    checkArgs(args, 3);
                    client.listClinics(config, args[2]);
                    break;
                default:
                    System.out.println("Error calling the OBIBTestClient: operation is not valid.");
                    help();
                    System.exit(-1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }

    private void submitDocument(Config config, String jsonDoc, List<File> attachments) throws OBIBException, IOException {
        ISubmitDoc submitDoc = new SubmitDoc(config);

        // TODO get doc from json file
        // TODO add attachments
        throw new UnsupportedOperationException("TODO Implement Submit Documents");

//        IDocument document = submitDoc.submit();
//
//        System.out.println(MAPPER.writeValueAsString(document));
    }

    private void documentStatus(Config config, String documentId) throws OBIBException, IOException {
        ISearchDoc searchDoc = new SearchDoc(config);

        List<IDocument> documents = searchDoc.distributionStatus(documentId);

        for (IDocument document : documents) {
            System.out.println(MAPPER.writeValueAsString(document));
        }
    }

    private void searchDocuments(Config config, Date startDate, Date endDate) throws OBIBException, IOException {
        ISearchDoc searchDoc = new SearchDoc(config);

        List<IDocument> documents = searchDoc.searchDocumentsByPeriod(startDate, endDate);

        for (IDocument document : documents) {
            System.out.println(MAPPER.writeValueAsString(document));
        }
    }

    private void listDocuments(Config config) throws OBIBException, IOException {
        IReceiveDoc receiveDoc = new ReceiveDoc(config);

        List<String> documentIds = receiveDoc.pollNewDocIDs();

        for (String documentId : documentIds) {
            System.out.println(documentId);
        }
    }

    private void getDocument(Config config, String documentId) throws OBIBException, IOException {
        IReceiveDoc receiveDoc = new ReceiveDoc(config);

        IDocument document = receiveDoc.retrieveDocument(documentId);

        System.out.println(MAPPER.writeValueAsString(document));
    }

    private void listClinics(Config config, String name) throws OBIBException, IOException {
        ISearchClinic searchClinic = new SearchClinic(config);

        List<IClinic> clinics = searchClinic.findByName(name);

        System.out.println(MAPPER.writeValueAsString(clinics));
    }

    private void listProviders(Config config, String name) throws OBIBException, IOException {
        ISearchProviders searchProviders = new SearchProviders(config);

        List<IProvider> providers = searchProviders.findByName(name);

        System.out.println(MAPPER.writeValueAsString(providers));
    }
}
