package ca.uvic.leadlab.obibconnector.impl.receive.mock;

import ca.uvic.leadlab.obibconnector.facades.datatypes.TelcoType;

public class TelcoEmailMe implements ca.uvic.leadlab.obibconnector.facades.receive.ITelco {

    @Override
    public TelcoType getTelcoType() {
        return TelcoType.WORK;
    }

    @Override
    public String getAddress() {
        return "me@me.com";
    }
}
