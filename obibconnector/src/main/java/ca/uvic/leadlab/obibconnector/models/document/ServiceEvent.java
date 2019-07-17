package ca.uvic.leadlab.obibconnector.models.document;

import java.util.ArrayList;
import java.util.List;

public class ServiceEvent {

    private String effectiveTime;
    private String statusCode;
    private List<Performer> performers = new ArrayList<>();

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

    public boolean haveStatus() {
        return statusCode != null && !statusCode.isEmpty();
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public List<Performer> getPerformers() {
        return performers;
    }

    public void setPerformers(List<Performer> performers) {
        this.performers = performers;
    }
}
