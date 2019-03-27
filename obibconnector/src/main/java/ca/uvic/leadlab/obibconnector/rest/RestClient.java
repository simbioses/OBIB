package ca.uvic.leadlab.obibconnector.rest;

import ca.uvic.leadlab.obibconnector.models.document.ClinicalDocument;
import ca.uvic.leadlab.obibconnector.models.queries.SearchClinicCriteria;
import ca.uvic.leadlab.obibconnector.models.queries.SearchDocumentCriteria;
import ca.uvic.leadlab.obibconnector.models.queries.SearchProviderCriteria;
import ca.uvic.leadlab.obibconnector.models.response.*;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class RestClient implements IOscarInformation {

    private static Properties properties = new Properties();

    static {
        try {
            String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
            properties.load(new FileInputStream(path + "obibconnector.properties"));
        } catch (IOException e) {
            e.printStackTrace(); // TODO improve this exception with a logger
        }
    }

    private static final String SERVICES_BASE_URL = properties.getProperty("obib.services.base.uri");
    private static final String SUBMIT_CDA_PATH = properties.getProperty("obib.submitcda.path");
    private static final String LIST_DOCUMENTS_PATH = properties.getProperty("obib.listdocuments.path");
    private static final String SEARCH_DOCUMENT_PATH = properties.getProperty("obib.searchdocument.path");
    private static final String GET_DOCUMENT_PATH = properties.getProperty("obib.getdocument.path");
    private static final String LIST_CLINICS_PATH = properties.getProperty("obib.listclinics.path");
    private static final String LIST_PROVIDERS_PATH = properties.getProperty("obib.listproviders.path");

    private Client client;

    private String locationId;

    public RestClient(String locationId) {
        this.locationId = locationId;
        this.client = ClientFactory.newClient(new ClientConfig().register(new JacksonJsonProvider()));
    }

    /**
     * Do a POST request
     *
     * @param path String with the path of the rest method
     * @param requestEntity T entity
     * @param responseEntity Class response type
     * @param <T>
     * @param <R>
     * @return
     */
    private <T, R extends OBIBResponse> R doRequest(String path, T requestEntity, Class<R> responseEntity) {
        try {
            Response response = client.target(SERVICES_BASE_URL)
                    .path(path)
                    .request(MediaType.APPLICATION_JSON)
                    .header("locationId", locationId)
                    .post(Entity.json(requestEntity), Response.class);

            if (response.getStatus() != 200) {
                // TODO return OBIBResponse.errorResponse(response.readEntity(String.class));
            }

            return response.readEntity(responseEntity);
        } catch (Exception e) {
            e.printStackTrace(); // TODO improve this exception with a logger
            return null;
        }
    }

    @Override
    public SubmitDocumentResponse submitCDA(ClinicalDocument clinicalDocument) {
        return doRequest(SUBMIT_CDA_PATH, clinicalDocument, SubmitDocumentResponse.class);
    }

    @Override
    public ListDocumentsResponse listDocument() {
        return doRequest(LIST_DOCUMENTS_PATH, new SearchDocumentCriteria(), ListDocumentsResponse.class);
    }

    @Override
    public ListDocumentsResponse searchDocument(SearchDocumentCriteria searchCriteria) {
        return doRequest(SEARCH_DOCUMENT_PATH, searchCriteria, ListDocumentsResponse.class);
    }

    @Override
    public ListDocumentsResponse getDocument(SearchDocumentCriteria searchCriteria) {
        return doRequest(GET_DOCUMENT_PATH, searchCriteria, ListDocumentsResponse.class);
    }

    @Override
    public ListProvidersResponse listProviders(SearchProviderCriteria searchCriteria) {
        return doRequest(LIST_PROVIDERS_PATH, searchCriteria, ListProvidersResponse.class);
    }

    @Override
    public ListClinicsResponse listClinics(SearchClinicCriteria searchCriteria) {
        return doRequest(LIST_CLINICS_PATH, searchCriteria, ListClinicsResponse.class);
    }
}