package ca.uvic.leadlab.oscarmirthrestclient;

import ca.uvic.leadlab.models.oscar.OscarReferralRequestInfo;
import ca.uvic.leadlab.models.oscar.*;

import java.util.ArrayList;
import java.util.List;

public class SetOscarReferralRequestInfo implements IOscarInformation{
    /*
        This is the class used to set all the Referral Request Information from Oscar EMR
   */
    OscarExtractedInfo oscarExtractedInfo;
    public  SetOscarReferralRequestInfo(OscarExtractedInfo oscarExtractedInfo)
    {
        this.oscarExtractedInfo = oscarExtractedInfo;
    }

    public OscarReferralRequestInfo OscarInfo()  //TODO Add the object that has all the parameter from oscar EMR
    {
        OscarReferralRequestInfo oscarReferralRequestInfo = new OscarReferralRequestInfo();

     /*
    Patient Object
     */
        PatientName patientName = new PatientName();
        patientName.setFirstname(oscarExtractedInfo.getPatientFirstName());
        patientName.setMiddlename(oscarExtractedInfo.getPatientMiddleName());
        patientName.setLastname(oscarExtractedInfo.getPatientLastName());

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
        AuthorDetail authorDetail = new AuthorDetail();

        List<AuthorName> authorNameList = new ArrayList<AuthorName>();
        AuthorName authorName = new AuthorName();
        authorName.setFirstname("Jens");
        authorName.setMiddlename("D");
        authorName.setLastname("Weber");
        authorNameList.add(authorName);


        //Author Address
        List<AuthorAddress> authorAddressList = new ArrayList<AuthorAddress>();
        AuthorAddress authorAddress = new AuthorAddress();
        authorAddress.setStreetaddress("1775 Fort Street");
        authorAddress.setCity("Victoria");
        authorAddress.setProvince("BC");
        authorAddress.setCountry("CA");
        authorAddress.setPostalcode("V89017");
        authorAddressList.add(authorAddress);


        List<String> authorTelecomList = new ArrayList<String>();
        authorTelecomList.add("(250)721-8797");

        List<String> authorIDList = new ArrayList<String>();
        authorIDList.add("342434234");

        List<String> authorTimeList = new ArrayList<String>();
        authorTimeList.add("2019-02-08T19:00:31+00:00");

        authorDetail.setAuthorName(authorNameList);
        authorDetail.setAuthorID(authorIDList);
        authorDetail.setAuthorAddress(authorAddressList);
        authorDetail.setAuthorTelcom(authorTelecomList);
        authorDetail.setAuthorTime(authorTimeList);

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


        oscarReferralRequestInfo.setPatientDetail(patientDetail);
        oscarReferralRequestInfo.setAuthorDetail(authorDetail);
        oscarReferralRequestInfo.setEffectiveTime("2019-02-08T19:00:31+00:00");
        oscarReferralRequestInfo.setCustodianDetail(custodianDetail);
        oscarReferralRequestInfo.setProviderDetail(providerDetail);
        oscarReferralRequestInfo.setBodyText("This patient was refered to you");
        return  oscarReferralRequestInfo;
    }




}
