package ca.leadlab.obib.doctr;

import ca.infoway.messagebuilder.model.ClinicalDocumentBean;

import java.util.TimeZone;
import java.util.UUID;

public class ReferralRequestApp {

    public static void main(final String[] args) throws Exception {
        ReferralRequestApp app = new ReferralRequestApp();

        app.createDocumentBeanAndConvertToXml();

        System.out.println("\nDone");
    }

    public final void createDocumentBeanAndConvertToXml() {

        // this method creates a ReferralRequest object and populates various fields

        ClinicalDocumentBean referralRequest = new ReferralRequestCreator()
                .loincCode("34117-2", "History &amp;Physical Note")
                .effectiveTime(2019,2,1, 12, 30, TimeZone.getTimeZone("GMT-8"))
                .docId(UUID.randomUUID().toString())
                .recordTarget(new RecordTargetCreator()
                        .patientName("Weber", "Jens")
                        .patientGender("MALE")
                        .patientDOB(2019,2,1)
                        .patientId("34234324")
                        .patientAddress("5470 Old West Saanich Rd.","Victoria","BC","V9E2A7","CA")
                        .patientTelecom("(250)721-8797"))
                .author(new AuthorCreator()
                        .authorId("2343242")
                        .authorAddress("3800 Finnerty Rd", "Victoria", "BC","V8P 5C3", "CA")
                        .authorTelecom("(250) 472-8743")
                        .authorTime(2019,2,1)
                        .authorPersonName("Price","Morgan"))
                .informationRecipient(new InformationRecipientCreator()
                        .primary()
                        .recipientId("12341234")
                        .recipientName("Clinic A", "")
                        .recipientAddress("3800 Finnerty Rd", "Victoria", "BC","V8P 5C3", "CA")
                        .recipientTelecom("(250) 472-8743")
                        .receivedOrganization("Org name", "12312312"))
                .custodianOrganizationId("3234242")
                .custodianOrganizationName("Ocean Spree Medical")
                .body("Please cure this person!")
                .get();

        DocumentTransformer documentTransformer = new DocumentTransformer();
        documentTransformer.parseDocument(referralRequest);
    }
}
