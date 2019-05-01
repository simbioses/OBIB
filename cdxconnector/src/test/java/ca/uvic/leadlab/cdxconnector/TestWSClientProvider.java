package ca.uvic.leadlab.cdxconnector;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestWSClientProvider extends TestWSClient {

    static WSClientProvider wsClientProviderA;
    static WSClientProvider wsClientProviderC;

    @BeforeClass
    public static void setUp() throws Exception{
        wsClientProviderA = new WSClientProvider(cdxServerUrl, ClinicA.username, ClinicA.password,
                ClassLoader.getSystemClassLoader().getResource(ClinicA.certificate).getFile(), ClinicA.certPassword);

        wsClientProviderC = new WSClientProvider(cdxServerUrl, ClinicC.username, ClinicC.password,
                ClassLoader.getSystemClassLoader().getResource(ClinicC.certificate).getFile(), ClinicC.certPassword);
    }

    @Test
    public void testListProvidersById() throws Exception {
        String response = wsClientProviderA.listProviders(ClinicA.id, "", "93188", "");

        Assert.assertNotNull(response);
        System.out.println(TestUtils.prettyXML(response));
    }

    @Test
    public void testListProvidersByName() throws Exception {
        String response = wsClientProviderA.listProviders(ClinicA.id, "", "", "Plisihb");

        Assert.assertNotNull(response);
        System.out.println(TestUtils.prettyXML(response));
    }

    @Test
    public void testListProvidersByClinicIdAndProviderName() throws Exception {
        String response = wsClientProviderA.listProviders(ClinicA.id, ClinicA.id, "", "Pli");

        Assert.assertNotNull(response);
        System.out.println(TestUtils.prettyXML(response));
    }
}
