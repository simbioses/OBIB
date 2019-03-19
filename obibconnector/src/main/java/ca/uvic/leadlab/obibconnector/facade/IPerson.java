package ca.uvic.leadlab.obibconnector.facade;

import java.util.Date;

public interface IPerson<R extends IPerson> extends IAnd {

    R id(String id);
    String getID();

    R name(NameType type, String firstName, String lastName, String prefix, String suffix);
    R name(NameType type, String firstName, String lastName);
    R name(String firstName, String lastName, String prefix, String suffix);
    R name(String firstName, String lastName);

    String getFirstName();
    String getLastName();


    R gender(Gender gender);
    Gender getGender();

    R birthday(Date date);

    R birthday(String year, String month, String day);
    Date getBirthdate();

    R address(AddressType type, String streetAddress, String city, String province, String postalCode, String country);
    String getStreetAddress();
    String getCity();
    String getProvince();
    String getPostalCode();
    String getCountry();

    R phone(PhoneType type, String number);

    PhoneType getPhoneType();
    String getPhoneNumber();

    R email(EmailType type, String email);

    PhoneType getEmailType();
    String getEmailAddress();

}
