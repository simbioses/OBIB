package ca.uvic.leadlab.cdxconnector;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

public abstract class TestUtils {

    public static String prettyXML(final String xml) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new InputSource(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8))));

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            StringWriter stringWriter = new StringWriter();
            StreamResult streamResult = new StreamResult(stringWriter);

            transformer.transform(new DOMSource(document), streamResult);
            return stringWriter.toString();
        } catch (Exception ignored) {
        }
        return xml;
    }
}
