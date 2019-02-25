
package ca.uvic.leadlab.models.oscar;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class AuthorDetail {

    @SerializedName("AuthorAddress")
    private List<AuthorAddress> authorAddress;
    @SerializedName("AuthorID")
    private List<String> authorID;
    @SerializedName("AuthorName")
    private List<AuthorName> authorName;
    @SerializedName("AuthorTelcom")
    private List<String> authorTelcom;
    @SerializedName("AuthorTime")
    private List<String> authorTime;

    public List<AuthorAddress> getAuthorAddress() {
        return authorAddress;
    }

    public void setAuthorAddress(List<AuthorAddress> authorAddress) {
        this.authorAddress = authorAddress;
    }

    public List<String> getAuthorID() {
        return authorID;
    }

    public void setAuthorID(List<String> authorID) {
        this.authorID = authorID;
    }

    public List<AuthorName> getAuthorName() {
        return authorName;
    }

    public void setAuthorName(List<AuthorName> authorName) {
        this.authorName = authorName;
    }

    public List<String> getAuthorTelcom() {
        return authorTelcom;
    }

    public void setAuthorTelcom(List<String> authorTelcom) {
        this.authorTelcom = authorTelcom;
    }

    public List<String> getAuthorTime() {
        return authorTime;
    }

    public void setAuthorTime(List<String> authorTime) {
        this.authorTime = authorTime;
    }

}
