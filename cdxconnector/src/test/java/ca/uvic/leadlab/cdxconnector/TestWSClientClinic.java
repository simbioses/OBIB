package ca.uvic.leadlab.cdxconnector;

import org.junit.Assert;
import org.junit.Test;

public class TestWSClientClinic extends TestWSClient {

    //@Test
    public void testListClinicsById() throws Exception {
        WSClientClinic client = new WSClientClinic("https://servicestest.bccdx.ca",
                username, password, getClass().getClassLoader().getResource(certificate).getFile(), certPassword);

        String response = client.listClinics("cdxpostprod-otca", "cdxpostprod-otca", "", "");

        Assert.assertNotNull(response);
        System.out.println(response);
    }

    //@Test
    public void testListClinicsByName() throws Exception {
        WSClientClinic client = new WSClientClinic("https://servicestest.bccdx.ca",
                username, password, getClass().getClassLoader().getResource(certificate).getFile(), certPassword);

        String response = client.listClinics("cdxpostprod-otca", "", "Oscar", "");

        Assert.assertNotNull(response);
        System.out.println(response);
    }

    //@Test
    public void testListClinicsByAddress() throws Exception {
        WSClientClinic client = new WSClientClinic("https://servicestest.bccdx.ca",
                username, password, getClass().getClassLoader().getResource(certificate).getFile(), certPassword);

        String response = client.listClinics("cdxpostprod-otca", "", "", "Test Ave");

        Assert.assertNotNull(response);
        System.out.println(response);
    }
}
