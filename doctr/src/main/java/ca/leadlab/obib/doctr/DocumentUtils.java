package ca.leadlab.obib.doctr;

import ca.infoway.messagebuilder.datatype.lang.*;
import ca.infoway.messagebuilder.datatype.lang.util.DateWithPattern;
import ca.infoway.messagebuilder.datatype.lang.util.PersonNamePartType;
import ca.infoway.messagebuilder.datatype.lang.util.PostalAddressPartType;
import ca.infoway.messagebuilder.domainvalue.basic.EntityNameUse;
import ca.infoway.messagebuilder.domainvalue.basic.PostalAddressUse;
import ca.infoway.messagebuilder.domainvalue.basic.TelecommunicationAddressUse;
import ca.infoway.messagebuilder.domainvalue.basic.URLScheme;
import ca.infoway.messagebuilder.j5goodies.DateUtil;
import ca.infoway.messagebuilder.model.ccda_r1_1.domainvalue.BasicConfidentialityKind;
import ca.infoway.messagebuilder.model.ccda_r1_1.domainvalue.Language;
import ca.infoway.messagebuilder.resolver.CodeResolverRegistry;

import java.util.TimeZone;

public class DocumentUtils {

    static Realm getRealmCode() {
        //return new CodedTypeR2<>(CodeResolverRegistry.lookup(Realm.class, "CA-BC")); // CONF-BC0005
        return Realm.BRITISH_COLUMBIA_CANADA; // CONF-BC0005 Todo verify
    }

    static CodedTypeR2<BasicConfidentialityKind> getConfidentialityCode() {
        return new CodedTypeR2<>(CodeResolverRegistry.lookup(BasicConfidentialityKind.class, "N", "2.16.840.1.113883.5.25")); // CONF-BC0028, CONF-BC0503
    }

    static CodedTypeR2<Language> getLanguageCode() {
        return new CodedTypeR2<>(CodeResolverRegistry.lookup(Language.class, "en-CA")); // CONF-BC0030
    }

    static OrganizationName createOrganizationName(String name) {
        OrganizationName organizationName = new OrganizationName();
        organizationName.addNamePart(new EntityNamePart(name));
        return organizationName;
    }

    static PersonName createName(String lastName, String... firstNames) {
        PersonName name = new PersonName();
        name.getParts().add(new EntityNamePart(lastName, PersonNamePartType.FAMILY)); // CONF-BC0033
        for (String givenName : firstNames) {
            name.getParts().add(new EntityNamePart(givenName, PersonNamePartType.GIVEN)); // CONF-BC0034
        }
        // TODO verify CONF-BC0035, CONF-BC0036
        name.getUses().add(EntityNameUse.LEGAL); // TODO verify CONF-BC0038
        return name;
    }

    static PostalAddress createAddress(String streetAddress, String city, String state, String postalCode, String country) {
        PostalAddress address = new PostalAddress();
        address.getUses().add(PostalAddressUse.PRIMARY_HOME); // TODO verify CONF-BC0041
        address.getParts().add(new PostalAddressPart(PostalAddressPartType.STREET_ADDRESS_LINE, streetAddress));
        address.getParts().add(new PostalAddressPart(PostalAddressPartType.CITY, city));
        address.getParts().add(new PostalAddressPart(PostalAddressPartType.STATE, state));
        address.getParts().add(new PostalAddressPart(PostalAddressPartType.POSTAL_CODE, postalCode));
        address.getParts().add(new PostalAddressPart(PostalAddressPartType.COUNTRY, country));
        return address;
    }

    static TelecommunicationAddress createTelecom(String number, TelecommunicationAddressUse use) {
        return new TelecommunicationAddress(URLScheme.TEL, number, use);
    }

    static MbDate createDate(int year, int month, int day) {
        return new MbDate(new DateWithPattern(DateUtil.getDate(year, month, day), "yyyyMMdd"));
    }

    static MbDate createDateTime(int year, int month, int day, int hour, int minute, TimeZone timeZone) {
        return new MbDate(new DateWithPattern(DateUtil.getDate(year, month, day, hour, minute, 0, 0, timeZone), "yyyyMMddhhmmZ"));
    }

    static Identifier createIdentifier(String root, String assigningAuthorityName) {
        Identifier id = new Identifier(root);
        id.setAssigningAuthorityName(assigningAuthorityName);
        return id;
    }

    static Identifier createIdentifier(String root, String extension, String assigningAuthorityName) {
        Identifier id = new Identifier(root, extension);
        id.setAssigningAuthorityName(assigningAuthorityName);
        return id;
    }
}
