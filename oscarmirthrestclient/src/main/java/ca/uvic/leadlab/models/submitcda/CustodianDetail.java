
package ca.uvic.leadlab.models.submitcda;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class CustodianDetail {

    @SerializedName("CustodianOrganizationID")
    private String custodianOrganizationID;
    @SerializedName("CustodianOrganizationName")
    private String custodianOrganizationName;

    public String getCustodianOrganizationID() {
        return custodianOrganizationID;
    }

    public void setCustodianOrganizationID(String custodianOrganizationID) {
        this.custodianOrganizationID = custodianOrganizationID;
    }

    public String getCustodianOrganizationName() {
        return custodianOrganizationName;
    }

    public void setCustodianOrganizationName(String custodianOrganizationName) {
        this.custodianOrganizationName = custodianOrganizationName;
    }

}
