
package ca.uvic.leadlab.models.oscar;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class PatientDetail {

    @SerializedName("PatientAddress")
    private PatientAddress patientAddress;
    @SerializedName("PatientDOB")
    private String patientDOB;
    @SerializedName("PatientGender")
    private String patientGender;
    @SerializedName("PatientID")
    private String patientID;
    @SerializedName("PatientName")
    private PatientName patientName;
    @SerializedName("PatientTelcom")
    private String patientTelcom;

    public PatientAddress getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(PatientAddress patientAddress) {
        this.patientAddress = patientAddress;
    }

    public String getPatientDOB() {
        return patientDOB;
    }

    public void setPatientDOB(String patientDOB) {
        this.patientDOB = patientDOB;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public PatientName getPatientName() {
        return patientName;
    }

    public void setPatientName(PatientName patientName) {
        this.patientName = patientName;
    }

    public String getPatientTelcom() {
        return patientTelcom;
    }

    public void setPatientTelcom(String patientTelcom) {
        this.patientTelcom = patientTelcom;
    }

}
