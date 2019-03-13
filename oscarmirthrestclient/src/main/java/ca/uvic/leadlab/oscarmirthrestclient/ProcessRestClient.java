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

        ClinicalDocument clinicalDocument = setOscarReferralRequestInfo.OscarInfo();




        //set credentials
       // ClinicalCredentials clinicalCredentials = new ClinicalCredentials();
        clinicalCredentials.setUsername("cdxpostprod-otca");
        clinicalCredentials.setPassword("VMK31");
        clinicalCredentials.setLocationId("cdxpostprod-obctc");



        RestClient restClient = new RestClient();
        restClient.submitCDA(clinicalDocument,clinicalCredentials);
        //restClient.createJsonSubmitCDA_NEW_InterfaceImplementation(oscarReferralRequestInfo,"cdxpostprod-otca","VMK31","cdxpostprod-obctc");
    }

}
