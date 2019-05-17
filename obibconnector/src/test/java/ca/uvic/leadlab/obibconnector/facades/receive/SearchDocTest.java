package ca.uvic.leadlab.obibconnector.facades.receive;

import ca.uvic.leadlab.obibconnector.facades.FacadesBaseTest;
import ca.uvic.leadlab.obibconnector.impl.receive.SearchDoc;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SearchDocTest extends FacadesBaseTest {

    @Test
    public void testSearchDocumentsByClinic() throws Exception {
        ISearchDoc searchDoc = new SearchDoc(configClinicA);

        List<IDocument> documents = searchDoc.searchDocumentsByClinic(configClinicA.getClinicId());

        Assert.assertNotNull(documents);

        System.out.println("Documents count: " + documents.size());
        System.out.println(mapper.writeValueAsString(documents));
    }

    @Test
    public void testSearchDocumentById() throws Exception {
        ISearchDoc searchDoc = new SearchDoc(configClinicA);

        IDocument document = searchDoc.searchDocumentById(configClinicA.getClinicId(),
                "1f53fad2-d380-4d50-a681-8b3d873645f1");

        Assert.assertNotNull(document);
        System.out.println(mapper.writeValueAsString(document));
    }
}
