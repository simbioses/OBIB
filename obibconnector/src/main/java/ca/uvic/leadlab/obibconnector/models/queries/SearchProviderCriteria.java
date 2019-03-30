package ca.uvic.leadlab.obibconnector.models.queries;

public class SearchProviderCriteria {

    private String clinicId;
    private String providerId;
    private String providerName;

    public static SearchProviderCriteria byProviderId(String providerId) {
        SearchProviderCriteria criteria = new SearchProviderCriteria();
        criteria.providerId = providerId;
        return criteria;
    }

    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }
}
