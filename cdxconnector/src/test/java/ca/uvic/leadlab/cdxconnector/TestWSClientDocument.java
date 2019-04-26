package ca.uvic.leadlab.cdxconnector;

import ca.uvic.leadlab.cdxconnector.messages.DocumentQueryParameterBuilder;
import org.junit.Assert;
import org.junit.Test;

public class TestWSClientDocument extends TestWSClient {

    @Test
    public void testSubmitDocument() throws Exception {
        WSClientDocument document = new WSClientDocument(cdxServerUrl, oscarClinic1Username, oscarClinic1Password,
                getClass().getClassLoader().getResource(oscarClinic1Certificate).getFile(), oscarClinic1certPassword);

        String response = document.submitDocument("cdxpostprod-otca", "", null, "cdxpostprod-octca");

        Assert.assertNotNull(response);
        System.out.println(TestUtils.prettyXML(response));
    }

    @Test
    public void testListNewDocuments() throws Exception {
        WSClientDocument document = new WSClientDocument(cdxServerUrl, oscarClinic1Username, oscarClinic1Password,
                getClass().getClassLoader().getResource(oscarClinic1Certificate).getFile(), oscarClinic1certPassword);

        String response = document.listNewDocuments("cdxpostprod-otca");

        Assert.assertNotNull(response);
        System.out.println(TestUtils.prettyXML(response));
    }

    @Test
    public void testGetDocument() throws Exception {
        WSClientDocument document = new WSClientDocument(cdxServerUrl, oscarClinic1Username, oscarClinic1Password,
                getClass().getClassLoader().getResource(oscarClinic1Certificate).getFile(), oscarClinic1certPassword);

        String response = document.getDocument("cdxpostprod-otca", "8a96c1d7-2823-e911-a96a-0050568c55a6");

        Assert.assertNotNull(response);
        System.out.println(TestUtils.prettyXML(response));
    }

    @Test
    public void testSearchDocuments() throws Exception {
        WSClientDocument document = new WSClientDocument(cdxServerUrl, oscarClinic1Username, oscarClinic1Password,
                getClass().getClassLoader().getResource(oscarClinic1Certificate).getFile(), oscarClinic1certPassword);

        String response = document.searchDocuments("cdxpostprod-otca", new DocumentQueryParameterBuilder()
                .clinicId("cdxpostprod-otca"));

        Assert.assertNotNull(response);
        System.out.println(TestUtils.prettyXML(response));
    }
}
