package ca.uvic.leadlab.obibconnector.impl.registry.mock;

import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;
import ca.uvic.leadlab.obibconnector.facades.registry.IClinic;
import ca.uvic.leadlab.obibconnector.facades.registry.ISearchClinic;

import java.util.ArrayList;
import java.util.List;

public class SearchClinicMock implements ISearchClinic {

    @Override
    public List<IClinic> findByName(String name) throws OBIBException {
        List<IClinic> clinics = new ArrayList<>();
        if (name.equalsIgnoreCase("oscar")) {
            clinics.add(new ClinicOTCA());
            clinics.add(new ClinicOTCB());
            clinics.add(new ClinicOBCTC());
        } else {
            throw new OBIBException("No clinics found");
        }
        return clinics;
    }

    @Override
    public List<IClinic> findByID(String id) throws OBIBException {
        List<IClinic> clinics = new ArrayList<>();
        if (id.equals("cdxpostprod-otca")) {
            clinics.add(new ClinicOTCA());
        } else {
            throw new OBIBException("No clinics found");
        }
        return clinics;
    }

    @Override
    public List<IClinic> findByAddress(String address) throws OBIBException {
        List<IClinic> clinics = new ArrayList<>();
        if (address.equalsIgnoreCase("Test")) {
            clinics.add(new ClinicOTCA());
            clinics.add(new ClinicOBCTC());
        } else {
            throw new OBIBException("No clinics found");
        }
        return clinics;
    }
}
