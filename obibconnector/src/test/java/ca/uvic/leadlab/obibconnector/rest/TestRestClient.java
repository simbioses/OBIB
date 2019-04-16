package ca.uvic.leadlab.obibconnector.rest;

import ca.uvic.leadlab.obibconnector.facades.Config;
import ca.uvic.leadlab.obibconnector.facades.datatypes.*;
import ca.uvic.leadlab.obibconnector.impl.send.SubmitDoc;
import ca.uvic.leadlab.obibconnector.models.document.ClinicalDocument;
import ca.uvic.leadlab.obibconnector.models.queries.SearchClinicCriteria;
import ca.uvic.leadlab.obibconnector.models.queries.SearchDocumentCriteria;
import ca.uvic.leadlab.obibconnector.models.queries.SearchProviderCriteria;
import ca.uvic.leadlab.obibconnector.models.response.ListClinicsResponse;
import ca.uvic.leadlab.obibconnector.models.response.ListDocumentsResponse;
import ca.uvic.leadlab.obibconnector.models.response.ListProvidersResponse;
import ca.uvic.leadlab.obibconnector.models.response.SubmitDocumentResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class TestRestClient {

    private ObjectMapper mapper = new ObjectMapper();
    private String obibUrl = "http://192.168.100.101:8081";
    private String clinicId = "cdxpostprod-otca";

    private Config config = new Config() {
        @Override
        public String getUrl() {
            return obibUrl;
        }

        @Override
        public String getClinicId() {
            return clinicId;
        }
    };

    //@Test
    public void testSubmitDocument() throws Exception {
        IOscarInformation restClient = new RestClient(obibUrl, clinicId);

        ClinicalDocument document = ((SubmitDoc) new SubmitDoc(config)
                .newDoc()
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
                .and().content("Plain text document content"))
                .getDocument();
        System.out.println(mapper.writeValueAsString(document));

        SubmitDocumentResponse response = restClient.submitCDA(document);
        System.out.println(mapper.writeValueAsString(response));

        Assert.assertNotNull(response);
    }

    //@Test
    public void testListDocument() throws Exception {
        IOscarInformation restClient = new RestClient(obibUrl, clinicId);

        ListDocumentsResponse response = restClient.listDocument();
        System.out.println(mapper.writeValueAsString(response));

        Assert.assertNotNull(response);
    }

    //@Test
    public void testSearchDocument() throws Exception {
        IOscarInformation restClient = new RestClient(obibUrl, clinicId);

        ListDocumentsResponse response = restClient.searchDocument(SearchDocumentCriteria
                .byClinicId("cdxpostprod-otca"));
        System.out.println(mapper.writeValueAsString(response));

        Assert.assertNotNull(response);
    }

    //@Test
    public void testGetDocument() throws Exception {
        IOscarInformation restClient = new RestClient(obibUrl, clinicId);

        ListDocumentsResponse response = restClient.getDocument(SearchDocumentCriteria
                .byDocumentId("ad0007b5-c846-e911-a96a-0050568c55a6"));
        System.out.println(mapper.writeValueAsString(response));

        Assert.assertNotNull(response);
    }

    //@Test
    public void testListClinics() throws Exception {
        IOscarInformation restClient = new RestClient(obibUrl, clinicId);

        ListClinicsResponse response = restClient.listClinics(SearchClinicCriteria.byClinicId("cdxpostprod-otca"));
        System.out.println(mapper.writeValueAsString(response));

        Assert.assertNotNull(response);
    }

    //@Test
    public void testListProviders() throws Exception {
        IOscarInformation restClient = new RestClient(obibUrl, clinicId);

        ListProvidersResponse response = restClient.listProviders(SearchProviderCriteria.byProviderId("93188"));
        System.out.println(mapper.writeValueAsString(response));

        Assert.assertNotNull(response);
    }
}
