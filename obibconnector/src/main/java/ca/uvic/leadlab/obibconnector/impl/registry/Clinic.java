package ca.uvic.leadlab.obibconnector.impl.registry;

import ca.uvic.leadlab.obibconnector.facades.registry.IClinic;
import ca.uvic.leadlab.obibconnector.facades.registry.IProvider;
import ca.uvic.leadlab.obibconnector.models.document.ReceivedOrganization;
import ca.uvic.leadlab.obibconnector.utils.OBIBConnectorHelper;

public class Clinic implements IClinic {

    private String ID;
    private String name;
    private String streetAddress;
    private String city;
    private String postalCode;
    private String province;

    private IProvider provider;

    public Clinic(ca.uvic.leadlab.obibconnector.models.registry.Clinic clinic) {
        ID = OBIBConnectorHelper.getDefaultClinicId(clinic.getIds());
        name = clinic.getName();
        if (clinic.getAddress() != null) {
            streetAddress = clinic.getAddress().getStreetAddress();
            city = clinic.getAddress().getCity();
            postalCode = clinic.getAddress().getPostalCode();
            province = clinic.getAddress().getProvince();
        }

        if (!clinic.getProviders().isEmpty()) {
            provider = new Provider(clinic.getProviders().get(0));
        }
    }

    public Clinic(ReceivedOrganization receivedOrganization) {
        ID = OBIBConnectorHelper.getDefaultClinicId(receivedOrganization.getIds());
        name = receivedOrganization.getName();
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public String getName() {
        return name;
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
    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public String getProvince() {
        return province;
    }

    @Override
    public IProvider getProvider() {
        return provider;
    }
}
