package ca.uvic.leadlab.cdxconnector;

import javax.xml.namespace.QName;
import javax.xml.soap.MimeHeader;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingHandler implements SOAPHandler<SOAPMessageContext> {

    private final Logger LOGGER = Logger.getLogger(LoggingHandler.class.getName());

    public Set<QName> getHeaders() {
        return Collections.EMPTY_SET;
    }

    public boolean handleMessage(SOAPMessageContext context) {
        logMessage(context, "SOAP Message is : ");
        return true;
    }

    public boolean handleFault(SOAPMessageContext context) {
        logMessage(context, "SOAP Error is : ");
        return true;
    }

    public void close(MessageContext context) {
    }

    private boolean logMessage(SOAPMessageContext context, String type) {
        try {
            LOGGER.log(Level.INFO, type);
            SOAPMessage message = context.getMessage();

            // Print out the Mime Headers
            MimeHeaders mimeHeaders = message.getMimeHeaders();
            Iterator mhIterator = mimeHeaders.getAllHeaders();
            LOGGER.log(Level.INFO,"  Mime Headers:");
            while (mhIterator.hasNext()) {
                MimeHeader mh = (MimeHeader) mhIterator.next();
                LOGGER.log(Level.INFO, " Name: " + mh.getName() + " Value: " + mh.getValue());
            }

            LOGGER.log(Level.INFO," SOAP Message: ");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            message.writeTo(baos);
            LOGGER.log(Level.INFO,"   " + baos.toString());
            baos.close();

            return true;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error logging SOAP message", e);
            return false;
        }
    }
}
