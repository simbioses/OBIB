package ca.uvic.leadlab.obibconnector.impl.support;

import ca.uvic.leadlab.obibconnector.facades.support.ISupport;

public class Support implements ISupport {
    @Override
    public boolean notifyError(String context, String trace) {
        return true;
    }
}
