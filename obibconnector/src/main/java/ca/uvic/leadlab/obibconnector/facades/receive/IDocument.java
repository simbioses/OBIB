package ca.uvic.leadlab.obibconnector.facades.receive;

import ca.uvic.leadlab.obibconnector.facades.datatypes.DocumentStatus;
import ca.uvic.leadlab.obibconnector.facades.registry.IProvider;

import java.util.Date;
import java.util.List;

public interface IDocument {

    String getTemplateID();
    String getTemplateName();           // return display name for template ID
    String getDocumentID();             // GUID of document
    int    getVersion();
    String getSetId();
    String getInFulFillmentOfId();
    String getLoincCode();              // LOINC code of document type
    String getLoincCodeDisplayName();   // display name for LOINC code
    String getTitle();

    IPatient getPatient();

    IProvider getAuthor();                // return name of human author (if present)
    Date    getAuthoringTime();          // return time of human authoring (if present)
    String  getAuthorDevice();           // return name of authoring device (if present)
    Date    getAuthorDeviceTime();       // return time of device authoring (if present)
    Date    getEffectiveTime();
    Date getReceivedTime();

    String getCustodianName();              // return name of custodian (that generated document)

    IProvider getPrimaryRecipient();
    List<IProvider> getPrimaryRecipients();
    List<IProvider> getSecondaryRecipients(); // if present

    IProvider getOrderingProvider();
    IProvider getFamilyProvider();

    String  getOrderID();               // TODO implement. the order ID for consultation notes is identical to their respective consultation requests
    DocumentStatus getStatusCode();
    Date  getObservationDate();         // TODO implement.

    String  getProcedureName();         // TODO implement.
    IProvider getProcedurePerformer();  // TODO implement.

    String getParentDocumentID();

    String getPatientEncounterID();     // TODO implement.

    Date getAdmissionDate();            // TODO implement.
    Date getDischargeDate();            // TODO implement.

    String getDischargeDisposition();   // TODO implement.

    List<IProvider> getParticipatingProviders();

    String getContents();               // get the xml of the document

    List<IAttachment> getAttachments();

    List<IDistributionStatus> getDistributionStatus();

}
