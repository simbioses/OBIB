package ca.uvic.leadlab.cdxconnector;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestWSClientDocument extends TestWSClient {

    static WSClientDocument wsClientDocumentA;
    static WSClientDocument wsClientDocumentC;

    @BeforeClass
    public static void setUp() throws Exception {
        wsClientDocumentA = new WSClientDocument(cdxServerUrl, ClinicA.username, ClinicA.password,
            ClassLoader.getSystemClassLoader().getResource(ClinicA.certificate).getFile(), ClinicA.certPassword);

        wsClientDocumentC = new WSClientDocument(cdxServerUrl, ClinicC.username, ClinicC.password,
                ClassLoader.getSystemClassLoader().getResource(ClinicC.certificate).getFile(), ClinicC.certPassword);
    }

    @Test
    public void testSubmitDocument() throws Exception {
        String response = wsClientDocumentA.submitDocument(ClinicA.id, "", null, ClinicC.id);

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
        String response = wsClientDocumentA.getDocument(ClinicA.id,
                "ad0007b5-c846-e911-a96a-0050568c55a6"); // Using 'CDX Message ID' = Found
                //"45a75b7e-5cb1-4d00-ab7f-b7872de47549"); // Using 'CDX Clinical Document ID' = Not found (?!)

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
                .searchDocuments(ClinicA.id, ClinicA.id,
                        //"ad0007b5-c846-e911-a96a-0050568c55a6", // Using 'CDX Message ID' = Not Found (?!)
                        "45a75b7e-5cb1-4d00-ab7f-b7872de47549", // Using 'CDX Clinical Document ID' = Found
                        null, null);

        Assert.assertNotNull(response);
        System.out.println(TestUtils.prettyXML(response));
    }
}
