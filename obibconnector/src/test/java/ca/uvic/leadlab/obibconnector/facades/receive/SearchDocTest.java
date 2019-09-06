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

        System.out.println("Documents count: " + documents.size());
        System.out.println(mapper.writeValueAsString(documents));
    }

    @Test
    public void testSearchDocumentById() throws Exception {
        ISearchDoc searchDoc = new SearchDoc(config);

        IDocument document = searchDoc.searchDocumentById("006b83bc-be96-46bb-beb1-472dcb12c56a");

        Assert.assertNotNull(document);
        System.out.println(mapper.writeValueAsString(document));
    }

    @Test
    public void testDistributionStatus() throws Exception {
        ISearchDoc searchDoc = new SearchDoc(config);

        IDocument document = searchDoc.distributionStatus("2ffcaae0-45f8-467c-95c9-0fc91ca816dd");

        Assert.assertNotNull(document);
        System.out.println(mapper.writeValueAsString(document));
    }
}
