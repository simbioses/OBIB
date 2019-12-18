package ca.uvic.leadlab.obibconnector.impl.registry;

import ca.uvic.leadlab.obibconnector.facades.registry.IClinic;
import ca.uvic.leadlab.obibconnector.facades.registry.IProvider;
import ca.uvic.leadlab.obibconnector.facades.registry.ITemplate;
import ca.uvic.leadlab.obibconnector.models.document.ReceivedOrganization;
import ca.uvic.leadlab.obibconnector.utils.OBIBConnectorHelper;

import java.util.ArrayList;
import java.util.List;

public class Clinic implements IClinic {

    private String ID;
    private String name;
    private String streetAddress;
    private String city;
    private String postalCode;
    private String province;

    private IProvider provider;
    private List<IProvider> providers;
    private List<ITemplate> templates;

    public Clinic(ca.uvic.leadlab.obibconnector.models.registry.Clinic clinic) {
        ID = OBIBConnectorHelper.getDefaultClinicId(clinic.getIds());
        name = clinic.getName();
        if (clinic.getAddress() != null) {
            streetAddress = clinic.getAddress().getStreetAddress();
            city = clinic.getAddress().getCity();
            postalCode = clinic.getAddress().getPostalCode();
            province = clinic.getAddress().getProvince();
        }

        providers = new ArrayList<>();
        if (!clinic.getProviders().isEmpty()) {
            provider = new Provider(clinic.getProviders().get(0));
            for (ca.uvic.leadlab.obibconnector.models.registry.Provider provider : clinic.getProviders()) {
                providers.add(new Provider(provider));
            }
        }

        templates = new ArrayList<>();
        if (!clinic.getTemplates().isEmpty()) {
            for (ca.uvic.leadlab.obibconnector.models.common.Template template : clinic.getTemplates()) {
                templates.add(new Template(template));
            }
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

    @Override
    public List<IProvider> getProviders() {
        return providers;
    }

    @Override
    public List<ITemplate> getTemplates() {
        return templates;
    }
}
