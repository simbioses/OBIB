package ca.uvic.leadlab.obibconnector.facade;

import java.util.Date;

public interface IPerson<R extends IPerson> extends IAnd {

    R id(String id);

    R name(NameType type, String firstName, String lastName, String prefix, String suffix);

    R name(NameType type, String firstName, String lastName);

    R name(String firstName, String lastName, String prefix, String suffix);

    R name(String firstName, String lastName);

    R gender(Gender gender);

    R birthday(Date date);

    R birthday(String year, String month, String day);

    R address(AddressType type, String streetAddress, String city, String province, String postalCode, String country);

    R phone(PhoneType type, String number);

    R email(EmailType type, String email);

}
