package ca.leadlab.obib.doctr;

import ca.infoway.messagebuilder.SpecificationVersion;
import ca.infoway.messagebuilder.VersionNumber;
import ca.infoway.messagebuilder.error.ErrorLevel;
import ca.infoway.messagebuilder.error.TransformError;
import ca.infoway.messagebuilder.error.TransformErrors;
import ca.infoway.messagebuilder.marshalling.CdaModelToXmlResult;
import ca.infoway.messagebuilder.marshalling.ClinicalDocumentTransformer;
import ca.infoway.messagebuilder.model.ClinicalDocumentBean;

import java.util.TimeZone;

public class DocumentTransformer {

    public static final VersionNumber MBSpecificationVersion = SpecificationVersion.CCDA_R1_1;

    public String parseDocument(ClinicalDocumentBean document) {
        CdaModelToXmlResult result = this.createTransformer().transformToDocument(MBSpecificationVersion, document);

        // IMPORTANT NOTE: it is the application's responsibility to add a valid xml header to the xml output
        //                 (this feature is under consideration for a future version of MB)

        System.out.println("\nDocument (converted to XML):\n"); // TODO debug log
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
        TimeZone timeZone = TimeZone.getTimeZone("GMT-8");
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
