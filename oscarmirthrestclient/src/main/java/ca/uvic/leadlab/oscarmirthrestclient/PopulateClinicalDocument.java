package ca.uvic.leadlab.oscarmirthrestclient;



import ca.uvic.leadlab.models.OBIBConnectorEntities.*;


import java.util.ArrayList;
import java.util.List;
public class PopulateClinicalDocument {


    public ClinicalDocument OscarInfo()  //TODO Add the object that has all the parameter from oscar EMR
    {


     /*
    Patient Object
     */

        /* .................................Patient.................................................*/
        //name for patient
        List<String> givenNamePatient = new ArrayList<String>();
        givenNamePatient.add("Jens-P");

        Name patientName = new Name();
        patientName.setGiven(givenNamePatient);
        patientName.setFamily("Weber-P");
        patientName.setPrefix("Dr");
        patientName.setSuffix("");

        patientName.setUse("L");
        List<Name> nameList = new ArrayList<Name>();
        nameList.add(patientName);  // Add List of Patient Name


        //telecom
        List<Telecom> patientTelecomList = new ArrayList<Telecom>();
        Telecom patientTelcomTel = new Telecom();
        patientTelcomTel.setUse("H");
        patientTelcomTel.setType("tel");
        patientTelcomTel.setValue("(250)721-8797");
        patientTelecomList.add(patientTelcomTel); // add telephone object

        Telecom patientTelecomEmail = new Telecom();
        patientTelecomEmail.setUse("H");
        patientTelecomEmail.setType("email");
        patientTelecomEmail.setValue("patient@gmail.com");
        patientTelecomList.add(patientTelecomEmail); //add email object

        //address
        List<Address> patientAddressList = new ArrayList<Address>();
        Address patientAddress = new Address();
        patientAddress.setUse("H");
        patientAddress.setStreetAddress("1775 Fort Street");
        patientAddress.setCity("Victoria");
        patientAddress.setProvince("BC");
        patientAddress.setPostalCode("V89017");
        patientAddress.setCountry("CA");
        patientAddressList.add(patientAddress);  // add address object

        //patient detail
        Patient patient = new Patient();
        patient.setId("123456789");
        patient.setAddress(patientAddressList);
        patient.setTelecom(patientTelecomList);
        patient.setName(nameList);
        patient.setGender("M");
        patient.setDob("2019-02-08");

        /* .................................Author.................................................*/


        //name for author
        List<String> givenNameAuthor = new ArrayList<String>();
        givenNameAuthor.add("Jens-A");

        Name authorName = new Name();
        authorName.setGiven(givenNameAuthor);
        authorName.setFamily("Weber-A");
        authorName.setPrefix("Dr");
        authorName.setSuffix("");


        //telecom for author
        List<Telecom> authorTelcomList = new ArrayList<Telecom>();
        Telecom authorTelecomTel = new Telecom();
        authorTelecomTel.setUse("H");
        authorTelecomTel.setType("tel");
        authorTelecomTel.setValue("(250)721-8797");
        authorTelcomList.add(authorTelecomTel); // add telephone object

        Telecom authorTelecomEmail = new Telecom();
        authorTelecomEmail.setUse("H");
        authorTelecomEmail.setType("email");
        authorTelecomEmail.setValue("patient@gmail.com");
        authorTelcomList.add(authorTelecomEmail); //add email object

        //address for author
        List<Address> authorAddressList = new ArrayList<Address>();
        Address authorAddress = new Address();
        authorAddress.setUse("H");
        authorAddress.setStreetAddress("1775 Fort Street");
        authorAddress.setCity("Victoria");
        authorAddress.setProvince("BC");
        authorAddress.setPostalCode("V89017");
        authorAddress.setCountry("CA");
        authorAddressList.add(authorAddress);  // add address object


        //author detail
        Author author = new Author();
        author.setId("123456789");
        author.setTime("2019-02-08t19:00:31:00+00:00");
        author.setAddress(authorAddressList);
        author.setTelecom(authorTelcomList);
        author.setName(authorName);
        List<Author> authorList = new ArrayList<Author>();
        authorList.add(author);


        /*....................................Recipient....................................................*/
        //name for recepient
        List<String> givenNameRecipient = new ArrayList<String>();
        givenNameRecipient.add("Jens-R");

        Name recipientName = new Name();
        recipientName.setGiven(givenNameAuthor);
        recipientName.setFamily("Weber-R");
        recipientName.setPrefix("Dr");
        recipientName.setSuffix("");


        //telecom for recepient
        List<Telecom> receipientTelcomList = new ArrayList<Telecom>();
        Telecom receipientTelecomTel = new Telecom();
        receipientTelecomTel.setUse("H");
        receipientTelecomTel.setType("tel");
        receipientTelecomTel.setValue("(250)721-8797");
        receipientTelcomList.add(receipientTelecomTel); // add telephone object

        Telecom recepientTelecomEmail = new Telecom();
        recepientTelecomEmail.setUse("H");
        recepientTelecomEmail.setType("email");
        recepientTelecomEmail.setValue("patient@gmail.com");
        receipientTelcomList.add(recepientTelecomEmail); //add email object

        //address of recipient
        List<Address> receipientAddressList = new ArrayList<Address>();
        Address recipientAddress = new Address();
        recipientAddress.setUse("H");
        recipientAddress.setStreetAddress("1775 Fort Street");
        recipientAddress.setCity("Victoria");
        recipientAddress.setProvince("BC");
        recipientAddress.setPostalCode("V89017");
        recipientAddress.setCountry("CA");
        receipientAddressList.add(recipientAddress);  // add address object

        // received organization
        ReceivedOrganization receivedOrganization = new ReceivedOrganization();
        receivedOrganization.setId("34534545454");
        receivedOrganization.setName("Organization Name");


        //recipient detail
        Recipient recipient = new Recipient();
        recipient.setId("987653421");
        recipient.setAddress(receipientAddressList);
        recipient.setTelecom(receipientTelcomList);
        recipient.setName(recipientName);
        recipient.setReceivedOrganization(receivedOrganization);

        List<Recipient> recipientList = new ArrayList<Recipient>();
        recipientList.add(recipient);

        /*.................................Custodian..................................................*/

        Custodian custodian = new Custodian();
        custodian.setId("45545453444");
        custodian.setName("Ocean Spread Medical");

        /*..................................Data Enterer.................................................*/


        //name for Data Enterer
        List<String> givenNameDataEnterer = new ArrayList<String>();
        givenNameDataEnterer.add("Jens-D");

        Name dataEntererName = new Name();
        dataEntererName.setGiven(givenNameAuthor);
        dataEntererName.setFamily("Weber-D");
        dataEntererName.setPrefix("Dr");
        dataEntererName.setSuffix("");


        //telecom for Data Enterer
        List<Telecom> dataEntererTelcomList = new ArrayList<Telecom>();
        Telecom dataEntererTelecomTel = new Telecom();
        dataEntererTelecomTel.setUse("H");
        dataEntererTelecomTel.setType("tel");
        dataEntererTelecomTel.setValue("(250)721-8797");
        dataEntererTelcomList.add(dataEntererTelecomTel); // add telephone object

        Telecom dataEntereTelecomEmail = new Telecom();
        dataEntereTelecomEmail.setUse("H");
        dataEntereTelecomEmail.setType("email");
        dataEntereTelecomEmail.setValue("patient@gmail.com");
        dataEntererTelcomList.add(dataEntereTelecomEmail); //add email object

        //address of Data Enterer
        List<Address> dataEntererAddressList = new ArrayList<Address>();
        Address dataEntererAddress = new Address();
        dataEntererAddress.setUse("H");
        dataEntererAddress.setStreetAddress("1775 Fort Street");
        dataEntererAddress.setCity("Victoria");
        dataEntererAddress.setProvince("BC");
        dataEntererAddress.setPostalCode("V89017");
        dataEntererAddress.setCountry("CA");
        dataEntererAddressList.add(dataEntererAddress);  // add address object


        //dataenterer detail
        DataEnterer dataEnterer = new DataEnterer();
        dataEnterer.setId("123456789");
        dataEnterer.setTime("2019-02-08t19:00:31:00+00:00");
        dataEnterer.setAddress(dataEntererAddressList);
        dataEnterer.setTelecom(dataEntererTelcomList);
        dataEnterer.setName(dataEntererName);


        /*...............................................Authenticator Paticipant ...............................*/

        //name for authenticator
        List<String> givenNameAuthenticator = new ArrayList<String>();
        givenNameAuthenticator.add("Jens-A");

        Name authenticatorName = new Name();
        authenticatorName.setGiven(givenNameAuthor);
        authenticatorName.setFamily("Weber");
        authenticatorName.setPrefix("Dr");
        authenticatorName.setSuffix("");


        //telecom for Authenticator
        List<Telecom> authenticatorTelcomList = new ArrayList<Telecom>();
        Telecom authenticatorTelecomTel = new Telecom();
        authenticatorTelecomTel.setUse("H");
        authenticatorTelecomTel.setType("tel");
        authenticatorTelecomTel.setValue("(250)721-8797");
        authenticatorTelcomList.add(authenticatorTelecomTel); // add telephone object

        Telecom authenticatorTelecomEmail = new Telecom();
        authenticatorTelecomEmail.setUse("H");
        authenticatorTelecomEmail.setType("email");
        authenticatorTelecomEmail.setValue("patient@gmail.com");
        authenticatorTelcomList.add(authenticatorTelecomEmail); //add email object

        //address of Authenticator
        List<Address> authenticatorAddressList = new ArrayList<Address>();
        Address authenticatorAddress = new Address();
        authenticatorAddress.setUse("H");
        authenticatorAddress.setStreetAddress("1775 Fort Street");
        authenticatorAddress.setCity("Victoria");
        authenticatorAddress.setProvince("BC");
        authenticatorAddress.setPostalCode("V89017");
        authenticatorAddress.setCountry("CA");
        authenticatorAddressList.add(authenticatorAddress);  // add address object

        //Authenticator detail
        Authenticator authenticator = new Authenticator();
        authenticator.setId("987653421");
        authenticator.setAddress(receipientAddressList);
        authenticator.setTelecom(receipientTelcomList);
        authenticator.setName(recipientName);

        List<Authenticator> authenticatorList = new ArrayList<Authenticator>();
        authenticatorList.add(authenticator);

        /*...........................................Participant ............................*/
        //name for participant
        List<String> givenNameParticipant = new ArrayList<String>();
        givenNameParticipant.add("Jens-P");

        Name participantName = new Name();
        participantName.setGiven(givenNameAuthor);
        participantName.setFamily("Weber");
        participantName.setPrefix("Dr");
        participantName.setSuffix("");


        //telecom for participant
        List<Telecom> participantTelcomList = new ArrayList<Telecom>();
        Telecom participantTelecomTel = new Telecom();
        participantTelecomTel.setUse("H");
        participantTelecomTel.setType("tel");
        participantTelecomTel.setValue("(250)721-8797");
        participantTelcomList.add(participantTelecomTel); // add telephone object

        Telecom participantTelecomEmail = new Telecom();
        participantTelecomEmail.setUse("H");
        participantTelecomEmail.setType("email");
        participantTelecomEmail.setValue("patient@gmail.com");
        participantTelcomList.add(participantTelecomEmail); //add email object

        //address of participant
        List<Address> participantAddressList = new ArrayList<Address>();
        Address participantAddress = new Address();
        participantAddress.setUse("H");
        participantAddress.setStreetAddress("1775 Fort Street");
        participantAddress.setCity("Victoria");
        participantAddress.setProvince("BC");
        participantAddress.setPostalCode("V89017");
        participantAddress.setCountry("CA");
        participantAddressList.add(participantAddress);  // add address object

        //Participant detail
        Participant participant = new Participant();
        participant.setId("987653421");
        participant.setAddress(participantAddressList);
        participant.setTelecom(participantTelcomList);
        participant.setName(participantName);

        List<Participant> participantList = new ArrayList<Participant>();
        participantList.add(participant);
        /*....................................................nonXMLBody..............................*/

        NonXMLBody nonXMLBody = new NonXMLBody();
        nonXMLBody.setMediaType("text/plain");
        nonXMLBody.setContent("This Patient was refered to you");

        /* .............................................ClinicalDocument.................................*/
        ClinicalDocument clinicalDocument = new ClinicalDocument();
        clinicalDocument.setEffectiveTime("2019-02-08T19:00:31:00+00:00");
        clinicalDocument.setPatient(patient);
        clinicalDocument.setAuthor(authorList);
        clinicalDocument.setRecipient(recipientList);
        clinicalDocument.setCustodian(custodian);
        clinicalDocument.setDataEnterer(dataEnterer);
        clinicalDocument.setAuthenticator(authenticatorList);
        clinicalDocument.setParticipant(participantList);
        clinicalDocument.setNonXMLBody(nonXMLBody);



        return  clinicalDocument;
    }

}
