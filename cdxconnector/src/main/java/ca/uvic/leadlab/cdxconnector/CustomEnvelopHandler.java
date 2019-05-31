package ca.uvic.leadlab.cdxconnector;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Collections;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomEnvelopHandler implements SOAPHandler<SOAPMessageContext> {

    private final Logger LOGGER = Logger.getLogger(CustomEnvelopHandler.class.getName());

    private final QName action;

    public CustomEnvelopHandler(QName action) {
        this.action = action;
    }

    @Override
    public Set<QName> getHeaders() {
        return Collections.EMPTY_SET;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        if ((Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY)) {
            try {
                LOGGER.log(Level.INFO, "Handling outbound messages");

                SOAPMessage message = context.getMessage();

                // Adjust SOAP Envelop prefixes and declarations
                SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
                envelope.removeNamespaceDeclaration("S");
                envelope.removeNamespaceDeclaration("SOAP-ENV");
                envelope.setPrefix("s");
                envelope.getHeader().setPrefix("s");
                envelope.getBody().setPrefix("s");
                envelope.getBody().addNamespaceDeclaration("xsi", "http://www.w3.org/2001/XMLSchema-instance");
                envelope.getBody().addNamespaceDeclaration("xsd", "http://www.w3.org/2001/XMLSchema");
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error adjusting soap envelop", e);
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

    private String getSOAPAction() {
        return action.getNamespaceURI() + ":" + action.getLocalPart().toUpperCase();
    }
}
