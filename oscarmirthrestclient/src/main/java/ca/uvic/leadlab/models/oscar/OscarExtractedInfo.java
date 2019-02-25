
package ca.uvic.leadlab.models.oscar;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
 /*
    This contains all the information meant to be extracted from OSCAR EMR
     */
public class OscarExtractedInfo {

    @SerializedName("OscarExtractedInfo")
    private OscarExtractedInfo mOscarExtractedInfo;
    @SerializedName("PatientFirstName")
    private String mPatientFirstName;
    @SerializedName("PatientLastName")
    private String mPatientLastName;
    @SerializedName("PatientMiddleName")
    private String mPatientMiddleName;

    public OscarExtractedInfo getOscarExtractedInfo() {
        return mOscarExtractedInfo;
    }

    public void setOscarExtractedInfo(OscarExtractedInfo oscarExtractedInfo) {
        mOscarExtractedInfo = oscarExtractedInfo;
    }

    public String getPatientFirstName() {
        return mPatientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        mPatientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return mPatientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        mPatientLastName = patientLastName;
    }

    public String getPatientMiddleName() {
        return mPatientMiddleName;
    }

    public void setPatientMiddleName(String patientMiddleName) {
        mPatientMiddleName = patientMiddleName;
    }

}
