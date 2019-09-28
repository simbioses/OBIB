package ca.uvic.leadlab.obibconnector.facades.support;

import ca.uvic.leadlab.obibconnector.facades.FacadesBaseTest;
import ca.uvic.leadlab.obibconnector.impl.support.Support;
import org.junit.Test;

import java.util.Date;

public class SupportTest extends FacadesBaseTest {

    private ISupport support = new Support(config);

    @Test
    public void testNotifyError() throws Exception {
        support.notifyError("Error message at " + new Date(), new Exception("Exception").toString());
    }
}
