package ca.uvic.leadlab.cdxconnector;

import org.junit.Assert;
import org.junit.Test;

public class TestWSClientClinic {

    //@Test
    public void testListClinicsById() throws Exception {

        WSClientClinic client = new WSClientClinic("https://servicestest.bccdx.ca",
                "cdxpostprod-otca", "VMK31",
                getClass().getClassLoader().getResource("LEADlab_Keystore.jks").getFile(), "LEADlab");

        String response = client.listClinics("cdxpostprod-otca", "cdxpostprod-otca", "", "");

        Assert.assertNotNull(response);
        System.out.println(response);
    }
}
