package ca.uvic.leadlab.obibconnector.models.document;

public class ServiceEvent {

    private String effectiveTime;
    private String statusCode;

    public ServiceEvent() {
    }

    public ServiceEvent(String effectiveTime, String statusCode) {
        this.effectiveTime = effectiveTime;
        this.statusCode = statusCode;
    }

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

}
