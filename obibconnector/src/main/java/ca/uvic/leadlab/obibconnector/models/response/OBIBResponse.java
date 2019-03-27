
package ca.uvic.leadlab.obibconnector.models.response;

public abstract class OBIBResponse {

    private String status;
    private String message;
    private AckDetail ackDetail;

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

    public AckDetail getAckDetail() {
        return ackDetail;
    }

    public void setAckDetail(AckDetail ackDetail) {
        this.ackDetail = ackDetail;
    }

}
