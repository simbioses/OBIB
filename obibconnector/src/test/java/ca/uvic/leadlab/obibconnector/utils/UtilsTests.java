package ca.uvic.leadlab.obibconnector.utils;

import ca.uvic.leadlab.obibconnector.facades.send.SubmitDocTest;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UtilsTests {

    @Test
    public void testCheckAttachment() throws Exception {
        byte[] content = loadFile("/CDXDocument-eReferral_att01.pdf");
        Assert.assertTrue(AttachmentUtils.checkAttachment(content, "e0352d30de2c021debda66498d221c1c5403c1a3"));
    }

    @Test
    public void testCalculateAttachmentHash() throws Exception {
        String hash = AttachmentUtils.calculateHash(loadFile("/CDXDocument-eReferral_att01.pdf"));
        Assert.assertEquals("e0352d30de2c021debda66498d221c1c5403c1a3", hash);
    }

    //@Test
    public void testOBIBConnectorVersion() throws Exception {
        Assert.assertNotNull(OBIBConnectorHelper.getOBIBConnectorVersion());
    }

    //@Test
    public void testReadOBIBConnectorProperties() throws Exception {
        Assert.assertNotNull(OBIBConnectorHelper.getProperty("obib.keystore.path"));
    }

    private static byte[] loadFile(String filePath) throws Exception {
        Path path = Paths.get(SubmitDocTest.class.getResource(filePath).toURI());
        return Files.readAllBytes(path);
    }
}
