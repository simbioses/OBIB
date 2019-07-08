package ca.uvic.leadlab.obibconnector.impl.receive.mock;

import ca.uvic.leadlab.obibconnector.facades.datatypes.DocumentStatus;
import ca.uvic.leadlab.obibconnector.facades.receive.IAttachment;
import ca.uvic.leadlab.obibconnector.facades.receive.IDistributionStatus;
import ca.uvic.leadlab.obibconnector.facades.receive.IDocument;
import ca.uvic.leadlab.obibconnector.facades.receive.IPatient;
import ca.uvic.leadlab.obibconnector.facades.registry.IProvider;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DocumentMock1 implements IDocument {

    @Override
    public String getTemplateID() {
        return "2.16.840.1.113883.10.20.7";
    }

    @Override
    public String getTemplateName() {
        return "Operative Note";
    }

    @Override
    public String getDocumentID() {
        return "11f5f3e4-aeae-4507-8ceb-ede4afb21234";
    }

    @Override
    public int getVersion() {
        return 1;
    }

    @Override
    public String getSetId() {
        return "1";
    }

    @Override
    public String getInFulFillmentOfId() {
        return null;
    }

    @Override
    public String getLoincCode() {
        return "34117-2";
    }

    @Override
    public String getLoincCodeDisplayName() {
        return "History & Physical Note";
    }

    @Override
    public String getTitle() {
        return "History & Physical Note";
    }

    @Override
    public IPatient getPatient() {
        return new PatientJens();
    }

    @Override
    public IProvider getAuthor() {
        return new ProviderRaymond();
    }

    @Override
    public Date getAuthoringTime() {
        return new Date(300);
    }

    @Override
    public String getAuthorDevice() {
        return "Meditech Software Device";
    }

    @Override
    public Date getAuthorDeviceTime() {
        return new Date(5000);
    }

    @Override
    public Date getEffectiveTime() {

            return new Date(5000);
    }

    @Override
    public String getCustodianName() {
        return "Interior Health Authority";
    }

    @Override
    public IProvider getPrimaryRecipient() {
        return new ProviderAdeshina();
    }

    @Override
    public List<IProvider> getSecondaryRecipients() {
        return new ArrayList<>();
    }

    @Override
    public IProvider getOrderingProvider() {
        return new ProviderRaymond();
    }

    @Override
    public IProvider getFamilyProvider() {
        return null;
    }

    @Override
    public String getOrderID() {
        return "123456789";
    }

    @Override
    public DocumentStatus getStatusCode() {
        return DocumentStatus.COMPLETED;
    }

    @Override
    public Date getObservationDate() {
        SimpleDateFormat sdf = new SimpleDateFormat ( "dd-mm-yyyy" ) ;

        Date result = null;

        try {
            sdf.parse("01-01-2019");
        } catch (Exception e) {
            // should never happen
        }

        return result;
    }

    @Override
    public String getProcedureName() {
        return "Elbow LT";
    }

    @Override
    public IProvider getProcedurePerformer() {
        return new ProviderRaymond();
    }

    @Override
    public String getParentDocumentID() {
        return "ITS123456789";
    }

    @Override
    public String getPatientEncounterID() {
        return "KA0108436/17";
    }

    @Override
    public Date getAdmissionDate() {
        SimpleDateFormat sdf = new SimpleDateFormat ( "dd-mm-yyyy" ) ;

        Date result = null;

        try {
            sdf.parse("02-02-2019");
        } catch (Exception e) {
            // should never happen
        }

        return result;
    }

    @Override
    public Date getDischargeDate() {
        SimpleDateFormat sdf = new SimpleDateFormat ( "dd-mm-yyyy" ) ;

        Date result = null;

        try {
            sdf.parse("03-03-2019");
        } catch (Exception e) {
            // should never happen
        }

        return result;
    }

    @Override
    public String getDischargeDisposition() {
        return "Discharge to home";
    }

    @Override
    public List<IProvider> getParticipatingProviders() {
        List<IProvider> result = new ArrayList<>();
        result.add(new ProviderRaymond());
        result.add(new ProviderAdeshina());
        return result;
    }

    @Override
    public String getContents() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<ClinicalDocument classCode=\"DOCCLIN\" moodCode=\"EVN\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"urn:hl7-org:v3\" xmlns:bccda=\"urn:bccda\" xsi:schemaLocation=\"urn:hl7-org:v3 CDA.xsd\">\n" +
                "\t<!--\n" +
                "********************************************************\n" +
                "CDA Header\n" +
                "********************************************************\n" +
                "-->\n" +
                "\t<realmCode code=\"CA-BC\"/>\n" +
                "\t<typeId root=\"2.16.840.1.113883.1.3\" extension=\"POCD_HD000040\" assigningAuthorityName=\"HL7 CDA R2\"/>\n" +
                "\t<templateId root=\"2.16.840.1.113883.3.51.60.2.1\" assigningAuthorityName=\"General Lab Report template\"/>\n" +
                "\t<id root=\"2.16.840.1.113883.3.277.100.3\" extension=\"eae2b9e6-321c-4957-8b6f-4a3513ce99eb\" assigningAuthorityName=\"CDX Clinical Document ID\"/>\n" +
                "\t<code codeSystem=\"2.16.840.1.113883.6.1\" code=\"11502-2\" displayName=\"General Lab Report\"/>\n" +
                "\t<title>General Lab Report</title>\n" +
                "\t<effectiveTime value=\"201311071138-0800\"/>\n" +
                "\t<confidentialityCode code=\"N\" codeSystem=\"2.16.840.1.113883.5.25\" codeSystemName=\"Confidentiality\"/>\n" +
                "\t<languageCode code=\"en-CA\"/>\n" +
                "\t<!-- ==== Document versioning Information ==== -->\n" +
                "\t<setId root=\"2.16.840.1.113883.3.277.3.4.2\" extension=\"bba5c335-01d2-4439-880f-feb464fdcbac\" assigningAuthorityName=\"Clinical Document Revision Number\"/>\n" +
                "\t<versionNumber value=\"1\" />\t\n" +
                "\t<!-- ==== Patient Information ==== -->\n" +
                "\t<recordTarget typeCode=\"RCT\" contextControlCode=\"OP\">\n" +
                "\t\t<patientRole classCode=\"PAT\">\n" +
                "\t\t\t<id root=\"2.16.840.1.113883.4.50\" extension=\"9191610101\" assigningAuthorityName=\"BC Patient Health Number\"/>\n" +
                "\t\t\t<addr use=\"H\">8423 Panko Pvt<delimiter/>\n" +
                "\t\t\t\t<city>Here</city>\n" +
                "\t\t\t\t<state>CA-BC</state>\n" +
                "\t\t\t\t<postalCode>XXX XXX</postalCode>\n" +
                "\t\t\t</addr>\n" +
                "\t\t\t<telecom use=\"H\" value=\"tel:111-1111\"/>\n" +
                "\t\t\t<patient classCode=\"PSN\" determinerCode=\"INSTANCE\">\n" +
                "\t\t\t\t<name use=\"L\">\n" +
                "\t\t\t\t\t<family>Ihhendrickson</family>\n" +
                "\t\t\t\t\t<given>Plis</given>\n" +
                "\t\t\t\t</name>\n" +
                "\t\t\t\t<administrativeGenderCode code=\"M\"/>\n" +
                "\t\t\t\t<birthTime value=\"19691202\"/>\n" +
                "\t\t\t</patient>\n" +
                "\t\t</patientRole>\n" +
                "\t</recordTarget>\n" +
                "\t<!-- ==== Author: Person and/or software that created this document ==== -->\n" +
                "\t<author typeCode=\"AUT\" contextControlCode=\"OP\">\n" +
                "\t\t<time value=\"201311071004-0800\"/>\n" +
                "\t\t<assignedAuthor classCode=\"ASSIGNED\">\n" +
                "\t\t\t<id root=\"2.16.840.1.113883.3.277.1.81\" extension=\"D2863329\" assigningAuthorityName=\"IHA Message Number\"/>\n" +
                "\t\t\t<assignedAuthoringDevice classCode=\"DEV\" determinerCode=\"INSTANCE\">\n" +
                "\t\t\t\t<softwareName code=\"LAB_ABC\" codeSystem=\"2.16.840.1.113883.3.277.1.12\" codeSystemName=\"IHA Software Code\"/>\n" +
                "\t\t\t</assignedAuthoringDevice>\n" +
                "\t\t</assignedAuthor>\n" +
                "\t</author>\n" +
                "\t<!--  ==== Custodian: organization responsible for this document ==== -->\n" +
                "\t<custodian typeCode=\"CST\">\n" +
                "\t\t<assignedCustodian classCode=\"ASSIGNED\">\n" +
                "\t\t\t<representedCustodianOrganization classCode=\"ORG\" determinerCode=\"INSTANCE\">\n" +
                "\t\t\t\t<id root=\"2.16.840.1.113883.3.277.1.62\" extension=\"ABC\" assigningAuthorityName=\"IHA Meditech Location Identifier\"/>\n" +
                "\t\t\t</representedCustodianOrganization>\n" +
                "\t\t</assignedCustodian>\n" +
                "\t</custodian>\n" +
                "\t<!-- ==== Order information ==== -->\n" +
                "\t<inFulfillmentOf typeCode=\"FLFS\">\n" +
                "\t\t<order classCode=\"ENC\" moodCode=\"RQO\">\n" +
                "\t\t\t<id root=\"2.16.840.1.113883.3.277.1.22\" extension=\"00050252\" assigningAuthorityName=\"IHA Order Number (Requisition Number)\"/>\n" +
                "\t\t\t<!-- ==== Being used as a statusCode for this order ==== -->\n" +
                "\t\t\t<code code=\"active\" codeSystem=\"statusCode\" displayName=\"statusCode\" />\t\t\t\n" +
                "\t\t</order>\n" +
                "\t</inFulfillmentOf>\n" +
                "\t<!-- ==== Service Event information ==== -->\n" +
                "\t<documentationOf typeCode=\"DOC\">\n" +
                "\t\t<serviceEvent classCode=\"ACT\" moodCode=\"EVN\">\n" +
                "\t\t\t<code code=\"71388002\" codeSystem=\"2.16.840.1.113883.6.96\" codeSystemName=\"SNOMED CT\"/>\n" +
                "\t\t\t<effectiveTime value=\"201207261453-0700\"/>\n" +
                "\t\t\t<performer typeCode=\"PPRF\">\n" +
                "\t\t\t\t<assignedEntity classCode=\"ASSIGNED\">\n" +
                "\t\t\t\t\t<id nullFlavor=\"NI\"/>\n" +
                "\t\t\t\t\t<representedOrganization classCode=\"ORG\" determinerCode=\"INSTANCE\">\n" +
                "\t\t\t\t\t\t<id root=\"2.16.840.1.113883.3.277.1.62\" extension=\"IHKGH\" assigningAuthorityName=\"IHA Lab Provider\"/>\n" +
                "\t\t\t\t\t\t<name>Kelowna General Hosp</name>\n" +
                "\t\t\t\t\t</representedOrganization>\n" +
                "\t\t\t\t</assignedEntity>\n" +
                "\t\t\t</performer>\n" +
                "\t\t\t<!-- ==== CDA extension element to allow for status of serviceEvent (active, completed, aborted) ==== -->\n" +
                "\t\t\t<bccda:statusCode code=\"active\"/>\n" +
                "\t\t</serviceEvent>\n" +
                "\t</documentationOf>\n" +
                "\t<!-- ==== Parent Document: HL7 v2 message from Meditech ==== -->\n" +
                "\t<relatedDocument typeCode=\"RPLC\">\n" +
                "\t\t<parentDocument classCode=\"DOCCLIN\" moodCode=\"EVN\">\n" +
                "\t\t\t<id root=\"2.16.840.1.113883.3.277.1.81\" extension=\"D2863329\" assigningAuthorityName=\"IHA Message Number\"/>\n" +
                "\t\t\t<!-- ==== versioning information ==== -->\n" +
                "\t\t\t<setId root=\"2.16.840.1.113883.3.277.3.4.2\" extension=\"bba5c335-01d2-4439-880f-feb464fdcbac\" assigningAuthorityName=\"Clinical Document Revision Number\"/>\n" +
                "\t\t\t<versionNumber value=\"1\" />\n" +
                "\t\t</parentDocument>\n" +
                "\t</relatedDocument>\n" +
                "\t<!-- ==== Encompassing Encounter: Patient Visit ==== -->\n" +
                "\t<componentOf typeCode=\"COMP\">\n" +
                "\t\t<encompassingEncounter classCode=\"ENC\" moodCode=\"EVN\">\n" +
                "\t\t\t<id root=\"2.16.840.1.113883.3.277.1.72\" extension=\"xx0055355x3\" assigningAuthorityName=\"IHA Patient Account Number\"/>\n" +
                "\t\t\t<!--Encounter has an admission date only-->\n" +
                "\t\t\t<effectiveTime value=\"201207261453-0700\"/>\n" +
                "\t\t\t<encounterParticipant typeCode=\"ATND\">\n" +
                "\t\t\t\t<assignedEntity classCode=\"ASSIGNED\">\n" +
                "\t\t\t\t\t<id root=\"2.16.840.1.113883.3.40.2.11\" extension=\"93190\" assigningAuthorityName=\"BC MSP Provider License Number\"/>\n" +
                "\t\t\t\t\t<id root=\"2.16.840.1.113883.3.277.1.61\" extension=\"xxxxxx\" assigningAuthorityName=\"IHA Provider Code: IHA-MT PVD-ID\"/>\n" +
                "\t\t\t\t\t<assignedPerson classCode=\"PSN\" determinerCode=\"INSTANCE\">\n" +
                "\t\t\t\t\t\t<name use=\"L\">\n" +
                "\t\t\t\t\t\t\t<prefix>Dr</prefix>\n" +
                "\t\t\t\t\t\t\t<family>Plisihd</family>\n" +
                "\t\t\t\t\t\t\t<given>xxxxxx</given>\n" +
                "\t\t\t\t\t\t</name>\n" +
                "\t\t\t\t\t</assignedPerson>\n" +
                "\t\t\t\t</assignedEntity>\n" +
                "\t\t\t</encounterParticipant>\n" +
                "\t\t\t<location>\n" +
                "\t\t\t\t<healthCareFacility classCode=\"SDLOC\">\n" +
                "\t\t\t\t\t<id root=\"2.16.840.1.113883.3.277.1.62\" extension=\"xxxxx\" assigningAuthorityName=\"IHA Meditech Location Identifier\"/>\n" +
                "\t\t\t\t\t<!--code represents the patient location, in the form \"Pt.Type:Unit[:Room[:Bed]]\"-->\n" +
                "\t\t\t\t\t<code code=\"RCR:TRAKBHLB\" codeSystemName=\"Patient Type:Unit\"/>\n" +
                "\t\t\t\t</healthCareFacility>\n" +
                "\t\t\t</location>\n" +
                "\t\t</encompassingEncounter>\n" +
                "\t</componentOf>\n" +
                "\t<!-- \n" +
                "********************************************************\n" +
                "CDA Body: Level 3 - Discrete Lab Results  \n" +
                "********************************************************\n" +
                "-->\n" +
                "\t<component typeCode=\"COMP\">\n" +
                "\t\t<structuredBody classCode=\"DOCBODY\" moodCode=\"EVN\">\n" +
                "\t\t\t<component typeCode=\"COMP\">\n" +
                "\t\t\t\t<section classCode=\"DOCSECT\" moodCode=\"EVN\">\n" +
                "\t\t\t\t\t<templateId root=\"1.3.6.1.4.1.19376.1.3.3.2.1\" assigningAuthorityName=\"Laboratory Specialty Section\"/>\n" +
                "\t\t\t\t\t<code codeSystem=\"2.16.840.1.113883.6.1\" codeSystemName=\"LOINC Code\" code=\"26436-6\" displayName=\"Laboratory Studies\"/>\n" +
                "\t\t\t\t\t<title>Laboratory Studies</title>\n" +
                "\t\t\t\t\t<component typeCode=\"COMP\">\n" +
                "\t\t\t\t\t\t<section classCode=\"DOCSECT\" moodCode=\"EVN\">\n" +
                "\t\t\t\t\t\t\t<templateId root=\"1.3.6.1.4.1.19376.1.3.3.2.2\" assigningAuthorityName=\"Laboratory Report Item Section\"/>\n" +
                "\t\t\t\t\t\t\t<code codeSystem=\"2.16.840.1.113883.2.20.5.1\" codeSystemName=\"pCLOCD\" code=\"34532-2\" displayName=\"Blood Type &amp; Indirect Antibody Screen\"/>\n" +
                "\t\t\t\t\t\t\t<!-- ==== Derived Text Representation of Discrete Lab Results ==== -->\n" +
                "\t\t\t\t\t\t\t<title>Blood Type &amp; Indirect Antibody Screen</title>\n" +
                "\t\t\t\t\t\t\t<text>\n" +
                "\t\t\t\t\t\t\t\t<table>\n" +
                "\t\t\t\t\t\t\t\t\t<caption>Specimen Information</caption>\n" +
                "\t\t\t\t\t\t\t\t\t<tbody>\n" +
                "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<th>Lab #</th>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td>PT0711:T00001R</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<th>Collected:</th>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td>07/Nov/2013 10:04 PST</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<th>Received:</th>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td>07/Nov/2013 10:04 PST</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t</tbody>\n" +
                "\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t<br/>\n" +
                "\t\t\t\t\t\t\t\t<table>\n" +
                "\t\t\t\t\t\t\t\t\t<caption>34532-2 Blood Type &amp; Indirect Antibody Screen</caption>\n" +
                "\t\t\t\t\t\t\t\t\t<tbody>\n" +
                "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<th>Comments</th>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td colspan=\"7\">Date required: 20131107<br/>Any previous transfusions? Unknown<br/>Specify any underlying blood disorders: U<br/>Has consent for blood products been obtained? Yes<br/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td colspan=\"8\"/>\n" +
                "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<th>Test ID:</th>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<th>Test Name:</th>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<th>Test Result:</th>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<th>Result Flags:</th>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<th>Reference Range:</th>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<th>Result Units:</th>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<th>Time Resulted:</th>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<th>Status:</th>\n" +
                "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t<tr ID=\"p882-1_1-1\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td>882-1</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td>Blood Type &amp; Indirect Antibody Screen</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td>OPOS</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td>07/Nov/2013 11:37 PST</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td>completed</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t<tr ID=\"pXBC1931-6_2-1\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td>XBC1931-6</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td>Blood Type &amp; Indirect Antibody Screen</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td>NEGATIVE</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td>07/Nov/2013 11:38 PST</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td>completed</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t</tbody>\n" +
                "\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t<br/>\n" +
                "\t\t\t\t\t\t\t\t<br/>\n" +
                "\t\t\t\t\t\t\t\t<table>\n" +
                "\t\t\t\t\t\t\t\t\t<tbody>\n" +
                "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<th>Result Flags Legend:</th>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<content styleCode=\"alert\">H</content>/<content styleCode=\"alert\">L</content>/<content styleCode=\"alert\">A</content>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<br/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<content styleCode=\"alert\">HH</content>/<content styleCode=\"alert\">LL</content>/<content styleCode=\"alert\">AA</content>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td>Abnormal Value<br/>Critical Value</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<th>Performing Lab:</th>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td colspan=\"2\">xx (xx)</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<th>Report Status:</th>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td colspan=\"2\">completed</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t</tbody>\n" +
                "\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t</text>\n" +
                "\t\t\t\t\t\t\t<!-- ==== Machine Readable HL7 V3 Representation of Discrete Lab Results ==== -->\n" +
                "\t\t\t\t\t\t\t<entry typeCode=\"DRIV\">\n" +
                "\t\t\t\t\t\t\t\t<templateId root=\"1.3.6.1.4.1.19376.1.3.1\" assigningAuthorityName=\"Laboratory Report Data Processing Entry\"/>\n" +
                "\t\t\t\t\t\t\t\t<act classCode=\"ACT\" moodCode=\"EVN\">\n" +
                "\t\t\t\t\t\t\t\t\t<code nullFlavor=\"NA\"/>\n" +
                "\t\t\t\t\t\t\t\t\t<statusCode code=\"NI\"/>\n" +
                "\t\t\t\t\t\t\t\t\t<!--Specimen Collection: PT0711:T00001R-->\n" +
                "\t\t\t\t\t\t\t\t\t<entryRelationship typeCode=\"COMP\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<procedure classCode=\"PROC\" moodCode=\"EVN\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<templateId root=\"1.3.6.1.4.1.19376.1.3.1.2\" assigningAuthorityName=\"Specimen Collection\"/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<code codeSystem=\"2.16.840.1.113883.6.1\" codeSystemName=\"LOINC Code\" code=\"33882-2\" displayName=\"Specimen Collection\"/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<effectiveTime value=\"201311071004-0800\"/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<targetSiteCode code=\"T\"/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<participant typeCode=\"PRD\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<participantRole classCode=\"SPEC\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<id root=\"2.16.840.1.113883.3.277.1.11\" extension=\"PT0711:T00001R\" assigningAuthorityName=\"IHA Test/Specimen Number (OBR.3 Filler Order Number)\"/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<playingEntity>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<code nullFlavor=\"UNK\"/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t</playingEntity>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</participantRole>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</participant>\n" +
                "\t\t\t\t\t\t\t\t\t\t</procedure>\n" +
                "\t\t\t\t\t\t\t\t\t</entryRelationship>\n" +
                "\t\t\t\t\t\t\t\t\t<!-- Battery: Blood Type & Indirect Antibody Screen -->\n" +
                "\t\t\t\t\t\t\t\t\t<entryRelationship typeCode=\"COMP\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<organizer classCode=\"BATTERY\" moodCode=\"EVN\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<templateId root=\"1.3.6.1.4.1.19376.1.3.1.4\" assigningAuthorityName=\"Laboratory Battery Organizer\"/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<id root=\"2.16.840.1.113883.3.277.1.11\" extension=\"PT0711:T00001R\" assigningAuthorityName=\"IHA Test/Specimen Number (OBR.3 Filler Order Number)\"/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<code codeSystem=\"2.16.840.1.113883.2.20.5.1\" codeSystemName=\"pCLOCD\" code=\"34532-2\" displayName=\"Blood Type &amp; Indirect Antibody Screen\"/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<statusCode code=\"completed\"/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<effectiveTime value=\"201311071004-0800\"/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--Associated Battery Observation Annotation-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<component typeCode=\"COMP\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<act classCode=\"ACT\" moodCode=\"EVN\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<templateId root=\"1.3.6.1.4.1.19376.1.5.3.1.4.2\" assigningAuthorityName=\"Annotation Comment\"/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<code codeSystem=\"2.16.840.1.113883.6.1\" codeSystemName=\"LOINC Code\" code=\"48767-8\" displayName=\"Annotation Comment\"/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<text>Date required: 20131107\n" +
                "\t\t\tAny previous transfusions? Unknown\n" +
                "\t\t\tSpecify any underlying blood disorders: U\n" +
                "\t\t\tHas consent for blood products been obtained? Yes</text>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<statusCode code=\"completed\"/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</act>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</component>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--1 - Blood Type & Indirect Antibody Screen-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<component typeCode=\"COMP\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<observation classCode=\"OBS\" moodCode=\"EVN\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<templateId root=\"1.3.6.1.4.1.19376.1.3.1.6\" assigningAuthorityName=\"Laboratory Observation\"/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<code codeSystem=\"2.16.840.1.113883.2.20.5.1\" codeSystemName=\"pCLOCD\" code=\"882-1\" displayName=\"Blood Type &amp; Indirect Antibody Screen\"/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<text>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<reference value=\"#p882-1_1-1\"/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t</text>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<statusCode code=\"NI\"/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<effectiveTime value=\"201311071137-0800\"/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<value mediaType=\"text/plain\" representation=\"TXT\" xsi:type=\"ST\">OPOS</value>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<performer typeCode=\"PRF\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<assignedEntity classCode=\"ASSIGNED\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<id nullFlavor=\"NI\"/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<representedOrganization classCode=\"ORG\" determinerCode=\"INSTANCE\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<id root=\"2.16.840.1.113883.3.277.1.62\" extension=\"xx\" assigningAuthorityName=\"IHA Lab Provider\"/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<name>xx</name>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</representedOrganization>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</assignedEntity>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t</performer>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</observation>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</component>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<!--2 - Blood Type & Indirect Antibody Screen-->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<component typeCode=\"COMP\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<observation classCode=\"OBS\" moodCode=\"EVN\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<templateId root=\"1.3.6.1.4.1.19376.1.3.1.6\" assigningAuthorityName=\"Laboratory Observation\"/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<code codeSystem=\"2.16.840.1.113883.2.20.5.1\" codeSystemName=\"pCLOCD\" code=\"XBC1931-6\" displayName=\"Blood Type &amp; Indirect Antibody Screen\"/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<text>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<reference value=\"#pXBC1931-6_2-1\"/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t</text>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<statusCode code=\"NI\"/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<effectiveTime value=\"201311071138-0800\"/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<value mediaType=\"text/plain\" representation=\"TXT\" xsi:type=\"ST\">NEGATIVE</value>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<performer typeCode=\"PRF\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<assignedEntity classCode=\"ASSIGNED\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<id nullFlavor=\"NI\"/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<representedOrganization classCode=\"ORG\" determinerCode=\"INSTANCE\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<id root=\"2.16.840.1.113883.3.277.1.62\" extension=\"xx\" assigningAuthorityName=\"IHA Lab Provider\"/>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<name>xx</name>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</representedOrganization>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</assignedEntity>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t</performer>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</observation>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</component>\n" +
                "\t\t\t\t\t\t\t\t\t\t</organizer>\n" +
                "\t\t\t\t\t\t\t\t\t</entryRelationship>\n" +
                "\t\t\t\t\t\t\t\t</act>\n" +
                "\t\t\t\t\t\t\t</entry>\n" +
                "\t\t\t\t\t\t</section>\n" +
                "\t\t\t\t\t</component>\n" +
                "\t\t\t\t</section>\n" +
                "\t\t\t</component>\n" +
                "\t\t</structuredBody>\n" +
                "\t</component>\n" +
                "</ClinicalDocument>\n";
    }

    @Override
    public List<IAttachment> getAttachments() {
        List<IAttachment> ats = new ArrayList<IAttachment>();
        ats.add(new AttachmentPDF());
        return ats;
    }

    @Override
    public List<IDistributionStatus> getDistributionStatus() {
        return null;
    }
}
