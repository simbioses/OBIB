package ca.uvic.leadlab.cdxconnector.messages;

import org.hl7.v3.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListNewDocumentsBuilder extends MessageBuilder {

    private final String messageId;
    private List<MCCIMT100100UV01Receiver> receivers;
    private MCCIMT100100UV01Sender sender;

    public ListNewDocumentsBuilder(String messageId) {
        this.messageId = messageId;
    }

    public ListNewDocumentsBuilder receiver(String receiverAgentOrganizationIdExtension) {
        if (receivers == null) {
            receivers = new ArrayList<>();
        }
        receivers.add(factory.createMCCIMT100100UV01Receiver(createDeviceForDocRetr(receiverAgentOrganizationIdExtension))); // CONF-CDXSPR0021
        return this;
    }

    public ListNewDocumentsBuilder sender(String senderAgentOrganizationIdExtension) {
        sender = factory.createMCCIMT100100UV01Sender(createDeviceForDocRetr(senderAgentOrganizationIdExtension)); // CONF-CDXSPR0036
        return this;
    }

    public MCCIIN100001UV01 build() throws MessageBuilderException {
        MCCIIN100001UV01 request = new MCCIIN100001UV01(); // CONF-CDXSPR0001, CONF-CDXSPR0002
        request.setITSVersion("XML_1.0"); // CONF-CDXSPR0003
        request.setId(factory.createII("2.16.840.1.113883.3.277.100.1", messageId)); // CONF-CDXSPR0004, CONF-CDXSPR0005, CONF-CDXSPR0006
        request.setCreationTime(factory.createTS(new Date())); // CONF-CDXSPR0007, CONF-CDXSPR0008
        request.setVersionCode(factory.createCS("Ballot2009May")); // CONF-CDXSPR0009, CONF-CDXSPR0010
        request.setInteractionId(factory.createII("2.16.840.1.113883.1.6", "MCCI_IN100001UV01")); // CONF-CDXSPR0011, CONF-CDXSPR0012, CONF-CDXSPR0013
        request.setProcessingCode(factory.createCS(ProcessingID.P.value())); // CONF-CDXSPR0014, CONF-CDXSPR0015
        request.setProcessingModeCode(factory.createCS(ProcessingMode.T.value())); // CONF-CDXSPR0016, CONF-CDXSPR0017
        request.setAcceptAckCode(factory.createCS(AcknowledgementCondition.AL.value())); // CONF-CDXSPR0018, CONF-CDXSPR0019
        request.getReceiver().addAll(receivers); // CONF-CDXSPR0020
        request.setSender(sender); // CONF-CDXSPR0035
        return request;
    }
}
