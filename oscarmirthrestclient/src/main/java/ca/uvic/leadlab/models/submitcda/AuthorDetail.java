
package ca.uvic.leadlab.models.submitcda;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class AuthorDetail {

    @SerializedName("AuthorAddress")
    private AuthorAddress authorAddress;
    @SerializedName("AuthorID")
    private String authorID;
    @SerializedName("AuthorName")
    private AuthorName authorName;
    @SerializedName("AuthorTelcom")
    private String authorTelcom;
    @SerializedName("AuthorTime")
    private String authorTime;

    public AuthorAddress getAuthorAddress() {
        return authorAddress;
    }

    public void setAuthorAddress(AuthorAddress authorAddress) {
        this.authorAddress = authorAddress;
    }

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public AuthorName getAuthorName() {
        return authorName;
    }

    public void setAuthorName(AuthorName authorName) {
        this.authorName = authorName;
    }

    public String getAuthorTelcom() {
        return authorTelcom;
    }

    public void setAuthorTelcom(String authorTelcom) {
        this.authorTelcom = authorTelcom;
    }

    public String getAuthorTime() {
        return authorTime;
    }

    public void setAuthorTime(String authorTime) {
        this.authorTime = authorTime;
    }

}
