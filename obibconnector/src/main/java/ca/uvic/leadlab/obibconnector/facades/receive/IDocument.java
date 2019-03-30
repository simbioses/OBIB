package ca.uvic.leadlab.obibconnector.facades.receive;

import ca.uvic.leadlab.obibconnector.facades.registry.IProvider;

import java.util.Date;
import java.util.List;

public interface IDocument {

    String getTemplateID();
    String getTemplateName();           // return display name for template ID
    String getDocumentID();             // GUID of document
    String getLoincCode();              // LOINC code of document type
    String getLoincCodeDisplayName();   // display name for LOINC code
    String getTitle();

    IPatient getPatient();

    IProvider getAuthor();                // return name of human author (if present)
    Date    getAuthoringTime();          // return time of human authoring (if present)
    String  getAuthorDevice();           // return name of authoring device (if present)
    Date    getAuthorDeviceTime();       // return time of device authoring (if present)

    String getCustodianName();              // return name of custodian (that generated document)

    IProvider getPrimaryRecipient();
    List<IProvider> getSecondaryRecipients(); // if present

    IProvider getOrderingProvider();
    IProvider getFamilyProvider();

    String  getOrderID();               // the order ID for consultation notes is identical to their respective consultation requests
    String  getStatusCode();
    Date  getObservationDate();

    String  getProcedureName();
    IProvider getProcedurePerformer();

    String getParentDocumentID();

    String getPatientEncounterID();

    Date getAdmissionDate();
    Date getDischargeDate();

    String getDischargeDisposition();

    List<IProvider> getParticipatingProviders();

    String getContents();               // get the actual contents of the body of the document

    List<IAttachment> getAttachments();

}
