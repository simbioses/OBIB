
package ca.uvic.leadlab.models.oscar;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class RecipientDetail {

    @SerializedName("RecepientID")
    private String recepientID;

    public String getRecepientID() {
        return recepientID;
    }

    public void setRecepientID(String recepientID) {
        this.recepientID = recepientID;
    }

}
