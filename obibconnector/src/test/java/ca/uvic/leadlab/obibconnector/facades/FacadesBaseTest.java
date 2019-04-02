package ca.uvic.leadlab.obibconnector.facades;

public class FacadesBaseTest {

    protected String obibUrl = "http://192.168.100.101:8081";
    protected String clinicId = "cdxpostprod-otca";

    protected Config config = new Config() {
        @Override
        public String getUrl() {
            return obibUrl;
        }

        @Override
        public String getClinicId() {
            return clinicId;
        }
    };

}
