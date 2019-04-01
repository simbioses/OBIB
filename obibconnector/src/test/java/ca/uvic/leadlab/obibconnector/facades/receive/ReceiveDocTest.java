package ca.uvic.leadlab.obibconnector.facades.receive;

import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;
import ca.uvic.leadlab.obibconnector.impl.receive.ReceiveDoc;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ReceiveDocTest {

    //@Test
    public void testPollNewDocIDs() throws Exception {
        IReceiveDoc receiveDoc = new ReceiveDoc("cdxpostprod-otca");

        List<String> documentsIds = receiveDoc.pollNewDocIDs();

        Assert.assertNotNull(documentsIds);
    }

    //@Test(expected = OBIBException.class)
    public void testPollNewDocIDsError() throws Exception {
        IReceiveDoc receiveDoc = new ReceiveDoc("_XYZXZYXZYXZY_");

        List<String> documentsIds = receiveDoc.pollNewDocIDs();

        //Assert.assertNull(documentsIds);
    }

    //@Test
    public void testRetrieveDocument() throws Exception {
        IReceiveDoc receiveDoc = new ReceiveDoc("cdxpostprod-otca");

        IDocument document = receiveDoc.retrieveDocument("2b0d8260-0c20-e911-a96a-0050568c55a6");

        Assert.assertNotNull(document);
        //Assert.assertEquals("2b0d8260-0c20-e911-a96a-0050568c55a6", document.getDocumentID());
    }

    //@Test(expected = OBIBException.class)
    public void testRetrieveDocumentError() throws Exception {
        IReceiveDoc receiveDoc = new ReceiveDoc("cdxpostprod-otca");

        IDocument document = receiveDoc.retrieveDocument("_XYZXZYXZYXZY_");

        //Assert.assertNull(document);
    }

}
