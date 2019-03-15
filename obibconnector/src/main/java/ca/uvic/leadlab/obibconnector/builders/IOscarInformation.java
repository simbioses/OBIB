package ca.uvic.leadlab.obibconnector.builders;
import ca.uvic.leadlab.obibconnector.models.CDXReturnEntities.CDResponses;
import ca.uvic.leadlab.obibconnector.models.OBIBConnectorEntities.* ;

/*
Interface declaration
 */
public interface IOscarInformation {

    //ClinicalDocument OscarInfo();
    //This is for Submit CDA
     CDResponses submitCDA(ClinicalDocument clinicalDocument, ClinicalCredentials clinicalCredentials);

     //for List Document
    CDResponses listDocument(ClinicalCredentials clinicalCredentials);

    //for search Document
    CDResponses searchDocument(ClinicalCredentials clinicalCredentials, SearchCriterials searchCriterials );

    //for get document
    CDResponses getDocument (SearchCriterials searchCriterials,ClinicalCredentials clinicalCredentials);

    //list Provider
    CDResponses listProviders(ClinicalCredentials clinicalCredentials);

    //list Clinics
    CDResponses listClinics(SearchCriterials searchCriterials);

}
