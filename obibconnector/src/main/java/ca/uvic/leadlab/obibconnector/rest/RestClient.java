package ca.uvic.leadlab.obibconnector.rest;

import ca.uvic.leadlab.obibconnector.models.document.ClinicalDocument;
import ca.uvic.leadlab.obibconnector.models.queries.SearchClinicCriteria;
import ca.uvic.leadlab.obibconnector.models.queries.SearchDocumentCriteria;
import ca.uvic.leadlab.obibconnector.models.queries.SearchProviderCriteria;
import ca.uvic.leadlab.obibconnector.models.response.*;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Properties;

public class RestClient implements IOscarInformation {

    private static final Properties properties = setupProperties();

    private static final String SERVICES_BASE_URL = properties.getProperty("obib.services.base.uri");
    private static final String SUBMIT_CDA_PATH = properties.getProperty("obib.submitcda.path");
    private static final String LIST_DOCUMENTS_PATH = properties.getProperty("obib.listdocuments.path");
    private static final String SEARCH_DOCUMENT_PATH = properties.getProperty("obib.searchdocument.path");
    private static final String GET_DOCUMENT_PATH = properties.getProperty("obib.getdocument.path");
    private static final String LIST_CLINICS_PATH = properties.getProperty("obib.listclinics.path");
    private static final String LIST_PROVIDERS_PATH = properties.getProperty("obib.listproviders.path");
    private static final String CONNECT_TIMEOUT = properties.getProperty("obib.connect.timeout");
    private static final String READ_TIMEOUT = properties.getProperty("obib.read.timeout");

    private static final Client client = setupRestClient();

    private final String locationId;

    public RestClient(String locationId) {
        this.locationId = locationId;
    }

    private static Properties setupProperties() {
        Properties properties = new Properties();
        try {
            properties.load(RestClient.class.getResourceAsStream("/obibconnector.properties"));
        } catch (Exception e) {
            e.printStackTrace(); // TODO log this exception
        }
        return properties;
    }

    private static Client setupRestClient() {
        ClientConfig config = new ClientConfig()
                .register(new JacksonJsonProvider())
                .setProperty(ClientProperties.CONNECT_TIMEOUT, CONNECT_TIMEOUT)
                .setProperty(ClientProperties.READ_TIMEOUT, READ_TIMEOUT);
        return ClientFactory.newClient(config);
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
    private <T, R extends OBIBResponse> R doRequest(String path, T requestEntity, Class<R> responseEntity) throws OBIBRequestException {
        try {
            Response response = client.target(SERVICES_BASE_URL)
                    .path(path)
                    .request(MediaType.APPLICATION_JSON)
                    .header("locationId", locationId)
                    .post(Entity.json(requestEntity), Response.class);

            if (response.getStatus() != 200) {
                // TODO throw exception?
            }

            return response.readEntity(responseEntity);
        } catch (Exception e) {
            throw new OBIBRequestException("Error submitting request to OBIB Server.", e);
        }
    }

    @Override
    public SubmitDocumentResponse submitCDA(ClinicalDocument clinicalDocument) throws OBIBRequestException {
        return doRequest(SUBMIT_CDA_PATH, clinicalDocument, SubmitDocumentResponse.class);
    }

    @Override
    public ListDocumentsResponse listDocument() throws OBIBRequestException {
        return doRequest(LIST_DOCUMENTS_PATH, new SearchDocumentCriteria(), ListDocumentsResponse.class);
    }

    @Override
    public ListDocumentsResponse searchDocument(SearchDocumentCriteria searchCriteria) throws OBIBRequestException {
        return doRequest(SEARCH_DOCUMENT_PATH, searchCriteria, ListDocumentsResponse.class);
    }

    @Override
    public ListDocumentsResponse getDocument(SearchDocumentCriteria searchCriteria) throws OBIBRequestException {
        return doRequest(GET_DOCUMENT_PATH, searchCriteria, ListDocumentsResponse.class);
    }

    @Override
    public ListProvidersResponse listProviders(SearchProviderCriteria searchCriteria) throws OBIBRequestException {
        return doRequest(LIST_PROVIDERS_PATH, searchCriteria, ListProvidersResponse.class);
    }

    @Override
    public ListClinicsResponse listClinics(SearchClinicCriteria searchCriteria) throws OBIBRequestException {
        return doRequest(LIST_CLINICS_PATH, searchCriteria, ListClinicsResponse.class);
    }
}