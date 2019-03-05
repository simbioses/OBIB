package ca.uvic.leadlab.oscarmirthrestclient;

//import javax.ws.rs.client.Client;
//import javax.ws.rs.core.Response;

import ca.uvic.leadlab.models.oscar.OscarReferralRequestInfo;
import ca.uvic.leadlab.models.submitcda.SubmitCDA;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

        import com.google.gson.Gson;

public class RestClient {

    private static final String REST_URI_SubmitCDA = "http://localhost:8081/SubmitCDA";
    Client client = Client.create();
    WebResource webResource = client.resource(REST_URI_SubmitCDA);
    //private Client client = ClientBuilder.newClient();



    public String createJsonSubmitCDA_NEW(SubmitCDA submitCDA) {
        String output = "";
        String testString= "{\r\n  \"PatientDetail\": {\r\n    \"PatientName\": {\r\n      \"firstname\": \"Jens\",\r\n      \"middlename\": \"D.\",\r\n      \"lastname\": \"Weber\"\r\n    },\r\n    \"PatientGender\": \"MALE\",\r\n    \"PatientDOB\": \"2019-02-08\",\r\n    \"PatientID\": \"34234324\",\r\n    \"PatientAddress\": {\r\n      \"streetaddress\": \"1775 Fort Street\",\r\n      \"city\": \"Victoria\",\r\n      \"province\": \"BC\",\r\n      \"postalcode\": \"V89017\",\r\n      \"country\": \"CA\"\r\n    },\r\n    \"PatientTelcom\": \"(250)721-8797\"\r\n  },\r\n  \"AuthorDetail\": {\r\n    \"AuthorName\": {\r\n      \"firstname\": \"Jens\",\r\n      \"middlename\": \"D.\",\r\n      \"lastname\": \"Weber\"\r\n    },\r\n    \"AuthorID\": \"2343242\",\r\n    \"AuthorAddress\": {\r\n      \"streetaddress\": \"1775 Fort Street\",\r\n      \"city\": \"Victoria\",\r\n      \"province\": \"BC\",\r\n      \"postalcode\": \"V89017\",\r\n      \"country\": \"CA\"\r\n    },\r\n    \"AuthorTelcom\": \"(250)721-8797\",\r\n    \"AuthorTime\": \"2019-02-08T19:00:31+00:00\"\r\n  },\r\n  \"EffectiveTime\": \"2019-02-08T19:00:31+00:00\",\r\n  \"CustodianDetail\": {\r\n    \"CustodianOrganizationName\": \"Ocean Spred Medical\",\r\n    \"CustodianOrganizationID\": \"3234242\"\r\n  },\r\n  \"ProviderDetail\": {\r\n    \"ProviderName\": {\r\n      \"firstname\": \"Jens\",\r\n      \"middlename\": \"D.\",\r\n      \"lastname\": \"Weber\"\r\n    },\r\n    \"ProviderID\": \"32342424\",\r\n    \"ProviderTelcom\": \"(250)721-8707\",\r\n    \"ProviderAddress\": {\r\n      \"streetaddress\": \"1775 Fort Street\",\r\n      \"city\": \"Victoria\",\r\n      \"province\": \"BC\",\r\n      \"postalcode\": \"V89017\",\r\n      \"country\": \"CA\"\r\n    }\r\n  },\r\n  \"BodyText\": \"This patient was refered to you\"\r\n}";

        try{
            ClientResponse response = webResource.type("application/json")
                    .post(ClientResponse.class, Object2JSONConverterGSON(submitCDA));

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            System.out.println("Output from Server .... \n");
            output = response.getEntity(String.class);
            System.out.println(output);

        }
         catch (Exception e) {

            e.printStackTrace();

        }
        return  output;
    }







    public String createJsonSubmitCDA_NEW_InterfaceImplementation(OscarReferralRequestInfo submitCDA, String username, String password, String locationID) {
        String output = "";
        //String testString= "{\r\n  \"PatientDetail\": {\r\n    \"PatientName\": {\r\n      \"firstname\": \"Jens\",\r\n      \"middlename\": \"D.\",\r\n      \"lastname\": \"Weber\"\r\n    },\r\n    \"PatientGender\": \"MALE\",\r\n    \"PatientDOB\": \"2019-02-08\",\r\n    \"PatientID\": \"34234324\",\r\n    \"PatientAddress\": {\r\n      \"streetaddress\": \"1775 Fort Street\",\r\n      \"city\": \"Victoria\",\r\n      \"province\": \"BC\",\r\n      \"postalcode\": \"V89017\",\r\n      \"country\": \"CA\"\r\n    },\r\n    \"PatientTelcom\": \"(250)721-8797\"\r\n  },\r\n  \"AuthorDetail\": {\r\n    \"AuthorName\": {\r\n      \"firstname\": \"Jens\",\r\n      \"middlename\": \"D.\",\r\n      \"lastname\": \"Weber\"\r\n    },\r\n    \"AuthorID\": \"2343242\",\r\n    \"AuthorAddress\": {\r\n      \"streetaddress\": \"1775 Fort Street\",\r\n      \"city\": \"Victoria\",\r\n      \"province\": \"BC\",\r\n      \"postalcode\": \"V89017\",\r\n      \"country\": \"CA\"\r\n    },\r\n    \"AuthorTelcom\": \"(250)721-8797\",\r\n    \"AuthorTime\": \"2019-02-08T19:00:31+00:00\"\r\n  },\r\n  \"EffectiveTime\": \"2019-02-08T19:00:31+00:00\",\r\n  \"CustodianDetail\": {\r\n    \"CustodianOrganizationName\": \"Ocean Spred Medical\",\r\n    \"CustodianOrganizationID\": \"3234242\"\r\n  },\r\n  \"ProviderDetail\": {\r\n    \"ProviderName\": {\r\n      \"firstname\": \"Jens\",\r\n      \"middlename\": \"D.\",\r\n      \"lastname\": \"Weber\"\r\n    },\r\n    \"ProviderID\": \"32342424\",\r\n    \"ProviderTelcom\": \"(250)721-8707\",\r\n    \"ProviderAddress\": {\r\n      \"streetaddress\": \"1775 Fort Street\",\r\n      \"city\": \"Victoria\",\r\n      \"province\": \"BC\",\r\n      \"postalcode\": \"V89017\",\r\n      \"country\": \"CA\"\r\n    }\r\n  },\r\n  \"BodyText\": \"This patient was refered to you\"\r\n}";

        try{
            ClientResponse response = webResource.type("application/json")
                    .header("username",username)
                    .header("password",password)
                    .header("locationid",locationID)
                    .post(ClientResponse.class, Object2JSONConverterGSON(submitCDA));

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            System.out.println("Output from Server .... \n");
            output = response.getEntity(String.class);
            System.out.println(output);

        }
        catch (Exception e) {

            e.printStackTrace();

        }
        return  output;
    }




    private String Object2JSONConverterGSON(SubmitCDA submitCDA) {
        Gson gson = new Gson();
        String json = gson.toJson(submitCDA);
        System.out.println(json);
        return json;
    }

    private String Object2JSONConverterGSON(OscarReferralRequestInfo submitCDA) {
        Gson gson = new Gson();
        String json = gson.toJson(submitCDA);
        System.out.println(json);
        return json;
    }
}