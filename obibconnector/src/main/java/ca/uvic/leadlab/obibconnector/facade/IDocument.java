package ca.uvic.leadlab.obibconnector.facade;

public interface IDocument {

    String getTemplateID();
    String getTemplateName();           // return display name for template ID
    String getDocumentID();             // GUID of document
    String getLoincCode();              // LOINC code of document type
    String getLoingCodeDisplayName();   // display name for LOINC code
    String getTitle();

    IPerson getPatient();
    IPerson getAuthor();                // return name of human author (if present)
    String getAuthoringTime();          // return time of human authoring (if present)
    String getAuthorDevice();           // return name of authoring device (if present)
    String getAuthorDeviceTime();       // return time of device authoring (if present)

    String getCustodian();              // return name of custodian (that generated document)

    IProvider   getPrimaryRecipient();
    IProvider[] getSecondaryRecipients(); // if present

    IProvider   getOrderingProvider();
    IProvider   getFamilyProvider();

    String  getOrderID();               // the order ID for consultation notes is identical to their respective consultation requests
    String  getStatusCode();
    String  getObservationDate();

    String  getProcedureName();
    IProvider getProcedurePerformer();

    String getParentDocumentID();

    String getPatientEncounterID();

    String getAdmissionDate();
    String getDischargeDate();

    String getDischargeDisposition();

    IProvider getParticipatingProviders();

    String getContents();               // get the actual contents of the body of the document

    AttachmentType getAttachmentType();
    Byte[]  getAttachment();


}
