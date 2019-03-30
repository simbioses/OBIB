package ca.uvic.leadlab.obibconnector.facades.receive;

import ca.uvic.leadlab.obibconnector.facades.datatypes.TelcoType;

public interface ITelco {
    TelcoType getTelcoType();
    String getAddress();
}
