package ca.uvic.leadlab.obibconnector.impl.send;

import ca.uvic.leadlab.obibconnector.facades.datatypes.AddressType;
import ca.uvic.leadlab.obibconnector.facades.datatypes.NameType;
import ca.uvic.leadlab.obibconnector.facades.datatypes.TelcoType;
import ca.uvic.leadlab.obibconnector.facades.send.IPerson;
import ca.uvic.leadlab.obibconnector.facades.send.ISubmitDoc;
import ca.uvic.leadlab.obibconnector.models.common.Address;
import ca.uvic.leadlab.obibconnector.models.common.Name;
import ca.uvic.leadlab.obibconnector.models.common.Person;
import ca.uvic.leadlab.obibconnector.models.common.Telecom;

public abstract class PersonBuilder<P extends Person, R extends IPerson> extends DocElement implements IPerson<R> {

    final P person;

    PersonBuilder(ISubmitDoc submitDoc, P person) {
        super(submitDoc);
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
    public R address(AddressType type, String streetAddress, String city, String province, String postalCode, String country) {
        person.setAddress(new Address(type.label, streetAddress, city, province, postalCode, country));
        return (R) this;
    }

    @Override
    public R phone(TelcoType type, String number) {
        person.setTelecom(new Telecom(type.label, "tel", number));
        return (R) this;
    }

    @Override
    public R email(TelcoType type, String email) {
        person.setTelecom(new Telecom(type.label, "email", email));
        return (R) this;
    }
}
