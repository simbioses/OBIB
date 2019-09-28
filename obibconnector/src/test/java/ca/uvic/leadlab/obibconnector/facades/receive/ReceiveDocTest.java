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

    private IReceiveDoc receiveDoc = new ReceiveDoc(config);
    private ISearchDoc searchDoc = new SearchDoc(config);
    private ISupport support = new Support(config);

    @Test
    public void testPollNewDocIDs() throws Exception {
        List<String> documentsIds = receiveDoc.pollNewDocIDs();

        Assert.assertNotNull(documentsIds);
        Assert.assertFalse(documentsIds.isEmpty());

        System.out.println("Documents count: " + documentsIds.size());
        for (String id : documentsIds) {
            System.out.println(id);
        }
    }

    @Test
    public void testRetrieveDocument() throws Exception {
        IDocument document = receiveDoc.retrieveDocument("7744d591-b0b4-e911-a96d-0050568c55a6");

        Assert.assertNotNull(document);

        System.out.println(mapper.writeValueAsString(document));
    }

    @Test(expected = OBIBException.class)
    public void testRetrieveNonexistentDocument() throws Exception {
        try {
            IDocument document = receiveDoc.retrieveDocument("12345678-aaaa-bbbb-cccc-0055566cddcc");

            //Assert.assertNull(document);
        } catch (OBIBException ex) {
            System.out.println("Message: " + ex.getMessage());
            System.out.println("OBIB Message: " + ex.getObibMessage());
            throw ex;
        }
    }

    @Test(expected = OBIBException.class)
    public void testRetrieveDocumentError() throws Exception {
        try {
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
        long startSearch = System.currentTimeMillis();
        List<IDocument> documents = searchDoc.searchDocuments();
        long endSearch = System.currentTimeMillis();

        Assert.assertNotNull(documents);
        System.out.println("Total documents: " + documents.size());
        System.out.println("Time elapsed: " + (endSearch - startSearch));
        for (IDocument doc : documents) {
            try {
                long startRetrieve = System.currentTimeMillis();
                IDocument document = receiveDoc.retrieveDocument(doc.getDocumentID());
                long endRetrieve = System.currentTimeMillis();

                Assert.assertNotNull(document);
                System.out.println("Retrieved Document: " + doc.getDocumentID());
                System.out.println("Time elapsed: " + (endRetrieve - startRetrieve));
                //System.out.println(mapper.writeValueAsString(document));
            } catch (Exception e) {
                System.out.println("Error retrieving Document: " + doc.getDocumentID());
                support.notifyError("Error retrieving Document: " + doc.getDocumentID(), e.getMessage());
            }
        }
    }

}
