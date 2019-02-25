package ca.uvic.leadlab.oscarmirthrestclient;
import ca.uvic.leadlab.models.oscar.OscarExtractedInfo;
import ca.uvic.leadlab.models.oscar.OscarReferralRequestInfo;
import ca.uvic.leadlab.models.submitcda.*;

public class ProcessRestClient
{
  public static void main(String[] argv) throws Exception {
    // Please, do not remove this line from file template, here invocation of web service will be inserted  

  //ToDo Get the parsed data from  Oscar

      //SubmitCDACreator submitCDACreator = new SubmitCDACreator();
      //RestClient restClient = new RestClient();
      //restClient.createJsonSubmitCDA_NEW(submitCDACreator.setSubmitCDAInfo());


      // Testing the Interface Implementation
      //SubmitCDACreator submitCDACreator = new SubmitCDACreator();
      //RestClient restClient = new RestClient();
      //restClient.createJsonSubmitCDA_NEW(submitCDACreator.setSubmitCDAInfo());

      UsedInterfaceImplementation();
  }

  /*
  Using the Interface Class

   */
    private static void UsedInterfaceImplementation(){
        // Set OscarExtractedInfo
        OscarExtractedInfo oscarExtractedInfo = new OscarExtractedInfo();
        oscarExtractedInfo.setPatientFirstName("Oluwaseun");
        oscarExtractedInfo.setPatientLastName("Alani");
        oscarExtractedInfo.setPatientMiddleName("Hannah");
       IOscarInformation iOscarInformation;
       iOscarInformation = new SetOscarReferralRequestInfo(oscarExtractedInfo);

        OscarReferralRequestInfo oscarReferralRequestInfo = iOscarInformation.OscarInfo();

        RestClient restClient = new RestClient();
        restClient.createJsonSubmitCDA_NEW_InterfaceImplementation(oscarReferralRequestInfo);
    }

}
