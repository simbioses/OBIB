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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;

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

  public static String loadTextFile(String filePath) throws Exception {
        Path path = Paths.get(TestUtils.class.getResource(filePath).toURI());
        StringBuilder content = new StringBuilder();
        for (String line : Files.readAllLines(path)) {
            content.append(line);
        }
        return content.toString();
    }

    public static byte[] loadBinaryFile(String filePath) throws Exception {
        Path path = Paths.get(TestUtils.class.getResource(filePath).toURI());
        return Files.readAllBytes(path);
    }

    public static byte[] calculateHash(byte[] content) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        return md.digest(content);
        //return DatatypeConverter.printHexBinary(hash);
        //return DatatypeConverter.printBase64Binary(hash);
    }
}
