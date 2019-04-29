package ca.uvic.leadlab.cdxconnector;

import ca.uvic.leadlab.cdxconnector.messages.DocumentAttachment;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

public class SubmitDocumentClient {

    private String clinicId;
    private String clinicUsername;
    private String clinicPassword;
    private String clinicCertificate;
    private String clinicCertpassword;

    private Properties properties = setupProperties();

    private String serverUrl = properties.getProperty("obib.cdx.server.url");

    private static Properties setupProperties() {
        Properties properties = new Properties();
        try {
            properties.load(SubmitDocumentClient.class.getResourceAsStream("/SubmitDocumentClient.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static void main(String[] args) {
        try {
            if (args.length < 3) {
                System.out.println("Error calling the SubmitDocumentClient. \n " +
                        "Usage: \n" +
                        " $ java SubmitDocumentClient [location_id] [receivers_ids] [cda_file_path] [attachment_files_paths] \n" +
                        "or: \n" +
                        " $ java -jar cdxconnector-0.0.1-SNAPSHOT-tests.jar [location_id] [receivers_ids] [cda_file_path] [attachment_files_paths] \n" +
                        "or: \n" +
                        " java -cp .:* -jar cdxconnector-0.0.1-SNAPSHOT-tests.jar ca.uvic.leadlab.cdxconnector.SubmitDocumentClient [location_id] [receivers_ids] [cda_file_path] [attachment_files_paths] \n" +
                        "where: \n" +
                        "    [location_id] is the clinic 'location id' registered in the SubmitDocumentClient.properties file \n" +
                        "    [receivers_ids] are the receiver clinics 'location id' separated by comma ',' \n" +
                        "    [cda_file_path] is the relative path for the cda file to be submitted \n" +
                        "    [attachment_files_paths] (optional) are the document's attachment file separated by comma ',' \n");
                System.exit(-1);
            }

            SubmitDocumentClient client = new SubmitDocumentClient();

            // Configure clinic credentials
            client.configureClinic(args[0]);

            // Configure receivers ids
            String[] receiversIds = args[1].split(",");

            // Load CDA from file
            String cda = client.loadCDAFile(args[2]);

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
        } catch (Exception e) {
            e.printStackTrace();
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
        URL certURL = getClass().getClassLoader().getResource(clinicCertificate);
        if (certURL == null) {
            throw new IOException("Clinic '" + clinicId + "' certificate file '" + clinicCertificate +"' not found.");
        }
        this.clinicCertificate =  certURL.getFile();
    }

    private String loadCDAFile(final String filePath) throws URISyntaxException, IOException {
        URL fileURL = getClass().getClassLoader().getResource(filePath);
        if (fileURL == null) {
            throw new IOException("CDA file '" + filePath + "' not found.");
        }
        Path path = Paths.get(fileURL.toURI());
        byte[] fileBytes = Files.readAllBytes(path);
        return new String(fileBytes);
    }

    private void submitDocument(final String cda, final List<DocumentAttachment> attachments,
                                final String... receiversIds) throws ConnectorException {
        WSClientDocument document = new WSClientDocument(serverUrl, clinicUsername, clinicPassword,
                clinicCertificate, clinicCertpassword);

        String response = document.submitDocument(clinicId, cda, attachments, receiversIds);

        System.out.println(TestUtils.prettyXML(response));
    }

}
