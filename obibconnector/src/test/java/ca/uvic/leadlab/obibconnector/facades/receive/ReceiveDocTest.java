package ca.uvic.leadlab.obibconnector.facades.receive;

import ca.uvic.leadlab.obibconnector.facades.Config;
import ca.uvic.leadlab.obibconnector.facades.FacadesBaseTest;
import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;
import ca.uvic.leadlab.obibconnector.facades.support.ISupport;
import ca.uvic.leadlab.obibconnector.impl.receive.ReceiveDoc;
import ca.uvic.leadlab.obibconnector.impl.receive.SearchDoc;
import ca.uvic.leadlab.obibconnector.impl.support.Support;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ReceiveDocTest extends FacadesBaseTest {

    @Test
    public void testPollNewDocIDs() throws Exception {
        IReceiveDoc receiveDoc = new ReceiveDoc(configClinicA);

        List<String> documentsIds = receiveDoc.pollNewDocIDs();

        Assert.assertNotNull(documentsIds);

        System.out.println("Documents count: " + documentsIds.size());
        for (String id : documentsIds) {
            System.out.println(id);
        }
    }

    @Test(expected = OBIBException.class)
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

    @Test
    public void testRetrieveDocument() throws Exception {
        IReceiveDoc receiveDoc = new ReceiveDoc(configClinicT);

        IDocument document = receiveDoc.retrieveDocument("e1e7ada8-41a7-e911-a96d-0050568c55a6");

        Assert.assertNotNull(document);

        System.out.println(mapper.writeValueAsString(document));
    }

    @Test(expected = OBIBException.class)
    public void testRetrieveDocumentError() throws Exception {
        IReceiveDoc receiveDoc = new ReceiveDoc(configClinicA);

        IDocument document = receiveDoc.retrieveDocument("__Wrong_ID");

        //Assert.assertNull(document);
    }

    @Test
    public void testRetrieveAllDocuments() throws Exception {
        Config configClinic = configClinicT;
        ISearchDoc searchDoc = new SearchDoc(configClinic);
        IReceiveDoc receiveDoc = new ReceiveDoc(configClinic);
        ISupport support = new Support(configClinic);

        List<IDocument> documents = searchDoc.searchDocumentsByClinic(configClinic.getClinicId());

        Assert.assertNotNull(documents);
        System.out.println("Total documents: " + documents.size());
        for (IDocument doc : documents) {
            try {
                IDocument document = receiveDoc.retrieveDocument(doc.getDocumentID());

                Assert.assertNotNull(document);
                System.out.println("Retrieved Document: " + doc.getDocumentID());
                System.out.println(mapper.writeValueAsString(document));
            } catch (Exception e) {
                System.out.println("Error retrieving Document: " + doc.getDocumentID());
                support.notifyError("Error retrieving Document: " + doc.getDocumentID(), e.getMessage());
            }
        }
    }

}
