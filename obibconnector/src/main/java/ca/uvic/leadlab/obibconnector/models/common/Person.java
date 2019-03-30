package ca.uvic.leadlab.obibconnector.models.common;

public abstract class Person {

    public abstract void setId(String id);

    public abstract void setName(Name name);

    public abstract void setAddress(Address address);

    public abstract void setTelecom(Telecom telecom);
}
