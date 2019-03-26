package ca.uvic.leadlab.obibconnector.impl.receive.mock;

import ca.uvic.leadlab.obibconnector.facades.receive.IId;

public class IdJens implements IId {
    @Override
    public String getIdType() {
        return "HIN";
    }

    @Override
    public String getIdCode() {
        return "1234";
    }
}
