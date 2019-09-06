package ca.uvic.leadlab.obibconnector.facades.receive;

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
        IReceiveDoc receiveDoc = new ReceiveDoc(config);

        List<String> documentsIds = receiveDoc.pollNewDocIDs();

        Assert.assertNotNull(documentsIds);

        System.out.println("Documents count: " + documentsIds.size());
        for (String id : documentsIds) {
            System.out.println(id);
        }
    }

    @Test
    public void testRetrieveDocument() throws Exception {
        IReceiveDoc receiveDoc = new ReceiveDoc(config);

        IDocument document = receiveDoc.retrieveDocument("7744d591-b0b4-e911-a96d-0050568c55a6");

        Assert.assertNotNull(document);

        System.out.println(mapper.writeValueAsString(document));
    }

    @Test(expected = OBIBException.class)
    public void testRetrieveDocumentError() throws Exception {
        try {
            IReceiveDoc receiveDoc = new ReceiveDoc(config);

            IDocument document = receiveDoc.retrieveDocument("__Wrong_ID");

            //Assert.assertNull(document);
        } catch (OBIBException ex) {
            System.out.println("Message: " + ex.getMessage());
            System.out.println("OBIB Message: " + ex.getObibMessage());
            throw ex;
        }
    }

    @Test
    public void testRetrieveAllDocuments() throws Exception {
        ISearchDoc searchDoc = new SearchDoc(config);
        IReceiveDoc receiveDoc = new ReceiveDoc(config);
        ISupport support = new Support(config);

        List<IDocument> documents = searchDoc.searchDocumentsByClinic(clinicIdA);

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
