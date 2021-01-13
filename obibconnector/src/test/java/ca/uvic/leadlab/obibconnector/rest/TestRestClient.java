package ca.uvic.leadlab.obibconnector.rest;

import ca.uvic.leadlab.obibconnector.facades.Config;
import ca.uvic.leadlab.obibconnector.facades.datatypes.*;
import ca.uvic.leadlab.obibconnector.impl.send.SubmitDoc;
import ca.uvic.leadlab.obibconnector.models.document.ClinicalDocument;
import ca.uvic.leadlab.obibconnector.models.queries.DateRange;
import ca.uvic.leadlab.obibconnector.models.queries.SearchClinicCriteria;
import ca.uvic.leadlab.obibconnector.models.queries.SearchDocumentCriteria;
import ca.uvic.leadlab.obibconnector.models.queries.SearchProviderCriteria;
import ca.uvic.leadlab.obibconnector.models.response.ListClinicsResponse;
import ca.uvic.leadlab.obibconnector.models.response.ListDocumentsResponse;
import ca.uvic.leadlab.obibconnector.models.response.ListProvidersResponse;
import ca.uvic.leadlab.obibconnector.models.response.SubmitDocumentResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class TestRestClient {

    private static String obibUrl = "https://192.168.100.101";

    private static IOscarInformation restClient;

    private ObjectMapper mapper = new ObjectMapper(); // to visualize the objects
    private Config config = new Config() { // to create a document on submit tests
        @Override
        public String getUrl() {
            return obibUrl;
        }
    };

    @BeforeClass
    public static void setup() throws Exception {
        restClient = new RestClient(obibUrl);
    }

    @Test
    public void testSubmitDocument() throws Exception {
        ClinicalDocument document = ((SubmitDoc) new SubmitDoc(config).newDoc()
                .documentType(DocumentType.REFERRAL_NOTE)
                .patient()
                    .id("8888999904")
                    .name(NameType.LEGAL, "JUNE", "ELDER")
                    .address(AddressType.HOME, "456 Main Street", "Toronto", "OT", "M6P 4J4", "CA")
                    .phone(TelcoType.HOME, "416-555-6789")
                    .gender(Gender.FEMALE)
                    .birthday("1942", "06", "06")
                .and().author()
                    .id("93188")
                    .time(new Date())
                    .name("Lucius", "Plisihb", "Dr.", null)
                .and().recipient()
                    .primary()
                    .id("11116")
                    .name("Todd", "Kinnee", "Dr.", null)
                .and().inFulfillmentOf()
                    .id("1")
                    .statusCode(OrderStatus.ACTIVE)
                .and()
                    .receiverId("cdxpostprod-obctc")
                    .content("Plain text document content"))
                .getDocument();
        System.out.println(mapper.writeValueAsString(document));

        SubmitDocumentResponse response = restClient.submitCDA(document);
        System.out.println(mapper.writeValueAsString(response));

        Assert.assertNotNull(response);
    }

    @Test
    public void testDistributionStatus() throws Exception {
        ListDocumentsResponse response = restClient.distributionStatus(SearchDocumentCriteria
                .byDocumentId("84082904-4f92-4d20-8014-9789b26afdfa"));
        System.out.println(mapper.writeValueAsString(response));

        Assert.assertNotNull(response);
    }

    @Test
    public void testListDocument() throws Exception {
        ListDocumentsResponse response = restClient.listDocument();
        System.out.println(mapper.writeValueAsString(response));

        Assert.assertNotNull(response);
    }

    @Test
    public void testSearchDocument() throws Exception {
        ListDocumentsResponse response = restClient.searchDocument(new SearchDocumentCriteria());
        System.out.println(mapper.writeValueAsString(response));

        Assert.assertNotNull(response);
    }

    @Test
    public void testSearchDocumentWithDate() throws Exception {
        SearchDocumentCriteria criteria = new SearchDocumentCriteria();
        Calendar cal = Calendar.getInstance();
        cal.set(2020, Calendar.JANUARY, 1);
        Date start = cal.getTime();
        cal.set(2020, Calendar.DECEMBER, 31);
        Date end = cal.getTime();
        criteria.setEffectiveTime(new DateRange(start, end));

        ListDocumentsResponse response = restClient.searchDocument(criteria);
        System.out.println(mapper.writeValueAsString(response));

        Assert.assertNotNull(response);
    }

    @Test
    public void testSearchDocumentById() throws Exception {
        ListDocumentsResponse response = restClient.searchDocument(SearchDocumentCriteria
                .byDocumentId("9a01b514-0955-eb11-a97d-0050568c55a6"));
        System.out.println(mapper.writeValueAsString(response));

        Assert.assertNotNull(response);
    }

    @Test
    public void testGetDocument() throws Exception {
        ListDocumentsResponse response = restClient.getDocument(SearchDocumentCriteria
                .byDocumentId("a670a569-2355-eb11-a97d-0050568c55a6"));
        System.out.println(mapper.writeValueAsString(response));

        Assert.assertNotNull(response);

        ListDocumentsResponse response2 = restClient.getDocument(SearchDocumentCriteria
                .byDocumentId("a670a569-2355-eb11-a97d-0050568c55a6"));
        System.out.println(mapper.writeValueAsString(response2));

        Assert.assertNotNull(response2);

        ListDocumentsResponse response3 = restClient.getDocument(SearchDocumentCriteria
                .byDocumentId("a670a569-2355-eb11-a97d-0050568c55a6"));
        System.out.println(mapper.writeValueAsString(response3));

        Assert.assertNotNull(response3);
    }

    @Test
    public void testListClinics() throws Exception {
        ListClinicsResponse response = restClient.listClinics(SearchClinicCriteria.byClinicId("cdxpostprod-otca"));
        System.out.println(mapper.writeValueAsString(response));

        Assert.assertNotNull(response);
    }

    @Test
    public void testListProviders() throws Exception {
        ListProvidersResponse response = restClient.listProviders(SearchProviderCriteria.byProviderName("Plisihd"));
        System.out.println(mapper.writeValueAsString(response));

        Assert.assertNotNull(response);
    }

    @Test(expected = OBIBRequestException.class)
    public void testConnectionError() throws Exception {
        RestClient restClientError = new RestClient(obibUrl + ":81");
        ListDocumentsResponse response = restClientError.distributionStatus(SearchDocumentCriteria
                .byDocumentId("006b83bc-be96-46bb-beb1-472dcb12c56a"));
        System.out.println(mapper.writeValueAsString(response));

        Assert.assertNull(response);
    }
}
