package ca.uvic.leadlab.obibconnector.facades;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FacadesBaseTest {

    protected ObjectMapper mapper = new ObjectMapper();

    protected String obibUrl = "http://192.168.100.101:8081";
    protected String clinicIdA = "cdxpostprod-otca";
    protected String clinicIdC = "cdxpostprod-obctc";
    protected String clinicIdT = "cdxpostprod-ctc";

    protected Config configClinicA = new Config() {
        @Override
        public String getUrl() {
            return obibUrl;
        }

        @Override
        public String getClinicId() {
            return clinicIdA;
        }
    };

    protected Config configClinicC = new Config() {
        @Override
        public String getUrl() {
            return obibUrl;
        }

        @Override
        public String getClinicId() {
            return clinicIdC;
        }
    };

    protected Config configClinicT = new Config() {
        @Override
        public String getUrl() {
            return obibUrl;
        }

        @Override
        public String getClinicId() {
            return clinicIdT;
        }
    };

}
