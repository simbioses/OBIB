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

    SubmitDocumentResponse submitCDA(ClinicalDocument clinicalDocument) throws OBIBRequestException;

    ListDocumentsResponse distributionStatus(SearchDocumentCriteria searchCriteria) throws OBIBRequestException;

    ListDocumentsResponse listDocument() throws OBIBRequestException;

    ListDocumentsResponse searchDocument(SearchDocumentCriteria searchCriteria) throws OBIBRequestException;

    ListDocumentsResponse getDocument(SearchDocumentCriteria searchCriteria) throws OBIBRequestException;

    ListProvidersResponse listProviders(SearchProviderCriteria searchCriteria) throws OBIBRequestException;

    ListClinicsResponse listClinics(SearchClinicCriteria searchCriteria) throws OBIBRequestException;

    OBIBResponse notifyError(ErrorMessage error) throws OBIBRequestException;

}
