package ca.uvic.leadlab.obibconnector.models.common;

import ca.uvic.leadlab.obibconnector.models.document.ReceivedOrganization;

import java.util.Date;

public interface IPerson {

    void addId(String id);

    void addName(Name name);

    void setGender(String gender);

    void setDob(String date);

    void addAddress(Address address);

    void addTelecom(Telecom telecom);

    void setTime(Date time);

    void setReceivedOrganization(ReceivedOrganization receivedOrganization);

}
