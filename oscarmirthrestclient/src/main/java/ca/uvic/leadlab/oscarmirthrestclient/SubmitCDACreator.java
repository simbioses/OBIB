package ca.uvic.leadlab.oscarmirthrestclient;
import ca.uvic.leadlab.models.*;
import ca.uvic.leadlab.models.submitcda.*;

import java.util.ArrayList;

public class SubmitCDACreator implements IOSCAROBIB {

    public   SubmitCDA setSubmitCDAInfo()  //TODO Add the object that has all the parameter from oscar EMR
    {
        SubmitCDA submitCDA = new SubmitCDA();

     /*
    Patient Object
     */
        PatientName patientName = new PatientName();
        patientName.setFirstname("Jens");
        patientName.setMiddlename("D");
        patientName.setLastname("Weber");

        //Patient Address
        PatientAddress patientAddress = new PatientAddress();
        patientAddress.setStreetaddress("1775 Fort Street");
        patientAddress.setCity("Victoria");
        patientAddress.setProvince("BC");
        patientAddress.setCountry("CA");
        patientAddress.setPostalcode("V89017");

        //Patient Detail
        PatientDetail patientDetail = new PatientDetail();
        patientDetail.setPatientName(patientName);
        patientDetail.setPatientGender("Male");
        patientDetail.setPatientDOB("2019-02-08");
        patientDetail.setPatientID("3423456");
        patientDetail.setPatientAddress(patientAddress);
        patientDetail.setPatientTelcom("(250)721-8789");

    /*
    Author Object
     */
        AuthorName authorName = new AuthorName();
        authorName.setFirstname("Jens");
        authorName.setMiddlename("D");
        authorName.setLastname("Weber");

        //Author Address
        AuthorAddress authorAddress = new AuthorAddress();
        authorAddress.setStreetaddress("1775 Fort Street");
        authorAddress.setCity("Victoria");
        authorAddress.setProvince("BC");
        authorAddress.setCountry("CA");
        authorAddress.setPostalcode("V89017");

        AuthorDetail authorDetail = new AuthorDetail();
        authorDetail.setAuthorName(authorName);
        authorDetail.setAuthorID("342434234");
        authorDetail.setAuthorAddress(authorAddress);
        authorDetail.setAuthorTelcom("(250)721-8797");
        authorDetail.setAuthorTime("2019-02-08T19:00:31+00:00");

        CustodianDetail custodianDetail = new CustodianDetail();
        custodianDetail.setCustodianOrganizationID("34455676");
        custodianDetail.setCustodianOrganizationName("Ocean Spred Medical");

    /*   2019-02-08T19:00:31+00:00
    Provider Object
     */
        ProviderName providerName  = new ProviderName();
        providerName.setFirstname("Jens");
        providerName.setMiddlename("D");
        providerName.setLastname("Weber");

        ProviderAddress providerAddress  = new ProviderAddress();
        providerAddress.setStreetaddress("1775 Fort Street");
        providerAddress.setCity("Victoria");
        providerAddress.setProvince("BC");
        providerAddress.setCountry("CA");
        providerAddress.setPostalcode("V89017");


        ProviderDetail providerDetail = new ProviderDetail();
        providerDetail.setProviderName(providerName);
        providerDetail.setProviderID("34567889");
        providerDetail.setProviderTelcom("(250)721-8707");
        providerDetail.setProviderAddress(providerAddress);

        submitCDA.setPatientDetail(patientDetail);
        submitCDA.setAuthorDetail(authorDetail);
        submitCDA.setEffectiveTime("2019-02-08T19:00:31+00:00");
        submitCDA.setCustodianDetail(custodianDetail);
        submitCDA.setProviderDetail(providerDetail);
        submitCDA.setBodyText("This patient was refered to you");
        return  submitCDA;
    }

    @Override
    public Object SubmitCDA() {
        return null;
    }

    @Override
    public ArrayList<String> ClinicSearch() {
        return null;
    }

    @Override
    public ArrayList<String> ProviderSearch() {
        return null;
    }
}
