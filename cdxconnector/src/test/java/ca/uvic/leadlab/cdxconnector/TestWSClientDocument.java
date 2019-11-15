package ca.uvic.leadlab.cdxconnector;

import ca.uvic.leadlab.cdxconnector.messages.submit.DocumentAttachment;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.bind.DatatypeConverter;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TestWSClientDocument extends TestWSClient {

    static WSClientDocument wsClientDocumentA;
    static WSClientDocument wsClientDocumentC;

    private static String updateDocument(String document) throws Exception {
        UUID documentId = UUID.randomUUID();
        String currentTime = new SimpleDateFormat("yyyyMMddHHmmZZZ").format(new Date());

        System.out.println("DocumentId = " + documentId);
        System.out.println("Time       = " + currentTime);

        Document doc = TestUtils.parseXml(document);
        XPath xpath = XPathFactory.newInstance().newXPath();
        // modify id and setId
        NodeList nodes = (NodeList) xpath.compile("(//id|//setId)[@root='2.16.840.1.113883.3.277.100.3']")
                .evaluate(doc, XPathConstants.NODESET);
        for (int i = 0; i < nodes.getLength(); i++) {
            nodes.item(i).getAttributes().getNamedItem("extension").setNodeValue(documentId.toString());
        }

        // modify effetiveTime and time
        nodes = (NodeList) xpath.compile("//effectiveTime|//time")
                .evaluate(doc, XPathConstants.NODESET);
        for (int i = 0; i < nodes.getLength(); i++) {
            nodes.item(i).getAttributes().getNamedItem("value").setNodeValue(currentTime);
        }

        return TestUtils.writeXml(doc);
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
        String document = updateDocument(TestUtils.loadTextFile("/documentTest.xml"));

        String response = wsClientDocumentA.submitDocument(ClinicA.id, document, null, ClinicA.id);

        Assert.assertNotNull(response);
        System.out.println(TestUtils.prettyXML(response));
    }

    @Test
    public void testSubmitDocumentWithAttachments() throws Exception {
        String document = updateDocument(TestUtils.loadTextFile("/documentTest.xml"));

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
                        "bc2d2059-f646-440b-880d-7f204aaf8321",
                        null, null);

        Assert.assertNotNull(response);
        System.out.println(TestUtils.prettyXML(response));
    }
}
