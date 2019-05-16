package ca.uvic.leadlab.obibconnector.facades.support;

import ca.uvic.leadlab.obibconnector.facades.FacadesBaseTest;
import ca.uvic.leadlab.obibconnector.impl.support.Support;
import org.junit.Test;

public class SupportTest extends FacadesBaseTest {

    @Test
    public void testNotifyError() throws Exception {
        ISupport support = new Support(configClinicA);

        support.notifyError("", "");
    }
}
