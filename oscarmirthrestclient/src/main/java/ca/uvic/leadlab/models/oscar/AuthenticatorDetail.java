
package ca.uvic.leadlab.models.oscar;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class AuthenticatorDetail {

    @SerializedName("AuthenticatorID")
    private String authenticatorID;
    @SerializedName("AuthenticatorName")
    private String authenticatorName;

    public String getAuthenticatorID() {
        return authenticatorID;
    }

    public void setAuthenticatorID(String authenticatorID) {
        this.authenticatorID = authenticatorID;
    }

    public String getAuthenticatorName() {
        return authenticatorName;
    }

    public void setAuthenticatorName(String authenticatorName) {
        this.authenticatorName = authenticatorName;
    }

}
