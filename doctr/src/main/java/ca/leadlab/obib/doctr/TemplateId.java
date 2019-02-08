package ca.leadlab.obib.doctr;

/**
 * CDX Template IDs
 */
public enum TemplateId {

    UNSTRUCTURED_REPORT("Unstructured Report", "2.16.840.1.113883.10.20.19"),
    CONSULTATION_NOTE("Consultation Note","2.16.840.1.113883.10.20.4"),
    DISCHARGE_SUMMARY("Discharge Summary", "2.16.840.1.113883.3.51.60.2.4"),
    HISTORY_AND_PHYSICAL_NOTE("History and Physical Note","2.16.840.1.113883.10.20.2"),
    OPERATIVE_NOTE("Operative Note","2.16.840.1.113883.10.20.7"),
    PROCEDURE_NOTE("Procedure Note","2.16.840.1.113883.3.51.60.2.3"),
    PROGRESS_NOTE("Progress Note","2.16.840.1.113883.10.20.21"),
    ANATOMIC_PATHOLOGY_REPORT("Anatomic Pathology Report","2.16.840.1.113883.3.51.60.2.2"),
    LAB_REPORT("Lab Report","2.16.840.1.113883.3.51.60.2.1"),
    E2E_UNSTRUCTURED_REFERRAL("e2e Unstructured Referral","2.16.840.1.113883.3.1818.10.1.5"),
    E2E_UNSTRUCTURED_DOCUMENT("e2e Unstructured Document","2.16.840.1.113883.3.1818.10.1.4"),
    E2E_GENERIC_EPISODIC_DOCUMENT("e2e Generic Episodic Document","2.16.840.1.113883.3.1818.10.1.2"),
    E2E_PATIENT_CHART_TRANSFER("e2e Patient Chart Transfer","2.16.840.1.113883.3.1818.10.1.3"),
    E2E_EMR_CONVERSION_TEMPLATE("e2e EMR Conversion Template","2.16.840.1.113883.3.1818.10.1.1"),
    ADMIT_NOTIFICATION("Admit Notification","2.16.840.1.113883.3.51.60.2.7"),
    DISCHARGE_NOTIFICATION("Discharge Notification","2.16.840.1.113883.3.51.60.2.6");

    private String title;
    private String oid;

    TemplateId(String title, String oid) {
        this.title = title;
        this.oid = oid;
    }

    public String title() {
        return title;
    }

    public String oid() {
        return oid;
    }
}
