package ca.uvic.leadlab.obibconnector;

import ca.uvic.leadlab.obibconnector.facades.Config;

public class Support {

    public Support(Config conf) {
    }

    public boolean notifyError(String context, String trace) {
        return true;
    }
}
