package ca.uvic.leadlab.obibconnector.impl.receive.mock;

import ca.uvic.leadlab.obibconnector.facades.receive.IId;

public class IdRaymond implements IId {
    @Override
    public String getIdType() {
        return "MSP";
    }

    @Override
    public String getIdCode() {
        return "098765";
    }
}
