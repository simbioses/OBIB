package ca.uvic.leadlab.obibconnector.facades.registry;

import ca.uvic.leadlab.obibconnector.facades.FacadesBaseTest;
import ca.uvic.leadlab.obibconnector.impl.registry.SearchProviders;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SearchProvidesTest extends FacadesBaseTest {

    private ISearchProviders searchProviders = new SearchProviders(config);

    @Test
    public void testFindByProviderId() throws Exception {
        List<IProvider> providers = searchProviders.findByProviderID("93188");

        Assert.assertNotNull(providers);
        Assert.assertFalse(providers.isEmpty());

        System.out.println(mapper.writeValueAsString(providers));
    }

    @Test
    public void testFindByNonexistentId() throws Exception {
        List<IProvider> providers = searchProviders.findByProviderID("__Wrong_ID");

        Assert.assertNotNull(providers);
        Assert.assertTrue(providers.isEmpty());
    }

    @Test
    public void testFindByName() throws Exception {
        List<IProvider> providers = searchProviders.findByName("Plisih");

        Assert.assertNotNull(providers);
        Assert.assertFalse(providers.isEmpty());

        System.out.println(mapper.writeValueAsString(providers));
    }

    @Test
    public void testFindByClinicID() throws Exception {
        List<IProvider> providers = searchProviders.findByClinicID(clinicIdC);

        Assert.assertNotNull(providers);
        Assert.assertFalse(providers.isEmpty());

        System.out.println(mapper.writeValueAsString(providers));
    }
}
