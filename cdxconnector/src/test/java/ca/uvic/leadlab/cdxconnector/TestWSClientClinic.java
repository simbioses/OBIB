package ca.uvic.leadlab.cdxconnector;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestWSClientClinic extends TestWSClient {

    static WSClientClinic wsClientClinicA;
    static WSClientClinic wsClientClinicC;

    @BeforeClass
    public static void setUp() throws Exception {
        wsClientClinicA = new WSClientClinic(cdxServerUrl, ClinicA.username, ClinicA.password,
                ClassLoader.getSystemClassLoader().getResource(ClinicA.certificate).getFile(), ClinicA.certPassword);

        wsClientClinicC = new WSClientClinic(cdxServerUrl, ClinicC.username, ClinicC.password,
                ClassLoader.getSystemClassLoader().getResource(ClinicC.certificate).getFile(), ClinicC.certPassword);
    }

    @Test
    public void testListClinicsById() throws Exception {
        String response = wsClientClinicA.listClinics(ClinicA.id, ClinicA.id, "", "");

        Assert.assertNotNull(response);
        System.out.println(TestUtils.prettyXML(response));
    }

    @Test
    public void testListClinicsByName() throws Exception {
        String response = wsClientClinicA.listClinics(ClinicA.id, "", "Oscar", "");

        Assert.assertNotNull(response);
        System.out.println(TestUtils.prettyXML(response));
    }

    @Test
    public void testListClinicsByAddress() throws Exception {
        String response = wsClientClinicA.listClinics(ClinicA.id, "", "", "Test Ave");

        Assert.assertNotNull(response);
        System.out.println(TestUtils.prettyXML(response));
    }
}
