package ca.uvic.leadlab.obibconnector.utils;

import org.junit.Assert;
import org.junit.Test;

public class AttachmentUtilsTest {

    //@Test
    public void testCheckAttachment() throws Exception {
        Assert.assertTrue(AttachmentUtils.checkAttachment("", ""));
    }
}
