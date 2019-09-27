package ca.uvic.leadlab.obibconnector.facades.receive;

import ca.uvic.leadlab.obibconnector.facades.FacadesBaseTest;
import ca.uvic.leadlab.obibconnector.impl.receive.SearchDoc;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SearchDocTest extends FacadesBaseTest {

    @Test
    public void testSearchDocumentsByClinic() throws Exception {
        ISearchDoc searchDoc = new SearchDoc(config);

        List<IDocument> documents = searchDoc.searchDocuments();

        Assert.assertNotNull(documents);
        Assert.assertFalse(documents.isEmpty());

        System.out.println("Documents count: " + documents.size());
        System.out.println(mapper.writeValueAsString(documents));
    }

    @Test
    public void testSearchDocumentsByClinicWithDate() throws Exception {
        ISearchDoc searchDoc = new SearchDoc(config);

        Calendar cal = Calendar.getInstance();
        cal.set(2019, Calendar.MARCH, 1);
        Date start = cal.getTime();
        cal.set(2019, Calendar.APRIL, 1);
        Date end = cal.getTime();

        List<IDocument> documents = searchDoc.searchDocumentsByPeriod(start, end);

        Assert.assertNotNull(documents);
        Assert.assertFalse(documents.isEmpty());

        System.out.println("Documents count: " + documents.size());
        System.out.println(mapper.writeValueAsString(documents));
    }

    @Test
    public void testSearchDocumentById() throws Exception {
        ISearchDoc searchDoc = new SearchDoc(config);

        List<IDocument> documents = searchDoc.searchDocumentById("f9841c14-941a-4406-aa96-861775e37492");

        Assert.assertNotNull(documents);
        Assert.assertFalse(documents.isEmpty());

        System.out.println("Documents count: " + documents.size());
        System.out.println(mapper.writeValueAsString(documents));
    }

    @Test
    public void testDistributionStatus() throws Exception {
        ISearchDoc searchDoc = new SearchDoc(config);

        List<IDocument> documents = searchDoc.distributionStatus("c992baed-6be6-4a77-85a4-18982ced957c");

        Assert.assertNotNull(documents);
        Assert.assertFalse(documents.isEmpty());

        System.out.println("Documents count: " + documents.size());
        System.out.println(mapper.writeValueAsString(documents));
    }
}
