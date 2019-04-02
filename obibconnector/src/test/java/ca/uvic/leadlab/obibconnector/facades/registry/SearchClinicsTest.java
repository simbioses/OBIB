package ca.uvic.leadlab.obibconnector.facades.registry;

import ca.uvic.leadlab.obibconnector.facades.FacadesBaseTest;
import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;
import ca.uvic.leadlab.obibconnector.impl.registry.SearchClinic;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SearchClinicsTest extends FacadesBaseTest {

    //@Test
    public void testFindById() throws Exception {
        ISearchClinic searchClinic = new SearchClinic(config);

        List<IClinic> clinics = searchClinic.findByID(clinicId);

        Assert.assertNotNull(clinics);
    }

    //@Test(expected = OBIBException.class)
    public void testFindByIdError() throws Exception {
        ISearchClinic searchClinic = new SearchClinic(config);

        List<IClinic> clinics = searchClinic.findByID("__Wrong_ID");

        //Assert.assertNull(clinics);
    }
}
