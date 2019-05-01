package ca.uvic.leadlab.obibconnector;

import ca.uvic.leadlab.obibconnector.facades.Config;

public class Support {

    public Support(Config conf) {
    }

    boolean notifyError(String context, String trace) {
        return true;
    };
}
