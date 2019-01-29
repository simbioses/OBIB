
package ca.uvic.leadlab.cdxconnector;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthenticationHandler implements SOAPHandler<SOAPMessageContext> {

    private final Logger LOGGER = Logger.getLogger(AuthenticationHandler.class.getName());

    private final String username;
    private final String password;

    public AuthenticationHandler(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Set<QName> getHeaders() {
        // throw new UnsupportedOperationException("Not supported yet.");
        QName securityHeader = new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd",
                "Security", "o");

        Set<QName> headers = new HashSet<>();
        headers.add(securityHeader);
        return headers;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        if ((Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY)) {
            try {
                LOGGER.log(Level.INFO, "Handling outbound message");

                SOAPMessage message = context.getMessage();

                SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
                envelope.addNamespaceDeclaration("u",
                        "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");

                SOAPHeader header = message.getSOAPHeader();

                // <o:Security>
                SOAPElement securityElem = header.addChildElement("Security", "o",
                        "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
                securityElem.addAttribute(QName.valueOf("s:mustUnderstand"), "1");

                // <u:Timestamp>
                SOAPElement timestampElem = securityElem.addChildElement("Timestamp", "u");
                timestampElem.addAttribute(QName.valueOf("u:Id"), "_0");

                ZonedDateTime now = ZonedDateTime.now();

                SOAPElement createdElem = timestampElem.addChildElement("Created", "u");
                createdElem.addTextNode(now.format(DateTimeFormatter.ISO_INSTANT));

                SOAPElement expiresElem = timestampElem.addChildElement("Expires", "u");
                expiresElem.addTextNode(now.plusMinutes(5).format(DateTimeFormatter.ISO_INSTANT));

                // <o:UsernameToken>
                SOAPElement tokenElem = securityElem.addChildElement("UsernameToken", "o");
                tokenElem.addAttribute(QName.valueOf("u:Id"), UUID.randomUUID().toString());

                SOAPElement usernameElem = tokenElem.addChildElement("Username", "o");
                usernameElem.addTextNode(username);

                SOAPElement passwordElem = tokenElem.addChildElement("Password", "o");
                passwordElem.addAttribute(QName.valueOf("Type"), "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
                passwordElem.addTextNode(password);
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error creating authentication header", e);
            }
        }
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    @Override
    public void close(MessageContext context) {
    }
}
