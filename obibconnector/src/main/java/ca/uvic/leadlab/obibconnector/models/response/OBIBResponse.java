
package ca.uvic.leadlab.obibconnector.models.response;

import java.util.Map;

public class OBIBResponse {

    private String obibVersion;
    private String status;
    private String message;
    private AckDetail ackDetail;
    private Map<String, String> obibErrors;

    public boolean isOK() {
        return "SUCCESS".equalsIgnoreCase(status);
    }

    public String getObibVersion() {
        return obibVersion;
    }

    public void setObibVersion(String obibVersion) {
        this.obibVersion = obibVersion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getObibErrors() {
        return obibErrors;
    }

    public void setObibErrors(Map<String, String> obibErrors) {
        this.obibErrors = obibErrors;
    }

    public AckDetail getAckDetail() {
        return ackDetail;
    }

    public void setAckDetail(AckDetail ackDetail) {
        this.ackDetail = ackDetail;
    }

}
