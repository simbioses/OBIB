package ca.uvic.leadlab.oscarmirthrestclient;
import ca.uvic.leadlab.models.submitcda.*;

public class ProcessRestClient
{
  public static void main(String[] argv) throws Exception {
    // Please, do not remove this line from file template, here invocation of web service will be inserted  

  //ToDo Get the parsed data from  Oscar

    //SubmitCDA submitCDA =  setSubmitCDARecords(); // populate the submitcda object

    SubmitCDACreator submitCDACreator = new SubmitCDACreator();

    RestClient restClient = new RestClient();
    restClient.createJsonSubmitCDA_NEW(submitCDACreator.setSubmitCDAInfo());
  }

  /*
  This return the populated object for Submit CDA
   */

}
