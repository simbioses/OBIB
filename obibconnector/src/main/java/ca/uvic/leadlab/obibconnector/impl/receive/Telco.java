package ca.uvic.leadlab.obibconnector.impl.receive;

import ca.uvic.leadlab.obibconnector.facades.datatypes.TelcoType;
import ca.uvic.leadlab.obibconnector.facades.receive.ITelco;
import ca.uvic.leadlab.obibconnector.models.common.Telecom;

public class Telco implements ITelco {

    private final Telecom telecom;

    public Telco(Telecom telecom) {
        this.telecom = telecom;
    }

    @Override
    public TelcoType getTelcoType() {
        return TelcoType.fromLabel(telecom.getUse());
    }

    @Override
    public String getAddress() {
        return telecom.getValue();
    }
}
