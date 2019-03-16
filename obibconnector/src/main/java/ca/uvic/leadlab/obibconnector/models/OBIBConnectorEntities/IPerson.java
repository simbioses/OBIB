package ca.uvic.leadlab.obibconnector.models.OBIBConnectorEntities;

public interface IPerson {

    void setId(String id);

    void setName(Name name);

    void setGender(String gender);

    void setDob(String date);

    void setAddress(Address address);

    void setTelecom(Telecom telecom);

    void setTime(String time);

    void setReceivedOrganization(ReceivedOrganization receivedOrganization);

}
