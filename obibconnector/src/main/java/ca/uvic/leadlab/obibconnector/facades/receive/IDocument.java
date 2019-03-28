package ca.uvic.leadlab.obibconnector.facades.receive;

import ca.uvic.leadlab.obibconnector.facades.datatypes.AttachmentType;
import ca.uvic.leadlab.obibconnector.facades.receive.IPerson;

import java.util.Date;
import java.util.List;

public interface IDocument {

    String getTemplateID();
    String getTemplateName();           // return display name for template ID
    String getDocumentID();             // GUID of document
    String getLoincCode();              // LOINC code of document type
    String getLoingCodeDisplayName();   // display name for LOINC code
    String getTitle();

    IPerson getPatient();
    IPerson getAuthor();                // return name of human author (if present)
    Date    getAuthoringTime();          // return time of human authoring (if present)
    String  getAuthorDevice();           // return name of authoring device (if present)
    Date    getAuthorDeviceTime();       // return time of device authoring (if present)

    String getCustodian();              // return name of custodian (that generated document)

    IPerson getPrimaryRecipient();
    List<IPerson> getSecondaryRecipients(); // if present

    IPerson   getOrderingProvider();
    IPerson   getFamilyProvider();

    String  getOrderID();               // the order ID for consultation notes is identical to their respective consultation requests
    String  getStatusCode();
    Date  getObservationDate();

    String  getProcedureName();
    IPerson getProcedurePerformer();

    String getParentDocumentID();

    String getPatientEncounterID();

    Date getAdmissionDate();
    Date getDischargeDate();

    String getDischargeDisposition();

    List<IPerson> getParticipatingProviders();

    String getContents();               // get the actual contents of the body of the document

    List<IAttachment> getAttachments();

}
