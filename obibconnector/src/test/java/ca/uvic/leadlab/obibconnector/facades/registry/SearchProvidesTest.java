package ca.uvic.leadlab.obibconnector.facades.registry;

import ca.uvic.leadlab.obibconnector.facades.FacadesBaseTest;
import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;
import ca.uvic.leadlab.obibconnector.impl.registry.SearchProviders;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SearchProvidesTest extends FacadesBaseTest {

    //@Test
    public void testFindByProviderId() throws Exception {
        ISearchProviders searchProviders = new SearchProviders(configClinicA);

        List<IProvider> providers = searchProviders.findByProviderID("93188");

        Assert.assertNotNull(providers);
    }

    //@Test(expected = OBIBException.class)
    public void testFindByProviderIdError() throws Exception {
        ISearchProviders searchProviders = new SearchProviders(configClinicA);

        List<IProvider> providers = searchProviders.findByProviderID("__Wrong_ID");

        //Assert.assertNull(providers);
    }
}
