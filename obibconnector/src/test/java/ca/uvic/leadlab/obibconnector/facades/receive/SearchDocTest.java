package ca.uvic.leadlab.obibconnector.facades.receive;

import ca.uvic.leadlab.obibconnector.facades.FacadesBaseTest;
import ca.uvic.leadlab.obibconnector.impl.receive.SearchDoc;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SearchDocTest extends FacadesBaseTest {

    //@Test
    public void testSearchDocumentsByClinic() throws Exception {
        ISearchDoc searchDoc = new SearchDoc(configClinicA);

        List<IDocument> documents = searchDoc.searchDocumentsByClinic(configClinicA.getClinicId());

        Assert.assertNotNull(documents);
        System.out.println(mapper.writeValueAsString(documents));
    }

    //@Test
    public void testSearchDocumentById() throws Exception {
        ISearchDoc searchDoc = new SearchDoc(configClinicA);

        IDocument document = searchDoc.searchDocumentById(configClinicA.getClinicId(),
                //"ad0007b5-c846-e911-a96a-0050568c55a6"); // Using 'CDX Message ID' = Not Found (?!)
                "45a75b7e-5cb1-4d00-ab7f-b7872de47549"); // Using 'CDX Clinical Document ID' = Found

        Assert.assertNotNull(document);
        System.out.println(mapper.writeValueAsString(document));
    }
}
