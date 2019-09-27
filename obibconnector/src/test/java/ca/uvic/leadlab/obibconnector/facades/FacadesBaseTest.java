package ca.uvic.leadlab.obibconnector.facades;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FacadesBaseTest {

    protected ObjectMapper mapper = new ObjectMapper();

    protected String obibUrl = "https://192.168.100.101";

    protected String clinicIdA = "cdxpostprod-otca";
    protected String clinicPasswordA = "VMK31";

    protected String clinicIdB = "cdxpostprod-otcb";

    protected String clinicIdC = "cdxpostprod-obctc";
    protected String clinicPasswordC = "2LsTebRe";

    protected String clinicIdT = "cdxpostprod-ctc";
    protected String clinicPasswordT = "conformance123";

    protected Config config = new Config() {
        @Override
        public String getUrl() {
            return obibUrl;
        }
    };
}
