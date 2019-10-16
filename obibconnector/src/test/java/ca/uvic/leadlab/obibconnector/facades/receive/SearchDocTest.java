package ca.uvic.leadlab.obibconnector.facades.receive;

import ca.uvic.leadlab.obibconnector.facades.FacadesBaseTest;
import ca.uvic.leadlab.obibconnector.impl.receive.SearchDoc;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SearchDocTest extends FacadesBaseTest {

    private ISearchDoc searchDoc = new SearchDoc(config);

    @Test
    public void testSearchDocumentsByClinic() throws Exception {
        List<IDocument> documents = searchDoc.searchDocuments();

        Assert.assertFalse(documents.isEmpty());

        System.out.println("Documents count: " + documents.size());
        System.out.println(mapper.writeValueAsString(documents));
    }

    @Test
    public void testSearchDocumentsByClinicWithDate() throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.set(2019, Calendar.MARCH, 1);
        Date start = cal.getTime();
        cal.set(2019, Calendar.APRIL, 1);
        Date end = cal.getTime();

        List<IDocument> documents = searchDoc.searchDocumentsByPeriod(start, end);

        Assert.assertFalse(documents.isEmpty());

        System.out.println("Documents count: " + documents.size());
        System.out.println(mapper.writeValueAsString(documents));
    }

    @Test
    public void testSearchDocumentById() throws Exception {
        List<IDocument> documents = searchDoc.searchDocumentById("b93216a1-1b78-45da-a13a-b1d2924761c6");

        Assert.assertFalse(documents.isEmpty());

        System.out.println("Documents count: " + documents.size());
        System.out.println(mapper.writeValueAsString(documents));
    }
}
