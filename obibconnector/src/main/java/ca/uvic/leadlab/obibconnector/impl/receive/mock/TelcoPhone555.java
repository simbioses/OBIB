package ca.uvic.leadlab.obibconnector.impl.receive.mock;

import ca.uvic.leadlab.obibconnector.facades.datatypes.TelcoType;

public class TelcoPhone555 implements ca.uvic.leadlab.obibconnector.facades.receive.ITelco {

    @Override
    public TelcoType getTelcoType() {
        return TelcoType.HOME;
    }

    @Override
    public String getAddress() {
        return "250-555-5555";
    }
}
