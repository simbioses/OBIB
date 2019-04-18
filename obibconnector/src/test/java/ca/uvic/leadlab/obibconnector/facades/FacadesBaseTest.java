package ca.uvic.leadlab.obibconnector.facades;

public class FacadesBaseTest {

    protected String obibUrl = "http://192.168.100.101:8081";
    protected String clinicIdA = "cdxpostprod-otca";
    protected String clinicIdC = "cdxpostprod-obctc";

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

}
