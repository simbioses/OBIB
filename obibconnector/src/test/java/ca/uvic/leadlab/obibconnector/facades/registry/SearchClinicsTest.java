package ca.uvic.leadlab.obibconnector.facades.registry;

import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;
import ca.uvic.leadlab.obibconnector.impl.registry.SearchClinic;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SearchClinicsTest {

    //@Test
    public void testFindById() throws Exception {
        ISearchClinic searchClinic = new SearchClinic("cdxpostprod-otca");

        List<IClinic> clinics = searchClinic.findByID("cdxpostprod-otca");

        Assert.assertNotNull(clinics);
    }

    //@Test(expected = OBIBException.class)
    public void testFindByIdError() throws Exception {
        ISearchClinic searchClinic = new SearchClinic("cdxpostprod-otca");

        List<IClinic> clinics = searchClinic.findByID("_XYXYXXYZ_");

        //Assert.assertNull(clinics);
    }
}
