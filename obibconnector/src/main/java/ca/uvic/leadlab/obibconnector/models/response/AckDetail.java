package ca.uvic.leadlab.obibconnector.models.response;

import java.util.Date;

public class AckDetail {

    private String ackId;
    private String ackTime;
    private String messageId;
    private String status;

    public String getAckId() {
        return ackId;
    }

    public void setAckId(String ackId) {
        this.ackId = ackId;
    }

    public String getAckTime() {
        return ackTime;
    }

    public void setAckTime(String ackTime) {
        this.ackTime = ackTime;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
