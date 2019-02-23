import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class SubmitCDA {

    @SerializedName("AuthorDetail")
    private AuthorDetail authorDetail;
    @SerializedName("BodyText")
    private String bodyText;
    @SerializedName("CustodianDetail")
    private CustodianDetail custodianDetail;
    @SerializedName("EffectiveTime")
    private String effectiveTime;
    @SerializedName("PatientDetail")
    private PatientDetail patientDetail;
    @SerializedName("ProviderDetail")
    private ProviderDetail providerDetail;

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

}
