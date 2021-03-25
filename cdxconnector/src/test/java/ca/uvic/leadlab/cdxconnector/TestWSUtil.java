package ca.uvic.leadlab.cdxconnector;

import ca.uvic.leadlab.cdxconnector.messages.submit.DocumentAttachment;
import ca.uvic.leadlab.cdxconnector.messages.submit.SubmitDocumentBuilder;
import cdasubmit.RCMRIN000002UV01;
import org.junit.Test;

import java.util.UUID;

public class TestWSUtil {

    @Test(expected = ConnectorException.class)
    public void testValidateObjectSize() throws Exception {
        byte[] attach = TestUtils.loadBinaryFile("/test_file_50MB.data");

        SubmitDocumentBuilder documentBuilder = new SubmitDocumentBuilder(UUID.randomUUID().toString())
                .sender("Sender File Test")
                .receiver("Receiver File Test")
                .document(UUID.randomUUID().toString(), "Document File Test");
        // String mediaType, String content, String hash, String hashAlgorithm, String reference
        documentBuilder.attachment(new DocumentAttachment("application/octet-stream", new String(attach),
                "", "SHA1", "fileName.pdf"));

        RCMRIN000002UV01 request = documentBuilder.build();
        WSUtil.validateObjectSize(request);
    }
}
