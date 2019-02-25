
package ca.uvic.leadlab.models.oscar;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class OscarReferralRequestInfo {

    @SerializedName("AuthenticatorDetail")
    private List<AuthenticatorDetail> authenticatorDetail;
    @SerializedName("AuthorDetail")
    private AuthorDetail authorDetail;
    @SerializedName("BodyText")
    private String bodyText;
    @SerializedName("CustodianDetail")
    private CustodianDetail custodianDetail;
    @SerializedName("EffectiveTime")
    private String effectiveTime;
    @SerializedName("LocationDetail")
    private List<LocationDetail> locationDetail;
    @SerializedName("PatientDetail")
    private PatientDetail patientDetail;
    @SerializedName("ProviderDetail")
    private ProviderDetail providerDetail;
    @SerializedName("RecipientDetail")
    private List<RecipientDetail> recipientDetail;

    public List<AuthenticatorDetail> getAuthenticatorDetail() {
        return authenticatorDetail;
    }

    public void setAuthenticatorDetail(List<AuthenticatorDetail> authenticatorDetail) {
        this.authenticatorDetail = authenticatorDetail;
    }

    public AuthorDetail getAuthorDetail() {
        return authorDetail;
    }

    public void setAuthorDetail(AuthorDetail authorDetail) {
        this.authorDetail = authorDetail;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public CustodianDetail getCustodianDetail() {
        return custodianDetail;
    }

    public void setCustodianDetail(CustodianDetail custodianDetail) {
        this.custodianDetail = custodianDetail;
    }

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public List<LocationDetail> getLocationDetail() {
        return locationDetail;
    }

    public void setLocationDetail(List<LocationDetail> locationDetail) {
        this.locationDetail = locationDetail;
    }

    public PatientDetail getPatientDetail() {
        return patientDetail;
    }

    public void setPatientDetail(PatientDetail patientDetail) {
        this.patientDetail = patientDetail;
    }

    public ProviderDetail getProviderDetail() {
        return providerDetail;
    }

    public void setProviderDetail(ProviderDetail providerDetail) {
        this.providerDetail = providerDetail;
    }

    public List<RecipientDetail> getRecipientDetail() {
        return recipientDetail;
    }

    public void setRecipientDetail(List<RecipientDetail> recipientDetail) {
        this.recipientDetail = recipientDetail;
    }

}
