package ca.uvic.leadlab.cdaparser;

import ca.bccdx.CE;
import ca.bccdx.CS;
import ca.bccdx.ObjectFactory;
import ca.bccdx.POCDMT000040ClinicalDocument;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * This CustomFactory contains customized factory methods, extending the ObjectFactory functionalities.
 *
 * @see ObjectFactory
 */
public class CustomFactory extends ObjectFactory {

    /**
     * get a new instance of this factory.
     *
     * @return the new instance of this factory.
     */
    public static CustomFactory getInstance() {
        return new CustomFactory();
    }

    /**
     * Create a CE object with the Confidentiality as described in the section 'Fixed Header Elements'
     * of the document 'Consuming a BC CDA at Level One.pdf'
     *
     * @return CE as a Confidentiality element
     */
    public CE createConfidentialityCode() {
        CE ce = createCE();
        ce.setCodeSystem("2.16.840.1.113883.5.25");
        ce.setCode("N");
        ce.setCodeSystemName("Confidentiality");
        return ce;
    }

    /**
     * Create a CS object with the Language as described in the section 'Fixed Header Elements'
     * of the document 'Consuming a BC CDA at Level One.pdf'
     *
     * @return CS as a Language element
     */
    public CS createLanguageCode() {
        CS cs = createCS();
        cs.setCode("en-CA");
        return cs;
    }

    /**
     * Build the XML from the clinicalDocument object.
     *
     * @param clinicalDocument POCDMT000040ClinicalDocument
     * @return String with XML
     */
    public String buildXML(POCDMT000040ClinicalDocument clinicalDocument) {
        try {
            JAXBElement<POCDMT000040ClinicalDocument> jaxbElement = createClinicalDocument(clinicalDocument);

            StringWriter writer = new StringWriter();

            JAXBContext context = JAXBContext.newInstance("ca.bccdx");
            context.createMarshaller().marshal(jaxbElement, writer);

            return writer.toString();
        } catch (JAXBException e) {
            e.printStackTrace(); // TODO Log or throws exception
        }
        return "";
    }

    /**
     * Read the XML into a ClinicalDocument class.
     *
     * @param xml String
     * @return POCDMT000040ClinicalDocument object
     */
    public POCDMT000040ClinicalDocument readXML(String xml) {
        try {
            StringReader reader = new StringReader(xml);

            JAXBContext context = JAXBContext.newInstance("ca.bccdx");
            Unmarshaller unmarshaller = context.createUnmarshaller();

            return (POCDMT000040ClinicalDocument) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            e.printStackTrace(); // TODO Log or throws exception
        }
        return null;
    }
}
