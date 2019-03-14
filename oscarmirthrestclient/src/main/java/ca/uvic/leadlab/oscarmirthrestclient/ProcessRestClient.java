package ca.uvic.leadlab.oscarmirthrestclient;
import ca.uvic.leadlab.models.OBIBConnectorEntities.*;

public class ProcessRestClient
{
  public static void main(String[] argv) throws Exception {
    // Please, do not remove this line from file template, here invocation of web service will be inserted  

  //ToDo Get the parsed data from  Oscar


      UsedInterfaceImplementation();
  }

  /*
  Using the Interface Class

   */
    private static void UsedInterfaceImplementation(){

        SetOscarReferralRequestInfo setOscarReferralRequestInfo = new SetOscarReferralRequestInfo();
        ClinicalCredentials clinicalCredentials = new ClinicalCredentials();

        ClinicalDocument clinicalDocument = setOscarReferralRequestInfo.OscarInfo();  // This is to populate the clinicaldocument object

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
