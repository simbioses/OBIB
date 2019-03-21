package ca.uvic.leadlab.obibconnector.impl.send;

import ca.uvic.leadlab.obibconnector.facades.datatypes.*;
import ca.uvic.leadlab.obibconnector.facades.send.*;
import ca.uvic.leadlab.obibconnector.models.OBIBConnectorEntities.Address;
import ca.uvic.leadlab.obibconnector.models.OBIBConnectorEntities.Name;
import ca.uvic.leadlab.obibconnector.models.OBIBConnectorEntities.Telecom;

import java.util.Date;

public class PersonBuilder<P extends ca.uvic.leadlab.obibconnector.models.OBIBConnectorEntities.IPerson, R extends IPerson> extends DocElement implements IPerson<R> {

    P person;

    PersonBuilder(ISubmitDoc submitDoc, P person) {
        this.submitDoc = submitDoc;
        this.person = person;
    }

    @Override
    public R id(String id) {
        person.setId(id);
        return (R) this;
    }

    @Override
    public R name(NameType type, String firstName, String lastName, String prefix, String suffix) {
        person.setName(new Name(type.label, firstName, lastName, prefix, suffix));
        return (R) this;
    }

    @Override
    public R name(NameType type, String firstName, String lastName) {
        person.setName(new Name(type.label, firstName, lastName, "", ""));
        return (R) this;
    }

    @Override
    public R name(String firstName, String lastName, String prefix, String suffix) {
        person.setName(new Name(NameType.LEGAL.label, firstName, lastName, prefix, suffix));
        return (R) this;
    }

    @Override
    public R name(String firstName, String lastName) {
        person.setName(new Name(NameType.LEGAL.label, firstName, lastName, "", ""));
        return (R) this;
    }

    @Override
    public R gender(Gender gender) {
        person.setGender(gender.label);
        return (R) this;
    }

    @Override
    public R birthday(Date date) {
        person.setDob(DATE_FORMATTER.format(date));
        return (R) this;
    }

    @Override
    public R birthday(String year, String month, String day) {
        person.setDob(year + "-" + month + "-" + day);
        return (R) this;
    }

    @Override
    public R address(AddressType type, String streetAddress, String city, String province, String postalCode, String country) {
        person.setAddress(new Address(type.label, streetAddress, city, province, postalCode, country));
        return (R) this;
    }

    @Override
    public R phone(PhoneType type, String number) {
        person.setTelecom(new Telecom(type.label, "tel", number));
        return (R) this;
    }

    @Override
    public R email(EmailType type, String email) {
        person.setTelecom(new Telecom(type.label, "email", email));
        return (R) this;
    }


}
