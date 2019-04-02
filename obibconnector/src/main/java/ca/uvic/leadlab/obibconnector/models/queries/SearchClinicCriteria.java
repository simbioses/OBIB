package ca.uvic.leadlab.obibconnector.models.queries;

public class SearchClinicCriteria {

    private String clinicId;
    private String clinicName;
    private String clinicAddress;

    public static SearchClinicCriteria byClinicId(String clinicId) {
        SearchClinicCriteria criteria = new SearchClinicCriteria();
        criteria.clinicId = clinicId;
        return criteria;
    }

    public static SearchClinicCriteria byClinicName(String clinicName) {
        SearchClinicCriteria criteria = new SearchClinicCriteria();
        criteria.clinicName = clinicName;
        return criteria;
    }

    public static SearchClinicCriteria byClinicAddress(String clinicAddress) {
        SearchClinicCriteria criteria = new SearchClinicCriteria();
        criteria.clinicAddress = clinicAddress;
        return criteria;
    }

    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getClinicAddress() {
        return clinicAddress;
    }

    public void setClinicAddress(String clinicAddress) {
        this.clinicAddress = clinicAddress;
    }
}
