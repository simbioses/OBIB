package ca.leadlab.obib.doctr;

import ca.infoway.messagebuilder.SpecificationVersion;
import ca.infoway.messagebuilder.VersionNumber;
import ca.infoway.messagebuilder.error.ErrorLevel;
import ca.infoway.messagebuilder.error.TransformError;
import ca.infoway.messagebuilder.error.TransformErrors;
import ca.infoway.messagebuilder.marshalling.CdaModelToXmlResult;
import ca.infoway.messagebuilder.marshalling.ClinicalDocumentTransformer;
import ca.infoway.messagebuilder.resolver.configurator.DefaultCodeResolutionConfigurator;
import ca.infoway.messagebuilder.model.ccda_r1_1.consultationnote.ConsultationNote;

import java.util.TimeZone;

public class ReferralRequestApp {

    public static final VersionNumber MBSpecificationVersion = SpecificationVersion.CCDA_R1_1;

    public static void main(final String[] args) throws Exception {
        ReferralRequestApp app = new ReferralRequestApp();

        app.createDocumentBeanAndConvertToXml();

        System.out.println("\nDone");
    }

    public final String createDocumentBeanAndConvertToXml() {

        // this method creates a ReferralRequest object and populates various fields

        // Relaxes code vocabulary code checks and sets up some basic code resolvers
        DefaultCodeResolutionConfigurator.configureCodeResolversWithTrivialDefault();

        ConsultationNote referralRequest= new ReferralRequestCreator()
                .patientName("Weber", "Jens")
                .patientGender("FEMALE")
                .patientDOB(2019,2,1)
                .patientID("34234324")
                .patientAddress("5470 Old West Saanich Rd.","Victoria","BC","V9E2A7","CA","(250)721-8797")
                .effectiveTime(2019,02,1)
                .docId("3435465")
                .authorID("2343242")
                .authorAddress("3800 Finnerty Rd", "Victoria", "BC","V8P 5C3", "CA","(250) 472-8743")
                .authorTime(2019,02,1)
                .authorName("Price","Morgan")
                .custodian("Ocean Spree Medical", "3234242")
                .custodianAddress("344 West Saanich Lane", "Victoria", "BC", "V9e 2j4", "CA", "(409) 444-2933")
                .body("Please cure this person!")
                .get();

        CdaModelToXmlResult result = this.createTransformer().transformToDocument(MBSpecificationVersion, referralRequest);

        System.out.println("\nDocument (converted to XML):\n");

        // IMPORTANT NOTE: it is the application's responsibility to add a valid xml header to the xml output
        //                 (this feature is under consideration for a future version of MB)

        System.out.println(result.getXmlDocument());

        reportErrorsAndWarnings(result, true, true);

        return result.getXmlDocumentWithoutFormatting();
    }


    protected ClinicalDocumentTransformer createTransformer() {

        // PERMISSIVE is the default setting for the transformer; this allows processing to continue even if errors are detected

        // this creates a transformer using the local timezone and PERMISSIVE
        // return new MessageBeanTransformerImpl();

        // this creates a transformer using the local timezone and explicitly sets PERMISSIVE
        // return new MessageBeanTransformerImpl(RenderMode.PERMISSIVE);

        // specify a time zone when using the transformer
        // (not absolutely necessary, if not set, local timezone is used)
        // Note: a time zone can also be specified for each individual transform, overriding any provided in the constructor
        TimeZone timeZone = TimeZone.getTimeZone("GMT-5");
        return new ClinicalDocumentTransformer(timeZone, timeZone);
    }


    private void reportErrorsAndWarnings(TransformErrors errors, boolean toXml, boolean includeInfo) {
        String message = (toXml ? "Document object to XML" : "Document XML to object");
        if (errors.isValid()) {
            System.out.println("\n\nNo errors or warnings to report from converting " + message + ".\n");
        } else {
            System.out.println("\n\nErrors/warnings from converting " + message + ":\n");
        }
        // printing everything (to include INFO messages as well)
        for (TransformError transformError : errors.getErrors()) {
            if (includeInfo || transformError.getErrorLevel() != ErrorLevel.INFO) {
                System.out.println(transformError);
            }
        }
    }

}
