package ca.leadlab.obib.doctr;

import ca.infoway.messagebuilder.datatype.lang.CodedTypeR2;
import ca.infoway.messagebuilder.domainvalue.basic.TelecommunicationAddressUse;
import ca.infoway.messagebuilder.domainvalue.payload.AdministrativeGender;
import ca.infoway.messagebuilder.model.ccda_r1_1.merged.PatientBean;
import ca.infoway.messagebuilder.model.ccda_r1_1.merged.PatientRoleBean;
import ca.infoway.messagebuilder.model.ccda_r1_1.merged.RecordTargetBean;
import ca.infoway.messagebuilder.resolver.CodeResolverRegistry;

import java.util.Date;

public class RecordTargetCreator {

    private RecordTargetBean recordTarget;
    private PatientRoleBean patientRole;
    private PatientBean patient;

    public RecordTargetCreator() {
        recordTarget = new RecordTargetBean(); // TODO verify CONF-BC0507 (typeCode = “RCT” and contextControlCode = "OP")
        patientRole = new PatientRoleBean(); // TODO verify CONF-BC0508 (classCode = "PAT")
        patient = new PatientBean(); // TODO verify CONF-BC0509 (classCode = “PSN” and determinerCode = “INSTANCE”)
        patientRole.setPatient(patient); // CONF-BC0053
        recordTarget.setPatientRole(patientRole); // CONF-BC0047
    }

    public RecordTargetCreator patientId(String patientId) {
        // CONF-BC0048, CONF-BC0049, CONF-BC0050 TODO verify "out of province"
        patientRole.getId().add(DocumentUtils.createIdentifier("2.16.840.1.113883.4.50", patientId, "BC Patient Health Number"));
        return this;
    }

    public RecordTargetCreator patientAddress(String street, String city, String state, String postalCode, String country) {
        patientRole.getAddr().add(DocumentUtils.createAddress(street, city, state, postalCode, country)); // CONF-BC0051
        return this;
    }

    public RecordTargetCreator patientTelecom(String phone) {
        patientRole.getTelecom().add(DocumentUtils.createTelecom(phone, TelecommunicationAddressUse.PRIMARY_HOME)); // CONF-BC0052
        return this;
    }

    public RecordTargetCreator patientName(String lastName, String firstName) {
        patientName(lastName, firstName, null);
        return this;
    }

    public RecordTargetCreator patientName(String lastName, String firstName, String middleName) {
        patient.getName().add(DocumentUtils.createName(lastName, firstName, middleName)); // CONF-BC0054
        return this;
    }

    public RecordTargetCreator patientGender(String gender) {
        // CONF-BC0055 TODO verify "default nullFlavor"
        patient.setAdministrativeGenderCode(new CodedTypeR2<>(CodeResolverRegistry.lookup(AdministrativeGender.class, gender)));
        return this;
    }

    public RecordTargetCreator patientDOB(int year, int month, int day) {
        patient.setBirthTime(DocumentUtils.createDate(year, month, day)); // CONF-BC0056
        return this;
    }

    public RecordTargetCreator patientDOB(Date date) {
        patient.setBirthTime(DocumentUtils.createDate(date)); // CONF-BC0056
        return this;
    }

    // TODO Optional CONF-BC0057 - languageCommunication or "default nullFlavor"

    public RecordTargetBean get() {
        return recordTarget;
    }
}
