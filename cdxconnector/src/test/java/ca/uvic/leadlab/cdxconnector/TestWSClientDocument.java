package ca.uvic.leadlab.cdxconnector;

import ca.uvic.leadlab.cdxconnector.messages.submit.DocumentAttachment;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.bind.DatatypeConverter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestWSClientDocument extends TestWSClient {

    static WSClientDocument wsClientDocumentA;
    static WSClientDocument wsClientDocumentC;

    private static String replaceDocumentId(String document) {
        UUID documentId = UUID.randomUUID();
        System.out.println("DocumentId = " + documentId);
        return document.replaceFirst("<id assigningAuthorityName=\"CDX Clinical Document ID\" extension=\".*\" root=\"2.16.840.1.113883.3.277.100.3\"/>",
                "<id assigningAuthorityName=\"CDX Clinical Document ID\" extension=\"" + documentId + "\" root=\"2.16.840.1.113883.3.277.100.3\"/>");
    }

    @BeforeClass
    public static void setUp() throws Exception {
        wsClientDocumentA = new WSClientDocument(cdxServerUrl, ClinicA.username, ClinicA.password,
            ClassLoader.getSystemClassLoader().getResource(ClinicA.certificate).getFile(), ClinicA.certPassword);

        wsClientDocumentC = new WSClientDocument(cdxServerUrl, ClinicC.username, ClinicC.password,
                ClassLoader.getSystemClassLoader().getResource(ClinicC.certificate).getFile(), ClinicC.certPassword);
    }

    @Test
    public void testSubmitDocument() throws Exception {
        String document = TestUtils.loadTextFile("/documentTest.xml");
        document = replaceDocumentId(document);

        String response = wsClientDocumentA.submitDocument(ClinicA.id, document, null, ClinicA.id);

        Assert.assertNotNull(response);
        System.out.println(TestUtils.prettyXML(response));
    }

    @Test
    public void testSubmitDocumentWithAttachments() throws Exception {
        String document = TestUtils.loadTextFile("/documentTest.xml");
        document = replaceDocumentId(document);

        List<DocumentAttachment> attachments = new ArrayList<>();
        byte[] attach = TestUtils.loadBinaryFile("/attachmentTest.pdf");
        byte[] hash = TestUtils.calculateHash(attach);

        attachments.add(new DocumentAttachment("application/pdf",
                DatatypeConverter.printBase64Binary(attach),
                DatatypeConverter.printHexBinary(hash),
                "SHA-1", "document.pdf"));

        String response = wsClientDocumentA.submitDocument(ClinicA.id, document, attachments, ClinicA.id);

        Assert.assertNotNull(response);
        System.out.println(TestUtils.prettyXML(response));
    }

    @Test
    public void testListNewDocuments() throws Exception {
        String response = wsClientDocumentA.listNewDocuments(ClinicA.id);

        Assert.assertNotNull(response);
        System.out.println(TestUtils.prettyXML(response));
    }

    @Test
    public void testGetDocument() throws Exception {
        String response = wsClientDocumentA.getDocument(ClinicA.id,"b468475a-adb4-e911-a96d-0050568c55a6");

        // "a163901e-6173-e911-a96a-0050568c55a6" - Documents returning message: Could not retrieve messages: Requested value 'SHA-1' was not found.
        // "f01a004a-5673-e911-a96a-0050568c55a6" - Documents returning message: Could not retrieve messages: Requested value 'SHA-1' was not found.

        Assert.assertNotNull(response);
        System.out.println(TestUtils.prettyXML(response));
    }

    @Test
    public void testSearchDocumentsByClinic() throws Exception {
        String response = wsClientDocumentA
                .searchDocuments(ClinicA.id, ClinicA.id, null, null, null);

        Assert.assertNotNull(response);
        System.out.println(TestUtils.prettyXML(response));
    }

    @Test
    public void testSearchDocumentsByDocumentId() throws Exception {
        String response = wsClientDocumentA
                .searchDocuments(ClinicA.id, null,
                        "1e480cd0-27d0-4029-99bb-d2e479d53ab8",
                        null, null);

        Assert.assertNotNull(response);
        System.out.println(TestUtils.prettyXML(response));
    }

    @Test
    public void testGetDistributionStatusByDocumentId() throws Exception {
        String response = wsClientDocumentA
                .getDistributionStatus(ClinicA.id, null,
                        "b284441b-cdfa-4642-b202-e8c5941df34d",
                        null, null);

        Assert.assertNotNull(response);
        System.out.println(TestUtils.prettyXML(response));
    }
}
