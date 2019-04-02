package ca.uvic.leadlab.cdxconnector;

import org.junit.Assert;
import org.junit.Test;

public class TestWSClientProvider {

    //@Test
    public void testListProvidersByName() throws Exception {

        WSClientProvider client = new WSClientProvider("https://servicestest.bccdx.ca",
                "cdxpostprod-otca", "VMK31",
                getClass().getClassLoader().getResource("LEADlab_Keystore.jks").getFile(), "LEADlab");

        String response = client.listProviders("cdxpostprod-otca", "", "93188", "");

        Assert.assertNotNull(response);
        System.out.println(response);
    }
}
