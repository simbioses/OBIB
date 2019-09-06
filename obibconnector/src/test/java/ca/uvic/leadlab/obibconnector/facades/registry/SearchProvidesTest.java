package ca.uvic.leadlab.obibconnector.facades.registry;

import ca.uvic.leadlab.obibconnector.facades.FacadesBaseTest;
import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;
import ca.uvic.leadlab.obibconnector.impl.registry.SearchProviders;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SearchProvidesTest extends FacadesBaseTest {

    @Test
    public void testFindByProviderId() throws Exception {
        ISearchProviders searchProviders = new SearchProviders(config);

        List<IProvider> providers = searchProviders.findByProviderID("93188");

        Assert.assertNotNull(providers);

        System.out.println(mapper.writeValueAsString(providers));
    }

    @Test(expected = OBIBException.class)
    public void testFindByProviderIdError() throws Exception {
        try {
            ISearchProviders searchProviders = new SearchProviders(config);

            List<IProvider> providers = searchProviders.findByProviderID("__Wrong_ID");

            //Assert.assertNull(providers);
        } catch (OBIBException ex) {
            System.out.println("Message: " + ex.getMessage());
            System.out.println("OBIB Message: " + ex.getObibMessage());
            throw ex;
        }
    }

    @Test
    public void testFindByName() throws Exception {
        ISearchProviders searchProviders = new SearchProviders(config);

        List<IProvider> providers = searchProviders.findByName("Plisih");

        Assert.assertNotNull(providers);

        System.out.println(mapper.writeValueAsString(providers));
    }

    @Test
    public void testFindByClinicID() throws Exception {
        ISearchProviders searchProviders = new SearchProviders(config);

        List<IProvider> providers = searchProviders.findByClinicID(clinicIdA);

        Assert.assertNotNull(providers);

        System.out.println(mapper.writeValueAsString(providers));
    }

}
