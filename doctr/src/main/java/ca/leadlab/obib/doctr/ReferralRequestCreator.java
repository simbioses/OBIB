package ca.leadlab.obib.doctr;

import ca.infoway.messagebuilder.datatype.lang.*;
import ca.infoway.messagebuilder.datatype.lang.util.DateWithPattern;
import ca.infoway.messagebuilder.datatype.lang.util.PersonNamePartType;
import ca.infoway.messagebuilder.datatype.lang.util.PostalAddressPartType;
import ca.infoway.messagebuilder.domainvalue.ReferralDocumentType;
import ca.infoway.messagebuilder.domainvalue.basic.EntityNameUse;
import ca.infoway.messagebuilder.domainvalue.basic.PostalAddressUse;
import ca.infoway.messagebuilder.domainvalue.basic.TelecommunicationAddressUse;
import ca.infoway.messagebuilder.domainvalue.basic.URLScheme;
import ca.infoway.messagebuilder.domainvalue.payload.AdministrativeGender;
import ca.infoway.messagebuilder.domainvalue.x_DocumentMediaType;
import ca.infoway.messagebuilder.j5goodies.DateUtil;

import ca.infoway.messagebuilder.model.ClinicalDocumentBean;
import ca.infoway.messagebuilder.model.ccda_pcs_r1_1.basemodel.*;
import ca.infoway.messagebuilder.model.ccda_pcs_r1_1.basemodel.AssignedAuthorBean;
import ca.infoway.messagebuilder.model.ccda_pcs_r1_1.basemodel.PatientBean;
import ca.infoway.messagebuilder.model.ccda_pcs_r1_1.basemodel.PatientRoleBean;
import ca.infoway.messagebuilder.model.ccda_pcs_r1_1.basemodel.RecordTargetBean;
import ca.infoway.messagebuilder.model.ccda_pcs_r1_1.domainvalue.BasicConfidentialityKind;
import ca.infoway.messagebuilder.model.ccda_pcs_r1_1.domainvalue.Language;
import ca.infoway.messagebuilder.model.ccda_pcs_r1_1.merged.*;
import ca.infoway.messagebuilder.resolver.CodeResolverRegistry;
import org.xml.sax.SAXException;

import java.util.TimeZone;

public class ReferralRequestCreator {

    private BaseModel doc;

    private RecordTargetBean recordTarget;
    private PatientRoleBean patientRole;
    private PatientBean patient;

    private Author_1Bean author;
    private AssignedAuthorBean assignedAuthor;
    private PersonBean assignedAuthorPerson;
    private AuthoringDeviceBean assignedAuthoringDevice;

    private CustodianBean custodian;
    private AssignedCustodianBean assignedCustodian;
    private CustodianOrganizationBean custodianOrganization;

    private NonXMLBodyBean body;
    private Component2Bean component;

    public ReferralRequestCreator() {
        // Document Header
        doc = new BaseModel(); // CONF-BC0001, TODO verify CONF-BC0502
        // CONF-BC002, CONF-BC003, CONF-BC004
        doc.setTypeId(createIdentifier("2.16.840.1.113883.1.3", "POCD_HD000040", "HL7 CDA R2"));
        doc.addRealmCode(getRealmCode()); // CONF-BC0005
        doc.setConfidentialityCode(getConfidentialityCode()); // CONF-BC0027
        doc.setLanguageCode(getLanguageCode()); // CONF-BC0029

        // Record Target
        recordTarget = new RecordTargetBean(); // TODO verify CONF-BC0507
        patientRole = new PatientRoleBean(); // TODO verify CONF-BC0508
        patient = new PatientBean(); // TODO verify CONF-BC0509
        patientRole.setPatient(patient); // CONF-BC0053
        recordTarget.setPatientRole(patientRole); // CONF-BC0047
        doc.getRecordTarget().add(recordTarget); // CONF-BC0047

        // Author
        author = new Author_1Bean(); // TODO verify CONF-BC0510
        assignedAuthor = new AssignedAuthorBean(); // TODO verify CONF-BC0511
        author.setAssignedAuthor(assignedAuthor); // CONF-BC0061
        doc.getAuthor().add(author); // CONF-BC0058

        // Custodian
        custodian = new CustodianBean(); // TODO verify CONF-BC0083
        assignedCustodian = new AssignedCustodianBean(); // TODO verify CONF-BC0514
        custodianOrganization = new CustodianOrganizationBean(); // TODO verify CONF-BC0515
        assignedCustodian.setRepresentedCustodianOrganization(custodianOrganization); // CONF-BC0085
        custodian.setAssignedCustodian(assignedCustodian); // CONF-BC0084
        doc.setCustodian(custodian); // CONF-BC0082

        // Document Body
        body = new NonXMLBodyBean();
        component = new Component2Bean();
        component.setComponent2Choice(body);
        doc.setComponent(component);
    }

    public ReferralRequestCreator templateId(TemplateId templateId) {
        return templateId(templateId.oid(), templateId.title());
    }

    public ReferralRequestCreator templateId(String rootId, String assigningAuthorityName) {
        doc.getTemplateId().add(createIdentifier(rootId, assigningAuthorityName));
        return this;
    }

    public ReferralRequestCreator docId(String docId) {
        // CONF-BC0014, CONF-BC0015
        doc.setId(createIdentifier("2.16.840.1.113883.3.277.100.3", docId, "CDX Clinical Document ID"));
        return this;
    }

    // TODO Optional ClinicalDocument/setId
    // TODO Optional ClinicalDocument/versionNumber
    // TODO Optional ClinicalDocument/relatedDocument.typeCode

    public ReferralRequestCreator loincCode(String code, String displayName) {
        /*CodedTypeR2<Code> loincCode = new CodedTypeR2<>(CodeResolverRegistry.lookup(ReferralDocumentType.class, code, "2.16.840.1.113883.6.1"));
        loincCode.setCodeSystemName("LOINC Code");
        loincCode.setDisplayName(displayName);
        doc.setCode(loincCode); // CONF-BC0021, TODO CONF-BC0022 get from vox.xml?
        doc.setTitle(loincCode.getDisplayName()); // CONF-BC0023, CONF-BC0024*/
        doc.setCode(CodeResolverRegistry.lookup(ReferralDocumentType.class, code, "2.16.840.1.113883.6.1")); // CONF-BC0021, TODO CONF-BC0022 get from vox.xml?
        doc.setTitle(displayName); // CONF-BC0023, CONF-BC0024
        return this;
    }

    public ReferralRequestCreator effectiveTime(int year, int month, int day, int hour, int minute, TimeZone timeZone) {
        doc.setEffectiveTime(createDateTime(year, month, day, hour, minute, timeZone)); // CONF-BC0025, CONF-BC0026
        return this;
    }

    // TODO Optional ClinicalDocument/documentationOf

    public ReferralRequestCreator patientId(String patientId) {
        // CONF-BC0048, CONF-BC0049, CONF-BC0050 TODO verify out of province
        patientRole.getId().add(createIdentifier("2.16.840.1.113883.4.50", patientId, "BC Patient Health Number"));
        return this;
    }

    public ReferralRequestCreator patientAddress(String street, String city, String state, String postalCode, String country) {
        patientRole.getAddr().add(createAddress(street, city, state, postalCode, country)); // CONF-BC0051
        return this;
    }

    public ReferralRequestCreator patientTelecom(String phone) {
        patientRole.getTelecom().add(createTelecom(phone, TelecommunicationAddressUse.PRIMARY_HOME)); // CONF-BC0052
        return this;
    }

    public ReferralRequestCreator patientName(String lastName, String firstName) {
        patient.getName().add(createName(lastName, firstName)); // CONF-BC0054
        return this;
    }

    public ReferralRequestCreator patientGender(String gender) {
        // TODO verify CONF-BC0055
        patient.setAdministrativeGenderCode(AdministrativeGender.valueOf(AdministrativeGender.class, gender));
        return this;
    }

    public ReferralRequestCreator patientDOB(int year, int month, int day) {
        patient.setBirthTime(createDate(year, month, day)); // CONF-BC0056
        return this;
    }

    // TODO Optional patient/languageCommunication

    public ReferralRequestCreator authorTime(int year, int month, int day) {
        author.setTime(createDate(year, month, day)); // CONF-BC0059
        return this;
    }

    public ReferralRequestCreator authorId(String authorId) {
        // CONF-BC0062 TODO verify out of province, CONF-BC0063, CONF-BC0064
        assignedAuthor.getId().add(new Identifier("2.16.840.1.113883.3.40.2.11", authorId));
        return this;
    }

    public ReferralRequestCreator authorPersonName(String lastName, String firstName) {
        assignedAuthorPerson = new PersonBean();
        assignedAuthorPerson.getName().add(createName(lastName, firstName)); // CONF-BC0068
        assignedAuthor.setAssignedAuthorChoice(assignedAuthorPerson); // CONF-BC0065 TODO verify CONF-BC0512
        return this;
    }

    public ReferralRequestCreator authorDeviceName(String software) {
        assignedAuthoringDevice = new AuthoringDeviceBean();
        // TODO assignedAuthoringDevice.setSoftwareName(new CodedTypeR2<>(???, software)); // CONF-BC0069
        assignedAuthor.setAssignedAuthorChoice(assignedAuthoringDevice); // CONF-BC0065 TODO verify CONF-BC0513
        return this;
    }

    public ReferralRequestCreator authorAddress(String street, String city, String state, String postalCode, String country) {
        assignedAuthor.getAddr().add(createAddress(street, city, state, postalCode, country)); // CONF-BC0066
        return this;
    }

    public ReferralRequestCreator authorTelecom(String phone) {
        assignedAuthor.getTelecom().add(createTelecom(phone, TelecommunicationAddressUse.WORKPLACE)); // CONF-BC0067
        return this;
    }

    // TODO Optional Information Recipient

    public ReferralRequestCreator custodianOrgazinationId(String organizationId) {
        custodianOrganization.getId().add(new Identifier("2.16.840.1.113883.3.277.1.62", organizationId)); // CONF-BC0086 TODO verify Health Authority vs EMR
        return this;
    }

    public ReferralRequestCreator custodianOrgazinationName(String name) {
        OrganizationName orgName = new OrganizationName();
        orgName.addNamePart(new EntityNamePart(name));
        custodianOrganization.setName(orgName); // CONF-BC0087
        return this;
    }

    // TODO Optional Data Enterer
    // TODO Optional Authenticator and Legal Authenticator
    // TODO Optional Generic Participant

    // TODO Optional Service Event
    // TODO Optional Order
    // TODO Optional relatedDocument
    // TODO Optional Encompassing Encounter

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

    /**
     * Get the document.
     *
     * @return
     */
    public ClinicalDocumentBean get() {
        return doc;
    }

    private Realm getRealmCode() {
        //return new CodedTypeR2<>(CodeResolverRegistry.lookup(Realm.class, "BC-CA")); // CONF-BC0005
        return Realm.BRITISH_COLUMBIA_CANADA; // CONF-BC0005 Todo verify
    }

    private BasicConfidentialityKind getConfidentialityCode() {
        //return new CodedTypeR2<>(CodeResolverRegistry.lookup(BasicConfidentialityKind.class, "N", "2.16.840.1.113883.5.25")); // CONF-BC0028, CONF-BC0503
        return CodeResolverRegistry.lookup(BasicConfidentialityKind.class, "N", "2.16.840.1.113883.5.25"); // CONF-BC0028, CONF-BC0503
    }

    private Language getLanguageCode() {
        //return new CodedTypeR2<>(CodeResolverRegistry.lookup(Language.class, "en-CA")); // CONF-BC0030
        return CodeResolverRegistry.lookup(Language.class, "en-CA"); // CONF-BC0030
    }

    private PersonName createName(String lastName, String... firstNames) {
        PersonName name = new PersonName();
        name.getParts().add(new EntityNamePart(lastName, PersonNamePartType.FAMILY)); // CONF-BC0033
        for (String givenName : firstNames) {
            name.getParts().add(new EntityNamePart(givenName, PersonNamePartType.GIVEN)); // CONF-BC0034
        }
        // TODO verify CONF-BC0035, CONF-BC0036
        name.getUses().add(EntityNameUse.LEGAL); // TODO verify CONF-BC0038
        return name;
    }

    private PostalAddress createAddress(String streetAddress, String city, String state, String postalCode, String country) {
        PostalAddress address = new PostalAddress();
        address.getUses().add(PostalAddressUse.PRIMARY_HOME); // TODO verify CONF-BC0041
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

    private Identifier createIdentifier(String root, String assigningAuthorityName) {
        Identifier id = new Identifier(root);
        id.setAssigningAuthorityName(assigningAuthorityName);
        return id;
    }

    private Identifier createIdentifier(String root, String extension, String assigningAuthorityName) {
        Identifier id = new Identifier(root, extension);
        id.setAssigningAuthorityName(assigningAuthorityName);
        return id;
    }
}
