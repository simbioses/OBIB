package ca.leadlab.obib.doctr;

import ca.infoway.messagebuilder.datatype.lang.CodedTypeR2;
import ca.infoway.messagebuilder.datatype.lang.Identifier;
import ca.infoway.messagebuilder.domainvalue.basic.TelecommunicationAddressUse;
import ca.infoway.messagebuilder.model.ccda_r1_1.domainvalue.x_InformationRecipient;
import ca.infoway.messagebuilder.model.ccda_r1_1.merged.InformationRecipientBean;
import ca.infoway.messagebuilder.model.ccda_r1_1.merged.IntendedRecipientBean;
import ca.infoway.messagebuilder.model.ccda_r1_1.merged.IntendedRecipientOrganizationBean;
import ca.infoway.messagebuilder.model.ccda_r1_1.merged.IntendedRecipientPersonBean;
import ca.infoway.messagebuilder.resolver.CodeResolverRegistry;

public class InformationRecipientCreator {

    private InformationRecipientBean informationRecipient;
    private IntendedRecipientBean intendedRecipient;

    public InformationRecipientCreator() {
        informationRecipient = new InformationRecipientBean(); // CONF-BC0071 TODO validate "at least one PRCP"?
        intendedRecipient = new IntendedRecipientBean(); // TODO verify CONF-BC0074 (classCode = “ASSIGNED”)
        informationRecipient.setIntendedRecipient(intendedRecipient); // CONF-BC0072
    }

    public InformationRecipientCreator primary() {
        // CONF-BC0071
        informationRecipient.setTypeCode(new CodedTypeR2<>(CodeResolverRegistry.lookup(x_InformationRecipient.class, "PRCP")));
        return this;
    }

    public InformationRecipientCreator secondary() {
        // CONF-BC0071
        informationRecipient.setTypeCode(new CodedTypeR2<>(CodeResolverRegistry.lookup(x_InformationRecipient.class, "TRC")));
        return this;
    }

    public InformationRecipientCreator recipientId(String recipientId) {
        // CONF-BC0073 TODO "validate id limit"? verify CONF-BC0075, CONF-BC0076
        intendedRecipient.getId().add(new Identifier("2.16.840.1.113883.3.40.2.11", recipientId));
        return this;
    }

    public InformationRecipientCreator recipientName(String lastName, String firstName) {
        recipientName(lastName, firstName, null);
        return this;
    }

    public InformationRecipientCreator recipientName(String lastName, String firstName, String middleName) {
        IntendedRecipientPersonBean intendedRecipientPerson = new IntendedRecipientPersonBean();
        intendedRecipientPerson.getName().add(DocumentUtils.createName(lastName, firstName, middleName));
        intendedRecipient.setInformationRecipient(intendedRecipientPerson); // CONF-BC0079
        return this;
    }

    public InformationRecipientCreator recipientAddress(String street, String city, String state, String postalCode, String country) {
        intendedRecipient.getAddr().add(DocumentUtils.createAddress(street, city, state, postalCode, country)); // CONF-BC0077
        return this;
    }

    public InformationRecipientCreator recipientTelecom(String phone) {
        intendedRecipient.getTelecom().add(DocumentUtils.createTelecom(phone, TelecommunicationAddressUse.WORKPLACE)); // CONF-BC0078
        return this;
    }

    public InformationRecipientCreator receivedOrganization(String organizationName, String... organizationIds) {
        IntendedRecipientOrganizationBean recipientOrganization = new IntendedRecipientOrganizationBean();
        recipientOrganization.setName(DocumentUtils.createOrganizationName(organizationName)); // CONF-BC0081
        for (String organizationId : organizationIds) {
            // CONF-BC0545 TODO validate "at least on id"?
            recipientOrganization.getId().add(new Identifier("2.16.840.1.113883.3.40.2.11", organizationId));
        }
        intendedRecipient.setReceivedOrganization(recipientOrganization);
        return this;
    }

    public InformationRecipientBean get() {
        return informationRecipient;
    }
}
