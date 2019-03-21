package ca.uvic.leadlab.obibconnector.impl.receive.mock;

import ca.uvic.leadlab.obibconnector.facades.receive.IProvider;

public class ProviderRaymond implements IProvider{
    @Override
    public String getFirstName() {
        return "Raymond";
    }

    @Override
    public String getLastName() {
        return "Rusk";
    }

    @Override
    public String getPrefix() {
        return "Dr";
    }

    @Override
    public String getID() {
        return "RRUSK";
    }

    @Override
    public String getClinicID() {
        return "34233";
    }

    @Override
    public String getClinicName() {
        return "Bleed Clinic";
    }
}
