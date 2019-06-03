package ca.uvic.leadlab.obibconnector.rest;

import ca.uvic.leadlab.obibconnector.models.support.ErrorMessage;
import ca.uvic.leadlab.obibconnector.models.document.ClinicalDocument;
import ca.uvic.leadlab.obibconnector.models.queries.SearchClinicCriteria;
import ca.uvic.leadlab.obibconnector.models.queries.SearchDocumentCriteria;
import ca.uvic.leadlab.obibconnector.models.queries.SearchProviderCriteria;
import ca.uvic.leadlab.obibconnector.models.response.*;
import ca.uvic.leadlab.obibconnector.utils.OBIBConnectorHelper;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestClient implements IOscarInformation {

    private static final String SUBMIT_CDA_PATH = OBIBConnectorHelper.getProperty("obib.submitcda.path");
    private static final String DISTRIBUTION_STATUS_PATH = OBIBConnectorHelper.getProperty("obib.distributionstatus.path");
    private static final String LIST_DOCUMENTS_PATH = OBIBConnectorHelper.getProperty("obib.listdocuments.path");
    private static final String SEARCH_DOCUMENT_PATH = OBIBConnectorHelper.getProperty("obib.searchdocument.path");
    private static final String GET_DOCUMENT_PATH = OBIBConnectorHelper.getProperty("obib.getdocument.path");
    private static final String LIST_CLINICS_PATH = OBIBConnectorHelper.getProperty("obib.listclinics.path");
    private static final String LIST_PROVIDERS_PATH = OBIBConnectorHelper.getProperty("obib.listproviders.path");
    private static final String NOTIFY_ERROR_PATH = OBIBConnectorHelper.getProperty("obib.notifyerror.path");
    private static final String CONNECT_TIMEOUT = OBIBConnectorHelper.getProperty("obib.connect.timeout");
    private static final String READ_TIMEOUT = OBIBConnectorHelper.getProperty("obib.read.timeout");

    private static final Client client = setupRestClient();

    private final String obibURL;
    private final String locationId;

    public RestClient(String obibURL, String locationId) {
        this.obibURL = obibURL;
        this.locationId = locationId;
    }

    private static Client setupRestClient() {
        ClientConfig config = new ClientConfig()
                //.register(new JacksonJsonProvider())
                .property(ClientProperties.CONNECT_TIMEOUT, CONNECT_TIMEOUT)
                .property(ClientProperties.READ_TIMEOUT, READ_TIMEOUT);
        return ClientBuilder.newClient(config);
    }

    private String getServicesURL() {
        return obibURL;
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
            Response response = client.target(getServicesURL())
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
    public ListDocumentsResponse distributionStatus(SearchDocumentCriteria searchCriteria) throws OBIBRequestException {
        return doRequest(DISTRIBUTION_STATUS_PATH, searchCriteria, ListDocumentsResponse.class);
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

    @Override
    public OBIBResponse notifyError(ErrorMessage error) throws OBIBRequestException {
        return doRequest(NOTIFY_ERROR_PATH, error, OBIBResponse.class);
    }
}