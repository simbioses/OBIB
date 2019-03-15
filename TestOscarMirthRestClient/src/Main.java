import ca.uvic.leadlab.obibconnector.models.*;
import ca.uvic.leadlab.obibconnector.models.OBIBConnectorEntities.ClinicalCredentials;
import ca.uvic.leadlab.obibconnector.models.OBIBConnectorEntities.ClinicalDocument;
import ca.uvic.leadlab.obibconnector.models.OBIBConnectorEntities.SearchCriterials;
import ca.uvic.leadlab.oscarmirthrestclient.ProcessRestClient;
import ca.uvic.leadlab.oscarmirthrestclient.RestClient;
import ca.uvic.leadlab.oscarmirthrestclient.SetOscarReferralRequestInfo;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        SetOscarReferralRequestInfo setOscarReferralRequestInfo = new SetOscarReferralRequestInfo();
        ClinicalCredentials clinicalCredentials = new ClinicalCredentials();

        /*
         This is to populate the clinicaldocument object,
         this object should be populated with Oscar information

         Check SetOscarReferralRequestInfo Class for the sample of the Structure
        */
        ClinicalDocument clinicalDocument = setOscarReferralRequestInfo.OscarInfo();

        //set credentials
        // ClinicalCredentials clinicalCredentials = new ClinicalCredentials();
        clinicalCredentials.setUsername("cdxpostprod-otca");
        clinicalCredentials.setPassword("VMK31");
        clinicalCredentials.setLocationId("cdxpostprod-otca");

        //set search criteria
        SearchCriterials searchCriterials = new SearchCriterials();
        searchCriterials.setDocumentId("b3894465-eb45-e911-a96a-0050568c55a6");



        if(clinicalCredentials != null && clinicalCredentials != null){
            RestClient restClient = new RestClient();
            //Submit Document
            restClient.submitCDA(clinicalDocument,clinicalCredentials);

            //List Document
            restClient.listDocument(clinicalCredentials);

            //GetDocument
            restClient.getDocument(searchCriterials,clinicalCredentials);

        }

    }
}
