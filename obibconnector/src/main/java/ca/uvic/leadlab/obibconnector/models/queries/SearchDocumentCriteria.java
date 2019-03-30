package ca.uvic.leadlab.obibconnector.models.queries;

public class SearchDocumentCriteria {

    private String documentId;
    private String clinicId;
    private DateRange effectiveTime;
    private DateRange eventTime;

    public static SearchDocumentCriteria byDocumentId(String documentId) {
        SearchDocumentCriteria criteria = new SearchDocumentCriteria();
        criteria.documentId = documentId;
        return criteria;
    }

    public static SearchDocumentCriteria byClinicId(String clinicId) {
        SearchDocumentCriteria criteria = new SearchDocumentCriteria();
        criteria.clinicId = clinicId;
        return criteria;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }

    public DateRange getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(DateRange effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public DateRange getEventTime() {
        return eventTime;
    }

    public void setEventTime(DateRange eventTime) {
        this.eventTime = eventTime;
    }
}
