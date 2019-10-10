package ca.uvic.leadlab.obibconnector.facades.send;

import ca.uvic.leadlab.obibconnector.facades.FacadesBaseTest;
import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;
import ca.uvic.leadlab.obibconnector.facades.receive.IDocument;
import ca.uvic.leadlab.obibconnector.facades.receive.ISearchDoc;
import ca.uvic.leadlab.obibconnector.impl.receive.SearchDoc;
import ca.uvic.leadlab.obibconnector.impl.send.SubmitDoc;
import ca.uvic.leadlab.obibconnector.facades.datatypes.*;
import ca.uvic.leadlab.obibconnector.models.document.ClinicalDocument;
import ca.uvic.leadlab.obibconnector.utils.DateFormatter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

public class SubmitDocTest extends FacadesBaseTest {

    private ISubmitDoc submitDoc = new SubmitDoc(config);
    private ISearchDoc searchDoc = new SearchDoc(config);

    @Before
    public void createBaseDoc() throws Exception {
        // create a base document without recipient
        submitDoc = submitDoc.newDoc()
            .documentType(DocumentType.REFERRAL_NOTE)
            .patient()
                .id("8888999904")
                .name("JUNE", "ELDER")
                .address(AddressType.HOME, "456 Main Streets", "Toronto", "OT", "M6P 4J4", "CA")
                .phone(TelcoType.HOME, "416-555-6789")
                .birthday("1942", "06", "06")
                .gender(Gender.FEMALE)
            .and().author()
                .time(new Date())
                .id("93188")
                .name("Lucius", "Plisihb", "Dr.", "")
            .and().inFulfillmentOf()
                .id("123")
                .statusCode(OrderStatus.ACTIVE)
            .and();
    }

    private static ISubmitDoc cloneBaseDoc(ISubmitDoc submitDoc, ClinicalDocument document) throws Exception {
        return submitDoc.documentType(DocumentType.fromCode(document.getLoinc().getLoincCode()))
            .patient()
                .id(document.getPatient().getIds().get(0).getCode())
                .name(document.getPatient().getNames().get(0).getGiven().get(0),
                        document.getPatient().getNames().get(0).getFamily())
                .birthday(DateFormatter.parseDateTime(document.getPatient().getBirthday()))
                .gender(Gender.fromLabel(document.getPatient().getGenderCode()))
            .and().author()
                .id(document.getAuthors().get(0).getIds().get(0).getCode())
                .time(new Date())
                .name(document.getAuthors().get(0).getName().getGiven().get(0),
                        document.getAuthors().get(0).getName().getFamily(),
                        document.getAuthors().get(0).getName().getPrefix(),
                        document.getAuthors().get(0).getName().getSuffix())
            .and().recipient().primary()
                .id(document.getRecipients().get(0).getIds().get(0).getCode())
                .name(document.getRecipients().get(0).getName().getGiven().get(0),
                        document.getRecipients().get(0).getName().getFamily(),
                        document.getRecipients().get(0).getName().getPrefix(),
                        document.getRecipients().get(0).getName().getSuffix())
            .and().inFulfillmentOf()
                .id(document.getOrders().get(0).getIds().get(0).getCode())
                .statusCode(OrderStatus.valueOf(document.getOrders().get(0).getStatusCode().toUpperCase()))
            .and().receiverId(document.getReceivers().get(0));
    }

    @Test
    public void testSubmitNewDoc() throws Exception {
        // add recipient (provider) and submit the document
        IDocument response = submitDoc
                .recipient().primary().id("11116").name("Todd", "Kinnee", "Dr.", "")
                .and().receiverId(clinicIdA)
                .content("e-Referral obib connector automated test.")
                .submit();

        Assert.assertNotNull(response);
        System.out.println("DOCUMENT ID: " + response.getDocumentID());

        Thread.sleep(5000); // wait for a few seconds

        // check the document status
        List<IDocument> documents = searchDoc.distributionStatus(response.getDocumentID());

        Assert.assertNotNull(documents);
        Assert.assertFalse(documents.isEmpty());
        System.out.println("DIST. STATUS: " + mapper.writeValueAsString(documents));
    }

    @Test
    public void testSubmitNewDocForSpecificClinic() throws Exception {
        // add recipient (clinic) and submit the document
        IDocument response = submitDoc.recipient()
                .primary().recipientOrganization("cdxpostprod-ctc", "Conformance Test Clinic")
                .and().receiverId(clinicIdT)
                .content("e-Referral obib connector automated test addressed to a clinic.")
                .submit();

        Assert.assertNotNull(response);
        System.out.println("DOCUMENT ID: " + response.getDocumentID());

        Thread.sleep(5000); // wait for a few seconds

        // check the document status
        List<IDocument> documents = searchDoc.distributionStatus(response.getDocumentID());

        Assert.assertNotNull(documents);
        Assert.assertFalse(documents.isEmpty());
        System.out.println("DIST. STATUS: " + mapper.writeValueAsString(documents));
    }

    @Test
    public void testSubmitUpdatedDocument() throws Exception {
        // add recipient (provider) and submit the document
        IDocument response = submitDoc.recipient()
                .primary().id("11116").name("Todd", "Kinnee", "Dr.", "")
                .and().receiverId(clinicIdA)
                .content("e-Referral obib connector automated test.")
                .submit();

        Assert.assertNotNull(response);
        System.out.println("DOCUMENT ID: " + response.getDocumentID());

        Thread.sleep(5000); // wait for a few seconds
        ClinicalDocument document = ((SubmitDoc) submitDoc).getDocument();

        // update the document
        IDocument response2 = cloneBaseDoc(submitDoc.updateDoc(response.getDocumentID(), response.getVersion()), document)
                .content(document.getNonXMLBody().getContent() + " - updating content of this document.")
                .submit();

        Assert.assertNotNull(response2);
        System.out.println("DOCUMENT ID: " + response2.getDocumentID());

        Thread.sleep(5000); // wait for a few seconds

        // check the document status
        List<IDocument> documents = searchDoc.distributionStatus(response2.getDocumentID());

        Assert.assertNotNull(documents);
        Assert.assertFalse(documents.isEmpty());
        System.out.println("DIST. STATUS: " + mapper.writeValueAsString(documents));
    }

    @Test
    public void testSubmitCancelDoc() throws Exception {
        // add recipient (provider) and submit the document
        IDocument response = submitDoc.recipient()
                .primary().id("11116").name("Todd", "Kinnee", "Dr.", "")
                .and().receiverId(clinicIdA)
                .content("e-Referral obib connector automated test.")
                .submit();

        Assert.assertNotNull(response);
        System.out.println("DOCUMENT ID: " + response.getDocumentID());

        Thread.sleep(5000); // wait for a few seconds
        ClinicalDocument document = ((SubmitDoc) submitDoc).getDocument();

        // cancel the document
        IDocument response2 = cloneBaseDoc(submitDoc.cancelDoc(response.getDocumentID(), 1), document)
                .content("Please, cancel this document; obib connector automated test.")
                .submit();

        Assert.assertNotNull(response2);
        System.out.println("DOCUMENT ID: " + response2.getDocumentID());

        Thread.sleep(5000); // wait for a few seconds

        // check the document status
        List<IDocument> documents = searchDoc.distributionStatus(response2.getDocumentID());

        Assert.assertNotNull(documents);
        Assert.assertFalse(documents.isEmpty());
        System.out.println("DIST. STATUS: " + mapper.writeValueAsString(documents));
    }

    @Test(expected = OBIBException.class)
    public void testSubmitDocWithErrors() throws Exception {
        try {
            IDocument respose = submitDoc.newDoc()
                    // .documentType(DocumentType.REFERRAL_NOTE) LOINC is required
                    .patient()
                        // .id("2222") Patient shall contain at least one id
                        // .name("Joe", "Wine") Patient shall contain at least one name
                        .address(AddressType.HOME, "111 Main St", "Victoria", "BC", "V8V Z9Z", "CA")
                        .phone(TelcoType.HOME, "250-111-1234")
                        .birthday("1980", "01", "01")
                        .gender(Gender.MALE)
                    .and().author()
                        // .time(new Date()) Author shall contain one time
                        // .id("93188") Author shall contain at least one id
                        // .name("Lucius", "Plisihb", "Dr.", "") Author shall contain one name
                    .and().recipient()
                        // .primary().id("93190") Recipient should contain one or two id
                        .name("Aaron", "Plisihd", "Dr.", "")
                    .and().participant()
                        .functionCode("PCP")
                        // .id("93193") Participant should contain one or two id
                        // .name("Mikel", "Plisihf", "Dr.", "") Participant shall contain one name
                    .and().inFulfillmentOf()
                        // .id("2") InFulfillmentOf shall contain at least one id
                        .statusCode(OrderStatus.COMPLETED)
                    .and().documentationOf()
                        //.effectiveTime(new Date()) ServiceEvent shall contain one effectiveTime
                        .statusCode(DocumentStatus.COMPLETED)
                    .and()
                        // .receiverId(clinicIdC) At least one receiver is required to submit document
                        // .content("Referral test") // NO BODY?
                    .submit();
        } catch (OBIBException ex) {
            System.out.println("Message: " + ex.getMessage());
            System.out.println("OBIB Message: " + ex.getObibMessage());
            throw ex;
        }
    }

    @Test
    public void testSubmitDocWithAttachment() throws Exception {
        // add recipient (provider), attachments and submit the document
        IDocument response = submitDoc.recipient()
                .primary().id("11116").name("Todd", "Kinnee", "Dr.", "")
                .and().receiverId(clinicIdA)
                .attach(AttachmentType.PDF, "logo.pdf", loadFile("/leadlab.pdf"))
                .attach(AttachmentType.PDF, "logo2.pdf", loadFile("/CDXDocument-eReferral_att01.pdf"))
                .submit();

        Assert.assertNotNull(response);
        System.out.println("DOCUMENT ID: " + response.getDocumentID());

        Thread.sleep(5000); // wait for a few seconds

        // check the document status
        List<IDocument> documents = searchDoc.distributionStatus(response.getDocumentID());

        Assert.assertNotNull(documents);
        Assert.assertFalse(documents.isEmpty());
        System.out.println("DIST. STATUS: " + mapper.writeValueAsString(documents));
    }

    @Test(expected = OBIBException.class)
    public void testSubmitDocWithTextBodyAndAttachment() throws Exception {
        try {
            // add recipient (provider), attachments and submit the document
            IDocument response = submitDoc.recipient()
                    .primary().id("11116").name("Todd", "Kinnee", "Dr.", "")
                    .and().receiverId(clinicIdA)
                    .content("e-Referral obib connector automated test - with multiple attachments")
                    .attach(AttachmentType.PDF, "logo.pdf", loadFile("/leadlab.pdf"))
                    .submit();

            Assert.assertNotNull(response);
            System.out.println("DOCUMENT ID: " + response.getDocumentID());
        } catch (OBIBException ex) {
            System.out.println("Message: " + ex.getMessage());
            System.out.println("OBIB Message: " + ex.getObibMessage());
            throw ex;
        }
    }

    @Test(expected = OBIBException.class)
    public void testSubmitDocWithAttachmentAndTextBody() throws Exception {
        try {
            // add recipient (provider), attachments and submit the document
            IDocument response = submitDoc.recipient()
                    .primary().id("11116").name("Todd", "Kinnee", "Dr.", "")
                    .and().receiverId(clinicIdA)
                    .attach(AttachmentType.PDF, "logo.pdf", loadFile("/leadlab.pdf"))
                    .content("e-Referral obib connector automated test - with multiple attachments")
                    .submit();

            Assert.assertNotNull(response);
            System.out.println("DOCUMENT ID: " + response.getDocumentID());
        } catch (OBIBException ex) {
            System.out.println("Message: " + ex.getMessage());
            System.out.println("OBIB Message: " + ex.getObibMessage());
            throw ex;
        }
    }

    @Test(expected = OBIBException.class)
    public void testSubmitDocWithBigAttachment() throws Exception {
        try {
            // add recipient (provider), big attachment and submit the document
            IDocument response = submitDoc.recipient()
                    .primary().id("11116").name("Todd", "Kinnee", "Dr.", "")
                    .and().receiverId(clinicIdA)
                    .attach(AttachmentType.PDF, "document.pdf", loadFile("/bc-ehr-cda-implementation-guide.pdf"))
                    .submit();
        } catch (OBIBException ex) {
            System.out.println("Message: " + ex.getMessage());
            System.out.println("OBIB Message: " + ex.getObibMessage());
            throw ex;
        }
    }

    //@Test
    public void testDistributionStatus() throws Exception {
        try {
            List<IDocument> documents = searchDoc.distributionStatus("7d9658db-d56e-439a-acce-1eb9303c7928");

            Assert.assertNotNull(documents);
            Assert.assertFalse(documents.isEmpty());
            System.out.println("DIST. STATUS: " + mapper.writeValueAsString(documents));
        } catch (OBIBException ex) {
            System.out.println("Message: " + ex.getMessage());
            System.out.println("OBIB Message: " + ex.getObibMessage());
            throw ex;
        }
    }

    private static byte[] loadFile(String filePath) throws Exception {
        Path path = Paths.get(SubmitDocTest.class.getResource(filePath).toURI());
        return Files.readAllBytes(path);
    }
}
