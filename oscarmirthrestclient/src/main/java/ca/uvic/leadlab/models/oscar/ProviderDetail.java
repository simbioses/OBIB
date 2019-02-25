
package ca.uvic.leadlab.models.oscar;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ProviderDetail {

    @SerializedName("ProviderAddress")
    private ProviderAddress providerAddress;
    @SerializedName("ProviderID")
    private String providerID;
    @SerializedName("ProviderName")
    private ProviderName providerName;
    @SerializedName("ProviderTelcom")
    private String providerTelcom;

    public ProviderAddress getProviderAddress() {
        return providerAddress;
    }

    public void setProviderAddress(ProviderAddress providerAddress) {
        this.providerAddress = providerAddress;
    }

    public String getProviderID() {
        return providerID;
    }

    public void setProviderID(String providerID) {
        this.providerID = providerID;
    }

    public ProviderName getProviderName() {
        return providerName;
    }

    public void setProviderName(ProviderName providerName) {
        this.providerName = providerName;
    }

    public String getProviderTelcom() {
        return providerTelcom;
    }

    public void setProviderTelcom(String providerTelcom) {
        this.providerTelcom = providerTelcom;
    }

}
