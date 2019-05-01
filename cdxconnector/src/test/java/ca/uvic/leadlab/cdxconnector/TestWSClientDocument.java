package ca.uvic.leadlab.cdxconnector;

import ca.uvic.leadlab.cdxconnector.messages.DocumentQueryParameterBuilder;
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
        String response = wsClientDocumentA.getDocument(ClinicA.id, "8a96c1d7-2823-e911-a96a-0050568c55a6");

        Assert.assertNotNull(response);
        System.out.println(TestUtils.prettyXML(response));
    }

    @Test
    public void testSearchDocuments() throws Exception {
        String response = wsClientDocumentC.searchDocuments(ClinicA.id, new DocumentQueryParameterBuilder()
                //.clinicId(ClinicA.id));
        //.documentId("801068d3-0c67-45a4-ba99-2f767e559733"));
        //.documentId("9965cc95-29df-4a4e-be26-93269d7a46c4"));
        );
        Assert.assertNotNull(response);
        System.out.println(TestUtils.prettyXML(response));
    }
}
