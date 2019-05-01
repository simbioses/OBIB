package ca.uvic.leadlab.cdxconnector;

public abstract class TestWSClient {

    static String cdxServerUrl = "https://servicestest.bccdx.ca";

    interface ClinicA {
        String id = "cdxpostprod-otca";
        String username = "cdxpostprod-otca";
        String password = "VMK31";
        String certificate = "LEADlab_IHA_cert.pfx";
        String certPassword = "LEADlab";
    }

    interface ClinicC {
        String id = "cdxpostprod-obctc";
        String username = "cdxpostprod-obctc";
        String password = "2LsTebRe";
        String certificate = "OscarTestClinic.pfx";
        String certPassword = "LEADlab";
    }
}
