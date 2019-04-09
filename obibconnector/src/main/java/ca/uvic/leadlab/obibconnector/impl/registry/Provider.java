package ca.uvic.leadlab.obibconnector.impl.registry;

import ca.uvic.leadlab.obibconnector.facades.registry.IProvider;
import ca.uvic.leadlab.obibconnector.facades.receive.ITelco;
import ca.uvic.leadlab.obibconnector.impl.ImplHelper;
import ca.uvic.leadlab.obibconnector.impl.receive.Telco;
import ca.uvic.leadlab.obibconnector.models.common.Address;
import ca.uvic.leadlab.obibconnector.models.common.Telecom;

import java.util.ArrayList;
import java.util.List;

public class Provider implements IProvider {

    private final ca.uvic.leadlab.obibconnector.models.registry.Provider provider;

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

    private String clinicID;
    private String clinicName;

    public Provider(ca.uvic.leadlab.obibconnector.models.registry.Provider provider) {
        this.provider = provider;

        ID = ImplHelper.getDefaultProviderId(provider.getIds());

        if (provider.getName() != null) {
            firstName = provider.getName().getGiven().get(0);
            lastName = provider.getName().getFamily();
            prefix = provider.getName().getPrefix();
        }

        if (!provider.getAddresses().isEmpty()) {
            Address address = provider.getAddresses().get(0);
            streetAddress = address.getStreetAddress();
            city = address.getCity();
            province = address.getProvince();
            postalCode = address.getPostalCode();
            country = address.getCountry();
        }

        for (Telecom telecom : provider.getTelecoms()) {
            if (telecom.isPhone()) {
                phones.add(new Telco(telecom));
            } else {
                emails.add(new Telco(telecom));
            }
        }

        if (!provider.getClinics().isEmpty()) {
            if (!provider.getClinics().get(0).getIds().isEmpty()) {
                clinicID = provider.getClinics().get(0).getIds().get(0).getCode();
            }
            clinicName = provider.getClinics().get(0).getName();
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

    @Override
    public String getClinicID() {
        return clinicID;
    }

    @Override
    public String getClinicName() {
        return clinicName;
    }
}
