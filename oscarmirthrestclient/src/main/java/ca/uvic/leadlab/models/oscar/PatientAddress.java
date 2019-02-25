
package ca.uvic.leadlab.models.oscar;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class PatientAddress {

    @Expose
    private String city;
    @Expose
    private String country;
    @Expose
    private String postalcode;
    @Expose
    private String province;
    @Expose
    private String streetaddress;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStreetaddress() {
        return streetaddress;
    }

    public void setStreetaddress(String streetaddress) {
        this.streetaddress = streetaddress;
    }

}
