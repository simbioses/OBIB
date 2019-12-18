package ca.uvic.leadlab.cdxconnector;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.StringWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class WSUtil {

    public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyyMMddHHmmZZZ");

    public static void logObject(final Logger logger, String message, Object obj) throws ConnectorException {
        if (logger.isLoggable(Level.FINE)) {
            logger.log(Level.FINE, message + " \n" + parseObject(obj, true));
        }
    }

    public static String parseObject(Object obj, boolean formattedOutput) throws ConnectorException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, formattedOutput);

            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);

            return writer.toString();
        } catch (JAXBException e) {
            throw new ConnectorException("Error parsing object", e);
        }
    }

    public static void validateObjectSize(Object obj) throws ConnectorException {
        String objStr = parseObject(obj, false);
        if (objStr.length() > 3145728) { // 3 MB
            throw new ConnectorException("The total size of the message is bigger than 3 MB.");
        }
    }

    public static void validateObject(Object obj, URL schemaUrl) throws ConnectorException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(schemaUrl);

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setSchema(schema);
            marshaller.marshal(obj, new DefaultHandler());
        } catch (JAXBException | SAXException e) {
            throw new ConnectorException("Error validating object", e);
        }
    }
}
