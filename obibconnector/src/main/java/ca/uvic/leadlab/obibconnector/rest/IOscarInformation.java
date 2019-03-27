package ca.uvic.leadlab.obibconnector.rest;

import ca.uvic.leadlab.obibconnector.models.document.ClinicalDocument;
import ca.uvic.leadlab.obibconnector.models.queries.SearchClinicCriteria;
import ca.uvic.leadlab.obibconnector.models.queries.SearchDocumentCriteria;
import ca.uvic.leadlab.obibconnector.models.queries.SearchProviderCriteria;
import ca.uvic.leadlab.obibconnector.models.response.*;

/*
Interface declaration
 */
public interface IOscarInformation {

    //ClinicalDocument OscarInfo();
    //This is for Submit CDA
    SubmitDocumentResponse submitCDA(ClinicalDocument clinicalDocument);

    //for List Document
    ListDocumentsResponse listDocument();

    //for search Document
    ListDocumentsResponse searchDocument(SearchDocumentCriteria searchCriteria);

    //for get document
    ListDocumentsResponse getDocument(SearchDocumentCriteria searchCriteria);

    //list Provider
    ListProvidersResponse listProviders(SearchProviderCriteria searchCriteria);

    //list Clinics
    ListClinicsResponse listClinics(SearchClinicCriteria searchCriteria);

}
