package ca.uvic.leadlab.cdxconnector;

import org.junit.Assert;
import org.junit.Test;

public class TestWSClientProvider extends TestWSClient {

    @Test
    public void testListProvidersById() throws Exception {
        WSClientProvider client = new WSClientProvider(cdxServerUrl, oscarClinic1Username, oscarClinic1Password,
                getClass().getClassLoader().getResource(oscarClinic1Certificate).getFile(), oscarClinic1certPassword);

        String response = client.listProviders("cdxpostprod-otca", "", "93188", "");

        Assert.assertNotNull(response);
        System.out.println(TestUtils.prettyXML(response));
    }

    @Test
    public void testListProvidersByName() throws Exception {
        WSClientProvider client = new WSClientProvider(cdxServerUrl, oscarClinic1Username, oscarClinic1Password,
                getClass().getClassLoader().getResource(oscarClinic1Certificate).getFile(), oscarClinic1certPassword);

        String response = client.listProviders("cdxpostprod-otca", "", "", "Plisihb");

        Assert.assertNotNull(response);
        System.out.println(TestUtils.prettyXML(response));
    }
}
