
package ca.uvic.leadlab.obibconnector.models.OBIBConnectorEntities;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Address {

    @Expose
    private String city;
    @Expose
    private String country;
    @Expose
    private String postalCode;
    @Expose
    private String province;
    @Expose
    private String streetAddress;
    @Expose
    private String use;

    public Address() {
    }

    public Address(String use, String streetAddress, String city, String province, String postalCode, String country) {
        this.use = use;
        this.streetAddress = streetAddress;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.country = country;
    }

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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

}
