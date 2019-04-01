package ca.uvic.leadlab.cdxconnector;

import org.junit.Assert;
import org.junit.Test;

public class TestWSClientDocument {

    //@Test
    public void testListNewDocuments() throws Exception {

        WSClientDocument document = new WSClientDocument("https://servicestest.bccdx.ca",
                "cdxpostprod-otca", "VMK31",
                getClass().getClassLoader().getResource("LEADlab_Keystore.jks").getFile(), "LEADlab");

        String response = document.listNewDocuments("cdxpostprod-otca");

        Assert.assertNotNull(response);
        System.out.println(response);
    }

    //@Test
    public void testGetDocument() throws Exception {

        WSClientDocument document = new WSClientDocument("https://servicestest.bccdx.ca",
                "cdxpostprod-otca", "VMK31",
                getClass().getClassLoader().getResource("LEADlab_Keystore.jks").getFile(), "LEADlab");

        String response = document.getDocument("cdxpostprod-otca", "1b920ac7-e224-e911-a96a-0050568c55a6");

        Assert.assertNotNull(response);
        System.out.println(response);
    }
}
