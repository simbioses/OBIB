package ca.leadlab.obib.doctr;

import ca.infoway.messagebuilder.datatype.lang.*;
import ca.infoway.messagebuilder.domainvalue.x_DocumentMediaType;

import ca.infoway.messagebuilder.model.ClinicalDocumentBean;
import ca.infoway.messagebuilder.model.ccda_r1_1.basemodel.NonXMLBodyBean;
import ca.infoway.messagebuilder.model.ccda_r1_1.consultationnote.ConsultationNote;
import ca.infoway.messagebuilder.model.ccda_r1_1.consultationnote.Component2Bean;
import ca.infoway.messagebuilder.model.ccda_r1_1.domainvalue.ConsultDocumentType;
import ca.infoway.messagebuilder.model.ccda_r1_1.merged.*;
import ca.infoway.messagebuilder.resolver.CodeResolverRegistry;
import ca.infoway.messagebuilder.resolver.configurator.DefaultCodeResolutionConfigurator;
import org.xml.sax.SAXException;

import java.util.TimeZone;

public class ReferralRequestCreator {

    private ConsultationNote doc;

    private CustodianBean custodian;
    private AssignedCustodianBean assignedCustodian;
    private CustodianOrganizationBean custodianOrganization;

    private NonXMLBodyBean body;
    private Component2Bean component;

    public ReferralRequestCreator() {
        // Relaxes code vocabulary code checks and sets up some basic code resolvers
        DefaultCodeResolutionConfigurator.configureCodeResolversWithTrivialDefault();

        // Document Header
        doc = new ConsultationNote(); // CONF-BC0001, TODO verify CONF-BC0502
        // CONF-BC002, CONF-BC003, CONF-BC004
        doc.setTypeId(DocumentUtils.createIdentifier("2.16.840.1.113883.1.3", "POCD_HD000040", "HL7 CDA R2"));
        doc.addRealmCode(DocumentUtils.getRealmCode()); // CONF-BC0005
        doc.setConfidentialityCode(DocumentUtils.getConfidentialityCode()); // CONF-BC0027
        doc.setLanguageCode(DocumentUtils.getLanguageCode()); // CONF-BC0029
        templateId(TemplateId.E2E_UNSTRUCTURED_REFERRAL); // TemplateId for Unstructured Referral

        // Custodian
        custodian = new CustodianBean(); // TODO verify CONF-BC0083
        assignedCustodian = new AssignedCustodianBean(); // TODO verify CONF-BC0514
        custodianOrganization = new CustodianOrganizationBean(); // TODO verify CONF-BC0515
        assignedCustodian.setRepresentedCustodianOrganization(custodianOrganization); // CONF-BC0085
        custodian.setAssignedCustodian(assignedCustodian); // CONF-BC0084
        doc.setCustodian(custodian); // CONF-BC0082

        // Document Body
        component = new Component2Bean();
        body = new NonXMLBodyBean();
        component.setComponent2Choice(body);
        doc.setComponent(component);
    }

    public ReferralRequestCreator templateId(TemplateId templateId) {
        return templateId(templateId.oid(), templateId.title());
    }

    public ReferralRequestCreator templateId(String rootId, String assigningAuthorityName) {
        doc.getTemplateId().add(DocumentUtils.createIdentifier(rootId, assigningAuthorityName));
        return this;
    }

    public ReferralRequestCreator docId(String docId) {
        // CONF-BC0014, CONF-BC0015
        doc.setId(DocumentUtils.createIdentifier("2.16.840.1.113883.3.277.100.3", docId, "CDX Clinical Document ID"));
        return this;
    }

    // TODO Optional ClinicalDocument/setId
    // TODO Optional ClinicalDocument/versionNumber
    // TODO Optional ClinicalDocument/relatedDocument.typeCode

    public ReferralRequestCreator loincCode(String code, String displayName) {
        CodedTypeR2<ConsultDocumentType> loincCode = new CodedTypeR2<>(CodeResolverRegistry.lookup(ConsultDocumentType.class, code, "2.16.840.1.113883.6.1"));
        loincCode.setCodeSystemName("LOINC Code");
        loincCode.setDisplayName(displayName);
        doc.setCode(loincCode); // CONF-BC0021, TODO CONF-BC0022 get from vox.xml?
        doc.setTitle(loincCode.getDisplayName()); // CONF-BC0023, CONF-BC0024
        return this;
    }

    public ReferralRequestCreator effectiveTime(int year, int month, int day, int hour, int minute, TimeZone timeZone) {
        doc.setEffectiveTime(DocumentUtils.createDateTime(year, month, day, hour, minute, timeZone)); // CONF-BC0025, CONF-BC0026
        return this;
    }

    // TODO Optional ClinicalDocument/documentationOf

    public ReferralRequestCreator recordTarget(RecordTargetCreator recordTargetCreator) {
        doc.getRecordTarget().add(recordTargetCreator.get()); // CONF-BC0047
        return this;
    }

    // TODO Optional patient/languageCommunication

    public ReferralRequestCreator author(AuthorCreator authorCreator) {
        doc.getAuthor().add(authorCreator.get()); // CONF-BC0058
        return this;
    }

    public ReferralRequestCreator informationRecipient(InformationRecipientCreator informationRecipientCreator) {
        doc.getInformationRecipient().add(informationRecipientCreator.get()); // CONF-BC0070
        return this;
    }

    public ReferralRequestCreator custodianOrganizationId(String organizationId) {
        custodianOrganization.getId().add(new Identifier("2.16.840.1.113883.3.277.1.62", organizationId)); // CONF-BC0086 TODO verify Health Authority vs EMR
        return this;
    }

    public ReferralRequestCreator custodianOrganizationName(String organizationName) {
        custodianOrganization.setName(DocumentUtils.createOrganizationName(organizationName)); // CONF-BC0087
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
}
