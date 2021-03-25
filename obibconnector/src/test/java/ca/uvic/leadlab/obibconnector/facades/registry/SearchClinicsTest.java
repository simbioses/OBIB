package ca.uvic.leadlab.obibconnector.facades.registry;

import ca.uvic.leadlab.obibconnector.facades.FacadesBaseTest;
import ca.uvic.leadlab.obibconnector.impl.registry.SearchClinic;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SearchClinicsTest extends FacadesBaseTest {

    private ISearchClinic searchClinic = new SearchClinic(config);

    @Test
    public void testFindById() throws Exception {
        List<IClinic> clinics = searchClinic.findByID(clinicIdC);

        Assert.assertNotNull(clinics);
        Assert.assertFalse(clinics.isEmpty());

        System.out.println(mapper.writeValueAsString(clinics));
    }

    @Test
    public void testFindByNonexistentId() throws Exception {
        List<IClinic> clinics = searchClinic.findByID("__Wrong_ID");

        Assert.assertNotNull(clinics);
        Assert.assertTrue(clinics.isEmpty());
    }

    @Test
    public void testFindByName() throws Exception {
        List<IClinic> clinics = searchClinic.findByName("oscar");

        Assert.assertNotNull(clinics);
        Assert.assertFalse(clinics.isEmpty());

        System.out.println(mapper.writeValueAsString(clinics));
    }

    @Test
    public void testFindByAddress() throws Exception {
        List<IClinic> clinics = searchClinic.findByAddress("Kelowna");

        Assert.assertNotNull(clinics);
        Assert.assertFalse(clinics.isEmpty());

        System.out.println(mapper.writeValueAsString(clinics));
    }
}
