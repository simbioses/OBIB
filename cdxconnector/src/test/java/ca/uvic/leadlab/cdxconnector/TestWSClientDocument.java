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
                        "8873e8f1-f4a3-4f93-969f-29ff7853bbe2",
                        null, null);

        Assert.assertNotNull(response);
        System.out.println(TestUtils.prettyXML(response));
    }

    @Test
    public void testGetDistributionStatusByDocumentId() throws Exception {
        String response = wsClientDocumentA
                .getDistributionStatus(ClinicA.id, null,
                        "b468475a-adb4-e911-a96d-0050568c55a6",
                        null, null);

        Assert.assertNotNull(response);
        System.out.println(TestUtils.prettyXML(response));
    }
}
