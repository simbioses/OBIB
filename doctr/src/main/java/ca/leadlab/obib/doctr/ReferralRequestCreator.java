package ca.leadlab.obib.doctr;

import ca.infoway.messagebuilder.datatype.lang.*;
import ca.infoway.messagebuilder.datatype.lang.util.DateWithPattern;
import ca.infoway.messagebuilder.datatype.lang.util.PersonNamePartType;
import ca.infoway.messagebuilder.datatype.lang.util.PostalAddressPartType;
import ca.infoway.messagebuilder.domainvalue.AdministrativeGender;
import ca.infoway.messagebuilder.domainvalue.Realm;
import ca.infoway.messagebuilder.domainvalue.basic.EntityNameUse;
import ca.infoway.messagebuilder.domainvalue.basic.PostalAddressUse;
import ca.infoway.messagebuilder.domainvalue.basic.TelecommunicationAddressUse;
import ca.infoway.messagebuilder.domainvalue.basic.URLScheme;
import ca.infoway.messagebuilder.domainvalue.x_DocumentMediaType;
import ca.infoway.messagebuilder.j5goodies.DateUtil;
import ca.infoway.messagebuilder.model.ccda_r1_1.basemodel.NonXMLBodyBean;
import ca.infoway.messagebuilder.model.ccda_r1_1.domainvalue.BasicConfidentialityKind;
import ca.infoway.messagebuilder.model.ccda_r1_1.domainvalue.ConsultDocumentType;
import ca.infoway.messagebuilder.model.ccda_r1_1.domainvalue.Language;
import ca.infoway.messagebuilder.model.ccda_r1_1.merged.*;
import ca.infoway.messagebuilder.model.ccda_r1_1.consultationnote.ConsultationNote;
import ca.infoway.messagebuilder.model.ccda_r1_1.consultationnote.Component2Bean;
import ca.infoway.messagebuilder.resolver.CodeResolverRegistry;
import ca.infoway.messagebuilder.xml.delta.RealmCode;
import org.xml.sax.SAXException;

import java.util.TimeZone;

public class ReferralRequestCreator {

    private PatientBean patient;
    private PatientRoleBean patientRole;
    private ConsultationNote doc;
    private RecordTargetBean recordTarget;
    private AssignedAuthorBean assignedAuthor;
    private AssignedAuthorPersonBean assignedAuthorPerson;
    private Author_2Bean author;
    private CustodianOrganizationBean organization;
    private AssignedCustodianBean assignedCustodian;
    private CustodianBean custodian;
    private NonXMLBodyBean body;
    private Component2Bean component;


    public  ReferralRequestCreator() {
        patient = new PatientBean();
        patientRole = new PatientRoleBean();
        patientRole.setPatient(patient);
        doc = new ConsultationNote();
        recordTarget = new RecordTargetBean();
        assignedAuthor = new AssignedAuthorBean();
        recordTarget.setPatientRole(patientRole);
        doc.getRecordTarget().add(recordTarget);
        author = new Author_2Bean();
        author.setAssignedAuthor(assignedAuthor);
        doc.getAuthor().add(author);
        organization = new CustodianOrganizationBean();
        assignedCustodian = new AssignedCustodianBean();
        assignedCustodian.setRepresentedCustodianOrganization(organization);
        custodian = new CustodianBean();
        custodian.setAssignedCustodian(assignedCustodian);
        doc.setCustodian(custodian);
        body = new NonXMLBodyBean();
        component = new Component2Bean();
        component.setComponent2Choice(body);
        assignedAuthorPerson = new AssignedAuthorPersonBean();
        assignedAuthor.setAssignedAuthorChoice(assignedAuthorPerson);

        Identifier id = new Identifier("2.16.840.1.113883.1.3", "POCD_HD000040");
        id.setAssigningAuthorityName("HL7 CDA R2");
        doc.setTypeId(id);

        //template id for generic unstructured referrals (see CDX Conformance doc, p. 15)
        id = new Identifier("2.16.840.1.113883.3.1818.10.1.5");
        id.setAssigningAuthorityName("Generic Unstructured Referral");
        doc.getTemplateId().add(id);
        doc.setCode(getLoincCode());
        doc.setTitle("Referral Request");
        doc.setConfidentialityCode(getConfidentialityCode());
        doc.setLanguageCode(getLanguageCode());
        doc.setComponent(component);
    }

    public ReferralRequestCreator patientName(String lastName, String firstName) {
        patient.getName().add(createName(lastName, firstName));
        return this;
    }

    public ReferralRequestCreator patientGender(String gender) {
        patient.setAdministrativeGenderCode(new CodedTypeR2<AdministrativeGender>(ca.infoway.messagebuilder.domainvalue.payload.AdministrativeGender.FEMALE));
        return this;
    }

    public ReferralRequestCreator patientDOB(int year, int month, int day) {
        patient.setBirthTime(createDate(2005, 4, 1));
        return this;
    }


    public ReferralRequestCreator patientID(String idstring) {
        Identifier id = new Identifier("2.16.840.1.113883.4.50", idstring);
        id.setAssigningAuthorityName("BC Patient Health Number");
        patientRole.getId().add(id);
        return this;
    }


    public ReferralRequestCreator patientAddress(String street, String city, String state, String postalCode, String country, String phone) {
        patientRole.getAddr().add(createAddress(street, city, state, postalCode, country));
        patientRole.getTelecom().add(createTelecom(phone, TelecommunicationAddressUse.PRIMARY_HOME));
        return this;
    }

    public ReferralRequestCreator effectiveTime(int year, int month, int day, int hour, int minute, TimeZone timeZone) {
        doc.setEffectiveTime(createDateTime(year, month, day, hour, minute, timeZone));
        return this;
    }

    public ReferralRequestCreator docId(String idstring) {
        Identifier id = new Identifier("2.16.840.1.113883.3.277.100.3", idstring);
        id.setAssigningAuthorityName("CDX Clinical Document ID");
        doc.setId(id);
        return this;
    }

    public ReferralRequestCreator authorID(String idstring) {
        assignedAuthor.getId().add(new Identifier("2.16.840.1.113883.3.40.2.11", idstring));
        return this;
    }

    public ReferralRequestCreator authorName(String lastName, String firstName) {
        assignedAuthorPerson.getName().add(createName(lastName, firstName));
        return this;
    }

    public ReferralRequestCreator authorAddress (String street, String city, String state, String postalCode, String country, String phone) {
        assignedAuthor.getAddr().add(createAddress(street, city, state, postalCode, country));
        assignedAuthor.getTelecom().add(createTelecom(phone, TelecommunicationAddressUse.WORKPLACE));
        return this;
    }

    public ReferralRequestCreator authorTime(int year, int month, int day) {
        author.setTime(createDate(year, month, day));
        return this;
    }

    public ReferralRequestCreator custodian(String namestring, String idstring) {
        EntityNamePart namePart = new EntityNamePart(namestring);
        OrganizationName name = new OrganizationName();
        name.addNamePart(namePart);
        organization.getId().add(new Identifier("2.16.840.1.113883.3.277.1.62", idstring));
        organization.setName(name);
        return this;
    }

    public ReferralRequestCreator custodianAddress(String street, String city, String state, String postalCode, String country, String phone) {
        organization.setTelecom(createTelecom(phone, TelecommunicationAddressUse.WORKPLACE));
        organization.setAddr(createAddress(street, city, state, postalCode, country));
        return this;
    }


    public ReferralRequestCreator body(String bodyString) {
        EncapsulatedData text = new EncapsulatedData();

        text.setMediaType(new x_DocumentMediaType() {
            public String getCodeValue() {
                return "text/plain";
            }
            public String getCodeSystem() {
                return null;
            }
            public String getCodeSystemName() {
                return null;
            }
        });

        try {
            text.addContent(bodyString);
        } catch (SAXException e) {
            System.out.println("Unexpected exception adding content to ED: " + e);
        }

        body.setText(text);
        return this;
    }

    public ConsultationNote get() {
        return doc;
    }



    private CodedTypeR2<ConsultDocumentType> getLoincCode() {
        ConsultDocumentType code = CodeResolverRegistry.lookup(ConsultDocumentType.class, "57133-1", "2.16.840.1.113883.6.1");

        CodedTypeR2<ConsultDocumentType> loincCode = new CodedTypeR2<ConsultDocumentType>();
        loincCode.setCode(code);
        loincCode.setCodeSystemName("LOINC");
        loincCode.setDisplayName("ReferralNote");

        return loincCode;
    }

    private CodedTypeR2<BasicConfidentialityKind> getConfidentialityCode() {
        BasicConfidentialityKind code = CodeResolverRegistry.lookup(BasicConfidentialityKind.class, "N", "2.16.840.1.113883.5.25");
        return new CodedTypeR2<BasicConfidentialityKind>(code);
    }

    private CodedTypeR2<Language> getLanguageCode() {
        Language code = CodeResolverRegistry.lookup(Language.class, "en-CA");
        return new CodedTypeR2<Language>(code);
    }



    private PostalAddress createAddress(String streetAddress, String city, String state, String postalCode, String country) {
        PostalAddress address = new PostalAddress();
        address.getUses().add(PostalAddressUse.PRIMARY_HOME);
        address.getParts().add(new PostalAddressPart(PostalAddressPartType.STREET_ADDRESS_LINE, streetAddress));
        address.getParts().add(new PostalAddressPart(PostalAddressPartType.CITY, city));
        address.getParts().add(new PostalAddressPart(PostalAddressPartType.STATE, state));
        address.getParts().add(new PostalAddressPart(PostalAddressPartType.POSTAL_CODE, postalCode));
        address.getParts().add(new PostalAddressPart(PostalAddressPartType.COUNTRY, country));
        return address;
    }

    private TelecommunicationAddress createTelecom(String number, TelecommunicationAddressUse use) {
        return new TelecommunicationAddress(URLScheme.TEL, number, use);
    }


    private MbDate createDate(int year, int month, int day) {
        return new MbDate(new DateWithPattern(DateUtil.getDate(year, month, day), "yyyyMMdd"));
    }

    private MbDate createDateTime(int year, int month, int day, int hour, int minute, TimeZone timeZone) {
        return new MbDate(new DateWithPattern(DateUtil.getDate(year, month, day, hour, minute, 0, 0, timeZone), "yyyyMMddhhmmZ"));
    }

    private PersonName createName(String lastName, String firstName) {
        PersonName name = new PersonName();
        name.getParts().add(new EntityNamePart(firstName, PersonNamePartType.GIVEN));
        name.getParts().add(new EntityNamePart(lastName, PersonNamePartType.FAMILY));
        name.getUses().add(EntityNameUse.LEGAL);
        return name;
    }
}
