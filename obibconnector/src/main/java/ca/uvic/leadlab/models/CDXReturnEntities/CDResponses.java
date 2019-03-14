
package ca.uvic.leadlab.models.CDXReturnEntities;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class CDResponses {

    @Expose
    private AckDetail ackDetail;
    @Expose
    private String ackId;
    @Expose
    private AckQuery ackQuery;
    @Expose
    private String ackStatus;
    @Expose
    private String ackTime;
    @Expose
    private String messageId;

    public AckDetail getAckDetail() {
        return ackDetail;
    }

    public void setAckDetail(AckDetail ackDetail) {
        this.ackDetail = ackDetail;
    }

    public String getAckId() {
        return ackId;
    }

    public void setAckId(String ackId) {
        this.ackId = ackId;
    }

    public AckQuery getAckQuery() {
        return ackQuery;
    }

    public void setAckQuery(AckQuery ackQuery) {
        this.ackQuery = ackQuery;
    }

    public String getAckStatus() {
        return ackStatus;
    }

    public void setAckStatus(String ackStatus) {
        this.ackStatus = ackStatus;
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

}
