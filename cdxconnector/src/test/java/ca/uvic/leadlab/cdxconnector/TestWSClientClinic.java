package ca.uvic.leadlab.cdxconnector;

import org.junit.Assert;
import org.junit.Test;

public class TestWSClientClinic extends TestWSClient {

    @Test
    public void testListClinicsById() throws Exception {
        WSClientClinic client = new WSClientClinic(cdxServerUrl, oscarClinic1Username, oscarClinic1Password,
                getClass().getClassLoader().getResource(oscarClinic1Certificate).getFile(), oscarClinic1certPassword);

        String response = client.listClinics("cdxpostprod-otca", "cdxpostprod-otca", "", "");

        Assert.assertNotNull(response);
        System.out.println(TestUtils.prettyXML(response));
    }

    @Test
    public void testListClinicsByName() throws Exception {
        WSClientClinic client = new WSClientClinic(cdxServerUrl, oscarClinic1Username, oscarClinic1Password,
                getClass().getClassLoader().getResource(oscarClinic1Certificate).getFile(), oscarClinic1certPassword);

        String response = client.listClinics("cdxpostprod-otca", "", "Oscar", "");

        Assert.assertNotNull(response);
        System.out.println(TestUtils.prettyXML(response));
    }

    @Test
    public void testListClinicsByAddress() throws Exception {
        WSClientClinic client = new WSClientClinic(cdxServerUrl, oscarClinic1Username, oscarClinic1Password,
                getClass().getClassLoader().getResource(oscarClinic1Certificate).getFile(), oscarClinic1certPassword);

        String response = client.listClinics("cdxpostprod-otca", "", "", "Test Ave");

        Assert.assertNotNull(response);
        System.out.println(TestUtils.prettyXML(response));
    }
}
