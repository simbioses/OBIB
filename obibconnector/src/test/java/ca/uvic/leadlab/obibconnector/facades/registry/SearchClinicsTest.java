package ca.uvic.leadlab.obibconnector.facades.registry;

import ca.uvic.leadlab.obibconnector.facades.FacadesBaseTest;
import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;
import ca.uvic.leadlab.obibconnector.impl.registry.SearchClinic;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SearchClinicsTest extends FacadesBaseTest {

    @Test
    public void testFindById() throws Exception {
        ISearchClinic searchClinic = new SearchClinic(config);

        List<IClinic> clinics = searchClinic.findByID(clinicIdC);

        Assert.assertNotNull(clinics);

        System.out.println(mapper.writeValueAsString(clinics));
    }

    @Test(expected = OBIBException.class)
    public void testFindByIdError() throws Exception {
        try {
            ISearchClinic searchClinic = new SearchClinic(config);

            List<IClinic> clinics = searchClinic.findByID("__Wrong_ID");

            //Assert.assertNull(clinics);
        } catch (OBIBException ex) {
            System.out.println("Message: " + ex.getMessage());
            System.out.println("OBIB Message: " + ex.getObibMessage());
            throw ex;
        }
    }

    @Test
    public void testFindByName() throws Exception {
        ISearchClinic searchClinic = new SearchClinic(config);

        List<IClinic> clinics = searchClinic.findByName("oscar");

        Assert.assertNotNull(clinics);

        System.out.println(mapper.writeValueAsString(clinics));
    }

    @Test
    public void testFindByAddress() throws Exception {
        ISearchClinic searchClinic = new SearchClinic(config);

        List<IClinic> clinics = searchClinic.findByAddress("Kelowna");

        Assert.assertNotNull(clinics);

        System.out.println(mapper.writeValueAsString(clinics));
    }
}
