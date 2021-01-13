package ca.uvic.leadlab.obibconnector.rest;

import ca.uvic.leadlab.obibconnector.models.support.ErrorMessage;
import ca.uvic.leadlab.obibconnector.models.document.ClinicalDocument;
import ca.uvic.leadlab.obibconnector.models.queries.SearchClinicCriteria;
import ca.uvic.leadlab.obibconnector.models.queries.SearchDocumentCriteria;
import ca.uvic.leadlab.obibconnector.models.queries.SearchProviderCriteria;
import ca.uvic.leadlab.obibconnector.models.response.*;
import ca.uvic.leadlab.obibconnector.utils.OBIBConnectorHelper;
import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import javax.net.ssl.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.GeneralSecurityException;
import java.util.Collections;

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

    private static final String OBIB_KEYSTORE_PATH = OBIBConnectorHelper.getPropertyExpanded("obib.keystore.path");
    private static final String OBIB_KEYSTORE_PASS = OBIBConnectorHelper.getPropertyExpanded("obib.keystore.pass");

    private static final String CDX_CLINIC_ID = OBIBConnectorHelper.getProperty("cdx.clinic.id");
    private static final String CDX_CLINIC_PASS = OBIBConnectorHelper.getProperty("cdx.clinic.password");

    private final WebClient client;

    /**
     * Construct a RestClient with a ssl context for authentication
     */
    public RestClient(String obibURL) {
        // build rest client
        this.client =  setupRestClient(obibURL);
    }

    private WebClient setupRestClient(String obibURL) {
        WebClient client = WebClient.create(obibURL, Collections.singletonList(new JacksonJsonProvider()))
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        HTTPConduit httpConduit = WebClient.getConfig(client).getHttpConduit();
        setupTimeout(httpConduit);
        setupSSL(httpConduit);
        return client;
    }

    public void setupTimeout(HTTPConduit httpConduit) {
        HTTPClientPolicy httpClientPolicy = httpConduit.getClient();
        if (httpClientPolicy == null) {
            httpClientPolicy = new HTTPClientPolicy();
        }

        httpClientPolicy.setConnectionTimeout(Long.parseLong(CONNECT_TIMEOUT));
        httpClientPolicy.setReceiveTimeout(Long.parseLong(READ_TIMEOUT));

        httpConduit.setClient(httpClientPolicy);
    }

    public void setupSSL(HTTPConduit httpConduit) {
        TLSClientParameters tlsParams = httpConduit.getTlsClientParameters();
        if (tlsParams == null) {
            tlsParams = new TLSClientParameters();
        }

        tlsParams.setDisableCNCheck(true); // Disable CXF client TLS CN Check
        tlsParams.setSSLSocketFactory(setupSSLContext().getSocketFactory()); // Set SSL Context

        httpConduit.setTlsClientParameters(tlsParams);
    }

    private SSLContext setupSSLContext() {
        try {
            KeyStoreManager keyStoreManager = new KeyStoreManager(OBIB_KEYSTORE_PATH, OBIB_KEYSTORE_PASS);

            SSLContext context = SSLContext.getInstance("TLSv1.2");
            context.init(new KeyManager[]{keyStoreManager}, new TrustManager[]{keyStoreManager}, null);

            return context;
        } catch (GeneralSecurityException e) {
            throw new IllegalArgumentException("Error initializing the SSL Context.", e);
        }
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
    private <T, R extends OBIBResponse> R doRequest(String path, T requestEntity,
                                                    Class<R> responseEntity) throws OBIBRequestException {
        try {
            Response response = client
                    .replacePath(path)
                    .header("clinicId", CDX_CLINIC_ID)
                    .header("password", CDX_CLINIC_PASS)
                    .header("connectorVersion", OBIBConnectorHelper.getOBIBConnectorVersion())
                    .post(requestEntity, Response.class);

            if (!Response.Status.Family.SUCCESSFUL.equals(response.getStatusInfo().getFamily())) {
                throw new OBIBRequestException(String.format("Response Error: %d - %s",
                        response.getStatusInfo().getStatusCode(), response.getStatusInfo().getReasonPhrase()));
            }

            R entity = response.readEntity(responseEntity);
            if (entity == null) {
                throw new OBIBRequestException("Response Error: Entity is null.");
            }
            return entity;
        } catch (Exception e) {
            if (e instanceof OBIBRequestException) {
                throw e;
            }
            String[] msgArray = e.getMessage().split(":");
            throw new OBIBRequestException(String.format("Connection error: %s",
                    msgArray.length > 0 ? msgArray[msgArray.length - 1] : "Unknown"), e);
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
        searchCriteria.setClinicId(CDX_CLINIC_ID); // using clinic id from properties
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