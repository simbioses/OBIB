package ca.uvic.leadlab.obibconnector.impl.receive;

import ca.uvic.leadlab.obibconnector.facades.datatypes.DateFormatter;
import ca.uvic.leadlab.obibconnector.facades.datatypes.Gender;
import ca.uvic.leadlab.obibconnector.facades.receive.IPatient;
import ca.uvic.leadlab.obibconnector.facades.receive.ITelco;
import ca.uvic.leadlab.obibconnector.impl.ImplHelper;
import ca.uvic.leadlab.obibconnector.models.common.Address;
import ca.uvic.leadlab.obibconnector.models.common.Name;
import ca.uvic.leadlab.obibconnector.models.common.Telecom;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Patient implements IPatient {

    private final ca.uvic.leadlab.obibconnector.models.document.Patient patient;

    private String ID;

    private String firstName;
    private String lastName;
    private String prefix;

    private String streetAddress;
    private String city;
    private String province;
    private String postalCode;
    private String country;

    private List<ITelco> phones = new ArrayList<>();
    private List<ITelco> emails = new ArrayList<>();

    Patient(ca.uvic.leadlab.obibconnector.models.document.Patient patient) {
        this.patient = patient;

        ID = ImplHelper.getDefaultPatientId(patient.getIds());

        if (!patient.getNames().isEmpty()) {
            Name name = patient.getNames().get(0);
            firstName = !name.getGiven().isEmpty() ? name.getGiven().get(0) : "";
            lastName = name.getFamily();
            prefix = name.getPrefix();
        }

        if (!patient.getAddresses().isEmpty()) {
            Address address = patient.getAddresses().get(0);
            streetAddress = address.getStreetAddress();
            city = address.getCity();
            province = address.getProvince();
            postalCode = address.getPostalCode();
            country = address.getCountry();
        }

        for (Telecom telecom : patient.getTelecoms()) {
            if (telecom.isPhone()) {
                phones.add(new Telco(telecom));
            } else {
                emails.add(new Telco(telecom));
            }
        }
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getPrefix() {
        return prefix;
    }

    @Override
    public Gender getGender() {
        return Gender.fromLabel(patient.getGenderCode());
    }

    @Override
    public Date getBirthdate() {
        return DateFormatter.parseDate(patient.getBirthday());
    }

    @Override
    public String getStreetAddress() {
        return streetAddress;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public String getProvince() {
        return province;
    }

    @Override
    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public List<ITelco> getPhones() {
        return phones;
    }

    @Override
    public List<ITelco> getEmails() {
        return emails;
    }
}
