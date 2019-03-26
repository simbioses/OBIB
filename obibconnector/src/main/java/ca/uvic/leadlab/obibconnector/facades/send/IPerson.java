package ca.uvic.leadlab.obibconnector.facades.send;

import ca.uvic.leadlab.obibconnector.facades.datatypes.*;

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

    R phone(TelcoType type, String number);

    R email(TelcoType type, String email);

}
