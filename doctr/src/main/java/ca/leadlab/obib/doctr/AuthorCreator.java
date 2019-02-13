package ca.leadlab.obib.doctr;

import ca.infoway.messagebuilder.datatype.lang.Identifier;
import ca.infoway.messagebuilder.domainvalue.basic.TelecommunicationAddressUse;
import ca.infoway.messagebuilder.model.ccda_r1_1.merged.AssignedAuthorBean;
import ca.infoway.messagebuilder.model.ccda_r1_1.merged.AssignedAuthorPersonBean;
import ca.infoway.messagebuilder.model.ccda_r1_1.merged.Author_2Bean;
import ca.infoway.messagebuilder.model.ccda_r1_1.merged.AuthoringDeviceBean;

public class AuthorCreator {

    private Author_2Bean author;
    private AssignedAuthorBean assignedAuthor;

    public AuthorCreator() {
        author = new Author_2Bean(); // TODO verify CONF-BC0510 (typeCode = "AUT" and contextControlCode = "OP")
        assignedAuthor = new AssignedAuthorBean(); // TODO verify CONF-BC0511 (classCode = “ASSIGNED”)
        author.setAssignedAuthor(assignedAuthor); // CONF-BC0061
    }

    public AuthorCreator authorTime(int year, int month, int day) {
        author.setTime(DocumentUtils.createDate(year, month, day)); // CONF-BC0059
        return this;
    }

    public AuthorCreator authorId(String authorId) {
        // CONF-BC0062, CONF-BC0063, CONF-BC0064 TODO verify "out of province"
        assignedAuthor.getId().add(new Identifier("2.16.840.1.113883.3.40.2.11", authorId));
        return this;
    }

    public AuthorCreator authorPersonName(String lastName, String firstName) {
        AssignedAuthorPersonBean assignedAuthorPerson = new AssignedAuthorPersonBean();
        assignedAuthorPerson.getName().add(DocumentUtils.createName(lastName, firstName)); // CONF-BC0068
        assignedAuthor.setAssignedAuthorChoice(assignedAuthorPerson); // CONF-BC0065 TODO verify CONF-BC0512 (classCode = “PSN” and determinerCode = “INSTANCE”)
        return this;
    }

    public AuthorCreator authorDeviceName(String software) {
        AuthoringDeviceBean assignedAuthoringDevice = new AuthoringDeviceBean();
        // TODO assignedAuthoringDevice.setSoftwareName(new CodedTypeR2<>(???, software)); // CONF-BC0069 verify how set this name
        assignedAuthor.setAssignedAuthorChoice(assignedAuthoringDevice); // CONF-BC0065 TODO verify CONF-BC0513 (classCode = “DEV” and determinerCode = “INSTANCE”)
        return this;
    }

    public AuthorCreator authorAddress(String street, String city, String state, String postalCode, String country) {
        assignedAuthor.getAddr().add(DocumentUtils.createAddress(street, city, state, postalCode, country)); // CONF-BC0066
        return this;
    }

    public AuthorCreator authorTelecom(String phone) {
        assignedAuthor.getTelecom().add(DocumentUtils.createTelecom(phone, TelecommunicationAddressUse.WORKPLACE)); // CONF-BC0067
        return this;
    }

    public Author_2Bean get() {
        return author;
    }
}
