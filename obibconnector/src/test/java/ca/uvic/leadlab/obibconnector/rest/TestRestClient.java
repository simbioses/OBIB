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
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class TestRestClient {

    private String obibUrl = "http://192.168.100.101:8081";
    private String clinicId = "cdxpostprod-otca";

    private IOscarInformation restClient;

    private ObjectMapper mapper = new ObjectMapper(); // to visualize the objects
    private Config config = new Config() { // to create a document on submit tests
        @Override
        public String getUrl() {
            return obibUrl;
        }

        @Override
        public String getClinicId() {
            return clinicId;
        }
    };

    @Before
    public void setup() {
        restClient = new RestClient(obibUrl, clinicId);
    }

    @Test
    public void testSubmitDocument() throws Exception {
        ClinicalDocument document = ((SubmitDoc) new SubmitDoc(config).newDoc()
                .documentType(DocumentType.REFERRAL_NOTE)
                .patient()
                    .id("2222")
                    .name(NameType.LEGAL, "Joe", "Wine")
                    .address(AddressType.HOME, "111 Main St", "Victoria", "BC", "V8V Z9Z", "CA")
                    .phone(TelcoType.HOME, "250-111-1234")
                    .gender(Gender.MALE)
                    .birthday("1980", "08", "19")
                .and().author()
                    .id("3333")
                    .time(new Date())
                    .name(NameType.LEGAL, "Joseph", "Cloud")
                    .address(AddressType.HOME, "111 Main St", "Victoria", "BC", "V8V Z9Z", "CA")
                    .phone(TelcoType.HOME, "250-111-1234")
                .and().recipient()
                    .primary()
                    .id("4444")
                    .name(NameType.LEGAL, "Joseph", "Cloud")
                    .address(AddressType.HOME, "111 Main St", "Victoria", "BC", "V8V Z9Z", "CA")
                    .phone(TelcoType.HOME, "250-111-1234")
                .and().recipient()
                    .id("6666")
                    .name(NameType.LEGAL, "Joseph", "Cloud")
                    .address(AddressType.HOME, "111 Main St", "Victoria", "BC", "V8V Z9Z", "CA")
                    .phone(TelcoType.HOME, "250-111-1234")
                .and().participant()
                    .id("5555")
                    .name(NameType.LEGAL, "Joseph", "Cloud")
                    .address(AddressType.HOME, "111 Main St", "Victoria", "BC", "V8V Z9Z", "CA")
                    .phone(TelcoType.HOME, "250-111-1234")
                .and().inFulfillmentOf()
                    .id("1111")
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
                .byDocumentId("006b83bc-be96-46bb-beb1-472dcb12c56a"));
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
        ListDocumentsResponse response = restClient.searchDocument(SearchDocumentCriteria
                .byClinicId("cdxpostprod-otca"));
        System.out.println(mapper.writeValueAsString(response));

        Assert.assertNotNull(response);
    }

    @Test
    public void testSearchDocumentWithDate() throws Exception {
        SearchDocumentCriteria criteria = SearchDocumentCriteria.byClinicId("cdxpostprod-otca");
        Calendar cal = Calendar.getInstance();
        cal.set(2019, Calendar.MARCH, 1);
        Date start = cal.getTime();
        cal.set(2019, Calendar.APRIL, 1);
        Date end = cal.getTime();
        criteria.setEffectiveTime(new DateRange(start, end));

        ListDocumentsResponse response = restClient.searchDocument(criteria);
        System.out.println(mapper.writeValueAsString(response));

        Assert.assertNotNull(response);
    }

    @Test
    public void testSearchDocumentById() throws Exception {
        ListDocumentsResponse response = restClient.searchDocument(SearchDocumentCriteria
                .byDocumentId("61a1a387-408b-4e5c-be24-1976ace1c280"));
        System.out.println(mapper.writeValueAsString(response));

        Assert.assertNotNull(response);
    }

    @Test
    public void testGetDocument() throws Exception {
        ListDocumentsResponse response = restClient.getDocument(SearchDocumentCriteria
                .byDocumentId("db0e5fb8-c946-e911-a96a-0050568c55a6"));
        System.out.println(mapper.writeValueAsString(response));

        Assert.assertNotNull(response);
    }

    @Test
    public void testListClinics() throws Exception {
        ListClinicsResponse response = restClient.listClinics(SearchClinicCriteria.byClinicId("cdxpostprod-otca"));
        System.out.println(mapper.writeValueAsString(response));

        Assert.assertNotNull(response);
    }

    @Test
    public void testListProviders() throws Exception {
        ListProvidersResponse response = restClient.listProviders(SearchProviderCriteria.byProviderId("93188"));
        System.out.println(mapper.writeValueAsString(response));

        Assert.assertNotNull(response);
    }

    @Test(expected = OBIBRequestException.class)
    public void testConnectionError() throws Exception {
        RestClient restClientError = new RestClient("http://192.168.0.0:80", "");
        ListDocumentsResponse response = restClientError.distributionStatus(SearchDocumentCriteria
                .byDocumentId("006b83bc-be96-46bb-beb1-472dcb12c56a"));
        System.out.println(mapper.writeValueAsString(response));

        Assert.assertNull(response);
    }
}
