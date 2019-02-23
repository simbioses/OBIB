package ca.uvic.leadlab.oscarmirthrestclient;

import java.util.ArrayList;

public interface IOSCAROBIB {



    Object SubmitCDA();

    ArrayList<String> ClinicSearch();

    ArrayList<String> ProviderSearch();
}
