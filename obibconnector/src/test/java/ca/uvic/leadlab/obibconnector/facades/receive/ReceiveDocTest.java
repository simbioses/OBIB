package ca.uvic.leadlab.obibconnector.facades.receive;

import ca.uvic.leadlab.obibconnector.facades.Config;
import ca.uvic.leadlab.obibconnector.facades.FacadesBaseTest;
import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;
import ca.uvic.leadlab.obibconnector.impl.receive.ReceiveDoc;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ReceiveDocTest extends FacadesBaseTest {

    //@Test
    public void testPollNewDocIDs() throws Exception {
        IReceiveDoc receiveDoc = new ReceiveDoc(configClinicA);

        List<String> documentsIds = receiveDoc.pollNewDocIDs();

        Assert.assertNotNull(documentsIds);

        System.out.println("Documents count: " + documentsIds.size());
        for (String id : documentsIds) {
            System.out.println(id);
        }
    }

    //@Test(expected = OBIBException.class)
    public void testPollNewDocIDsError() throws Exception {
        IReceiveDoc receiveDoc = new ReceiveDoc(new Config() {
            @Override
            public String getUrl() {
                return obibUrl;
            }

            @Override
            public String getClinicId() {
                return "__Wrong_ID";
            }
        });

        List<String> documentsIds = receiveDoc.pollNewDocIDs();

        //Assert.assertNull(documentsIds);
    }

    //@Test
    public void testRetrieveDocument() throws Exception {
        IReceiveDoc receiveDoc = new ReceiveDoc(configClinicA);

        IDocument document = receiveDoc.retrieveDocument("ad0007b5-c846-e911-a96a-0050568c55a6");

        Assert.assertNotNull(document);
        //Assert.assertEquals("2b0d8260-0c20-e911-a96a-0050568c55a6", document.getDocumentID());

        System.out.println(mapper.writeValueAsString(document));
    }

    //@Test(expected = OBIBException.class)
    public void testRetrieveDocumentError() throws Exception {
        IReceiveDoc receiveDoc = new ReceiveDoc(configClinicA);

        IDocument document = receiveDoc.retrieveDocument("__Wrong_ID");

        //Assert.assertNull(document);
    }

}
