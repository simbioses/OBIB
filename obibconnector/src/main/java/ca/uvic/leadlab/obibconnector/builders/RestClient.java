package ca.uvic.leadlab.obibconnector.builders;
import ca.uvic.leadlab.obibconnector.models.CDXReturnEntities.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.google.gson.Gson;
import ca.uvic.leadlab.obibconnector.models.OBIBConnectorEntities.*;

import javax.ws.rs.core.MediaType;

public class RestClient implements IOscarInformation {

    private static final String REST_URI_SubmitCDA = "http://localhost:8081/SubmitCDA";
    private static final String REST_URI_LISTDOCUMENTS = "http://localhost:8081/ListDocuments";
    private static final String REST_URI_GETDOCUMENT = "http://localhost:8081/GetDocument";
    Client client = Client.create();


    private String Object2JSONConverterGSON(Object data) {
        Gson gson = new Gson();
        String json = gson.toJson(data);
        System.out.println(json);
        return json;
    }



    @Override
    public CDResponses submitCDA(ClinicalDocument clinicalDocument, ClinicalCredentials clinicalCredentials) {
        String output = "";
        WebResource webResource = client.resource(REST_URI_SubmitCDA);
        CDResponses cdResponses = new CDResponses();


        //ClientResponse response = resource.type(MediaType.APPLICATION_XML).put(ClientResponse.class, b1);
        try{
            ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
                    .header("username",clinicalCredentials.getUsername())
                    .header("password",clinicalCredentials.getPassword())
                    .header("locationId",clinicalCredentials.getLocationId())
                    .post(ClientResponse.class, Object2JSONConverterGSON(clinicalDocument));

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
                //cdResponse
            }

            System.out.println("Output from Server .... \n");
            output = response.getEntity(String.class);
            System.out.println(output);

        }
        catch (Exception e) {

            e.printStackTrace();

        }
        return cdResponses;
    }

    @Override
    public CDResponses listDocument(ClinicalCredentials clinicalCredentials) {
        String output = "";
        WebResource webResource = client.resource(REST_URI_LISTDOCUMENTS);
        CDResponses cdResponses = new CDResponses();
        try{
            ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
                    .header("username",clinicalCredentials.getUsername())
                    .header("password",clinicalCredentials.getPassword())
                    .header("locationId",clinicalCredentials.getLocationId())
                    .post(ClientResponse.class,Object2JSONConverterGSON(clinicalCredentials));

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
                //cdResponse
            }

            System.out.println("Output from Server .... \n");
            output = response.getEntity(String.class);
            System.out.println(output);

        }
        catch (Exception e) {

            e.printStackTrace();

        }
        return cdResponses;
    }

    @Override
    public CDResponses searchDocument(ClinicalCredentials clinicalCredentials, SearchCriterials searchCriterials) {
        return null;
    }

    @Override
    public CDResponses getDocument(SearchCriterials searchCriterials,ClinicalCredentials clinicalCredentials) {
        String output = "";
        WebResource webResource = client.resource(REST_URI_GETDOCUMENT);
        CDResponses cdResponses = new CDResponses();
        try{
            ClientResponse response = webResource.type(MediaType.APPLICATION_JSON) //"application/json"
                    .header("username",clinicalCredentials.getUsername())
                    .header("password",clinicalCredentials.getPassword())
                    .header("locationId",clinicalCredentials.getLocationId())
                    .post(ClientResponse.class,Object2JSONConverterGSON(searchCriterials));

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
                //cdResponse
            }

            System.out.println("Output from Server .... \n");
            output = response.getEntity(String.class);
            System.out.println(output);

        }
        catch (Exception e) {

            e.printStackTrace();

        }
        return cdResponses;

    }

    @Override
    public CDResponses listProviders(ClinicalCredentials clinicalCredentials) {
        return null;
    }

    @Override
    public CDResponses listClinics(SearchCriterials searchCriterials) {
        return null;
    }
}