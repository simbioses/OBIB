package ca.uvic.leadlab.oscarmirthrestclient;
import ca.uvic.leadlab.models.OBIBConnectorEntities.* ;

/*
Interface declaration
 */
public interface IOscarInformation {

    //ClinicalDocument OscarInfo();
    //This is for Submit CDA
     CDResponse submitCDA(ClinicalDocument clinicalDocument, ClinicalCredentials clinicalCredentials);

     //for List Document
    CDResponse listDocument(ClinicalCredentials clinicalCredentials);

    //for search Document
    CDResponse searchDocument(ClinicalCredentials clinicalCredentials, SearchCriterials searchCriterials );

    //for get document
    CDResponse getDocument (SearchCriterials searchCriterials,ClinicalCredentials clinicalCredentials);

    //list Provider
    CDResponse listProviders(ClinicalCredentials clinicalCredentials);

    //list Clinics
    CDResponse listClinics(SearchCriterials searchCriterials);

}
