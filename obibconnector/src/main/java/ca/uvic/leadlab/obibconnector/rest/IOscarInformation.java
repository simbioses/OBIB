package ca.uvic.leadlab.obibconnector.rest;

import ca.uvic.leadlab.obibconnector.models.support.ErrorMessage;
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
    SubmitDocumentResponse submitCDA(ClinicalDocument clinicalDocument) throws OBIBRequestException;

    //for List Document
    ListDocumentsResponse listDocument() throws OBIBRequestException;

    //for search Document
    ListDocumentsResponse searchDocument(SearchDocumentCriteria searchCriteria) throws OBIBRequestException;

    //for get document
    ListDocumentsResponse getDocument(SearchDocumentCriteria searchCriteria) throws OBIBRequestException;

    //list Provider
    ListProvidersResponse listProviders(SearchProviderCriteria searchCriteria) throws OBIBRequestException;

    //list Clinics
    ListClinicsResponse listClinics(SearchClinicCriteria searchCriteria) throws OBIBRequestException;

    OBIBResponse notifyError(ErrorMessage error) throws OBIBRequestException;

}
