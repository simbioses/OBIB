package ca.uvic.leadlab.obibconnector.utils;

import org.junit.Assert;
import org.junit.Test;

public class UtilsTests {

    //@Test
    public void testCheckAttachment() throws Exception {
        Assert.assertTrue(AttachmentUtils.checkAttachment("", ""));
    }

    //Test
    public void testOBIBConnectorVersion() throws Exception {
        Assert.assertNotNull(OBIBConnectorHelper.getOBIBConnectorVersion());
    }
}
