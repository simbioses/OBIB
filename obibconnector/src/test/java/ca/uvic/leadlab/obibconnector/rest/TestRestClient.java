package ca.uvic.leadlab.obibconnector.rest;

import ca.uvic.leadlab.obibconnector.facades.datatypes.AddressType;
import ca.uvic.leadlab.obibconnector.facades.datatypes.NameType;
import ca.uvic.leadlab.obibconnector.facades.datatypes.TelcoType;
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

import java.io.IOException;
import java.util.Date;

public class TestRestClient {

    //@Test
    public void testSubmitDocument() throws IOException {
        IOscarInformation restClient = new RestClient("cdxpostprod-otca");

        ClinicalDocument document = (ClinicalDocument) new SubmitDoc("11111")
                .patient()
                    .id("2222")
                    .name(NameType.LEGAL, "Joe", "Wine")
                    .address(AddressType.HOME, "111 Main St", "Victoria", "BC", "V8V Z9Z", "CA")
                    .phone(TelcoType.HOME, "250-111-1234")
                .and().author()
                    .id("3333")
                    .participantTime(new Date())
                    .name(NameType.LEGAL, "Joseph", "Cloud")
                    .address(AddressType.HOME, "111 Main St", "Victoria", "BC", "V8V Z9Z", "CA")
                    .phone(TelcoType.HOME, "250-111-1234")
                .and().recipient()
                    .id("4444")
                    .name(NameType.LEGAL, "Joseph", "Cloud")
                    .address(AddressType.HOME, "111 Main St", "Victoria", "BC", "V8V Z9Z", "CA")
                    .phone(TelcoType.HOME, "250-111-1234")
                .and().participant()
                    .id("555")
                    .name(NameType.LEGAL, "Joseph", "Cloud")
                    .address(AddressType.HOME, "111 Main St", "Victoria", "BC", "V8V Z9Z", "CA")
                    .phone(TelcoType.HOME, "250-111-1234")
                .and().content("Plain text document content")
                .submit();

        SubmitDocumentResponse response = restClient.submitCDA(document);

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(response));

        Assert.assertNotNull(response);
    }

    //@Test
    public void testListDocument() throws IOException {
        IOscarInformation restClient = new RestClient("cdxpostprod-otca");

        ListDocumentsResponse response = restClient.listDocument();

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(response));

        Assert.assertNotNull(response);
    }

    //@Test
    public void testSearchDocument() throws IOException {
        IOscarInformation restClient = new RestClient("cdxpostprod-otca");

        ListDocumentsResponse response = restClient.searchDocument(SearchDocumentCriteria
                .byClinicId("cdxpostprod-otca"));

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(response));

        Assert.assertNotNull(response);
    }

    //@Test
    public void testGetDocument() throws IOException {
        IOscarInformation restClient = new RestClient("cdxpostprod-otca");

        ListDocumentsResponse response = restClient.getDocument(SearchDocumentCriteria
                .byDocumentId("2b0d8260-0c20-e911-a96a-0050568c55a6"));

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(response));

        Assert.assertNotNull(response);
    }

    //@Test
    public void testListClinics() throws IOException {
        IOscarInformation restClient = new RestClient("cdxpostprod-otca");

        ListClinicsResponse response = restClient.listClinics(SearchClinicCriteria.byClinicId("cdxpostprod-otca"));

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(response));

        Assert.assertNotNull(response);
    }

    //@Test
    public void testListProviders() throws IOException {
        IOscarInformation restClient = new RestClient("cdxpostprod-otca");

        ListProvidersResponse response = restClient.listProviders(SearchProviderCriteria.byProviderId("93188"));

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(response));

        Assert.assertNotNull(response);
    }
}
