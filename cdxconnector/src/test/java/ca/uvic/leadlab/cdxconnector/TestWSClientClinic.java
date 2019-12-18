package ca.uvic.leadlab.cdxconnector;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestWSClientClinic extends TestWSClient {

    static WSClientClinic wsClientClinicA;
    static WSClientClinic wsClientClinicC;
    static WSClientClinic wsClientClinicT;

    @BeforeClass
    public static void setUp() throws Exception {
        wsClientClinicA = new WSClientClinic(cdxServerUrl, ClinicA.username, ClinicA.password,
                ClassLoader.getSystemClassLoader().getResource(ClinicA.certificate).getFile(), ClinicA.certPassword);

        wsClientClinicC = new WSClientClinic(cdxServerUrl, ClinicC.username, ClinicC.password,
                ClassLoader.getSystemClassLoader().getResource(ClinicC.certificate).getFile(), ClinicC.certPassword);

        wsClientClinicT = new WSClientClinic(cdxServerUrl, ClinicT.username, ClinicT.password,
                ClassLoader.getSystemClassLoader().getResource(ClinicT.certificate).getFile(), ClinicT.certPassword);
    }

    @Test
    public void testListClinicsById() throws Exception {
        String response = wsClientClinicA.listClinics(ClinicA.id, ClinicC.id, "", "");

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

    @Test(expected = ConnectorException.class)
    public void testListClinicsWithWrongServer() throws Exception {
        try {
            WSClientClinic wsClientClinic = new WSClientClinic("https://servicestest.bccd.ca", ClinicA.username, ClinicA.password,
                    ClassLoader.getSystemClassLoader().getResource(ClinicA.certificate).getFile(), ClinicA.certPassword);
            String response = wsClientClinic.listClinics(ClinicA.id, ClinicC.id, "", "");

            Assert.assertNotNull(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw  e;
        }
    }

    @Test(expected = ConnectorException.class)
    public void testListClinicsWithWrongCredentials() throws Exception {
        try {
            WSClientClinic wsClientClinic = new WSClientClinic(cdxServerUrl, ClinicA.username, "wroNG_PASS",
                    ClassLoader.getSystemClassLoader().getResource(ClinicA.certificate).getFile(), ClinicA.certPassword);
            String response = wsClientClinic.listClinics(ClinicA.id, ClinicC.id, "", "");

            Assert.assertNotNull(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw  e;
        }
    }
}
