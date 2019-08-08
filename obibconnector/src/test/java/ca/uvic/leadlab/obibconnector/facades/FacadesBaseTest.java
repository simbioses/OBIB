package ca.uvic.leadlab.obibconnector.facades;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FacadesBaseTest {

    protected ObjectMapper mapper = new ObjectMapper();

    protected String obibUrl = "https://192.168.100.101";

    protected String clinicIdA = "cdxpostprod-otca";
    protected String clinicPasswordA = "VMK31";

    protected String clinicIdC = "cdxpostprod-obctc";
    protected String clinicPasswordC = "2LsTebRe";

    protected String clinicIdT = "cdxpostprod-ctc";
    protected String clinicPasswordT = "conformance123";

    protected String certFile = "obibcerts.pfx";
    protected String certPass = "password";

    protected Config configClinicA = new Config() {
        @Override
        public String getUrl() {
            return obibUrl;
        }

        @Override
        public String getClinicId() {
            return clinicIdA;
        }

        @Override
        public String getClinicPassword() {
            return clinicPasswordA;
        }

        @Override
        public String getKeystorePassword() {
            return certPass;
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

        @Override
        public String getClinicPassword() {
            return clinicPasswordC;
        }

        @Override
        public String getKeystorePassword() {
            return certPass;
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

        @Override
        public String getClinicPassword() {
            return clinicPasswordT;
        }

        @Override
        public String getKeystorePassword() {
            return certPass;
        }
    };

}
