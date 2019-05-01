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

        IDocument document = searchDoc.searchDocumentById("9965cc95-29df-4a4e-be26-93269d7a46c4");

        Assert.assertNotNull(document);
        System.out.println(mapper.writeValueAsString(document));
    }
}
