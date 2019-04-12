package ca.uvic.leadlab.cdxconnector.messages;

import org.hl7.v3.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubmitDocumentBuilder extends MessageBuilder {

    private String messageId;
    private List<MCCIMT000100UV01Receiver> receivers;
    private MCCIMT000100UV01Sender sender;
    private List<ED> attachments;
    private RCMRIN000002UV01MCAIMT700201UV01ControlActProcess controlActProcess;

    public SubmitDocumentBuilder(String messageId) {
        this.messageId = messageId;
    }

    public SubmitDocumentBuilder receiver(String receiverAgentOrganizationIdExtension) {
        if (receivers == null) {
            receivers = new ArrayList<>();
        }
        // CONF-CDXOD024
        receivers.add(factory.createMCCIMT000100UV01Receiver(createDevice(receiverAgentOrganizationIdExtension)));
        return this;
    }

    public SubmitDocumentBuilder sender(String senderAgentOrganizationIdExtension) {
        sender = factory.createMCCIMT000100UV01Sender(createDevice(senderAgentOrganizationIdExtension));
        return this;
    }

    public SubmitDocumentBuilder attachment(DocumentAttachment attachment) {
        if (attachments == null) {
            attachments = new ArrayList<>();
        }
        attachments.add(createAttachmentText(attachment.getMediaType(), attachment.getContent(), attachment.getHash()));
        return this;
    }

    public SubmitDocumentBuilder document(String documentId, Serializable document) {
        controlActProcess = new RCMRIN000002UV01MCAIMT700201UV01ControlActProcess();
        controlActProcess.setClassCode(ActClassControlAct.CACT); // CONF-CDXOD053
        controlActProcess.setMoodCode(XActMoodIntentEvent.APT); // CONF-CDXOD054
        controlActProcess.getId().add(factory.createII("2.16.840.1.113883.3.277.100.3",
                "CDX Clinical Document ID",
                documentId)); // CONF-CDXOD055, CONF-CDXOD056, CONF-CDXOD057, CONF-CDXOD058
        // CONF-CDXOD059, CONF-CDXOD059, CONF-CDXOD061
        controlActProcess.setCode(factory.createCD("RCMR_IN000002UV01", "2.16.840.1.113883.1.18"));
        controlActProcess.setText(createText(document)); // CONF-CDXOD062
        return this;
    }

    public RCMRIN000002UV01 build() throws MessageBuilderException {
        validate();
        // Transmission Wrapper
        RCMRIN000002UV01 request = new RCMRIN000002UV01(); // CONF-CDXOD001, CONF-CDXOD002, CONF-CDXOD003
        request.setId(factory.createII("2.16.840.1.113883.3.277.100.1", messageId)); // CONF-CDXOD004, CONF-CDXOD005
        request.setCreationTime(factory.createTS(new Date())); // CONF-CDXOD007, CONF-CDXOD008
        request.setVersionCode(factory.createCS("Ballot2009May")); // CONF-CDXOD009, CONF-CDXOD010
        // CONF-CDXOD011, CONF-CDXOD012, CONF-CDXOD013
        request.setInteractionId(factory.createII("2.16.840.1.113883.1.6", "RCMR_IN000002UV01"));
        request.setProcessingCode(factory.createCS(ProcessingID.P.value())); // CONF-CDXOD014, CONF-CDXOD015
        request.setProcessingModeCode(factory.createCS(ProcessingMode.T.value())); // CONF-CDXOD016, CONF-CDXOD017
        request.setAcceptAckCode(factory.createCS(AcknowledgementCondition.AL.value())); // CONF-CDXOD018, CONF-CDXOD019
        if (attachments != null && !attachments.isEmpty()) {
            request.getAttachmentText().addAll(attachments); // CONF-CDXOD020
        }
        request.getReceiver().addAll(receivers); // CONF-CDXOD023
        request.setSender(sender);
        // Control Act Wrapper
        request.setControlActProcess(controlActProcess);
        return request;
    }

    private ED createText(Serializable content) {
        ED ed = new ED();
        ed.setMediaType(MediaType.TEXT_XML.value()); // CONF-CDXOD063
        // TODO ed.setRepresentation(BinaryDataEncoding.TXT); // CONF-CDXOD064
        ed.getContent().add("<![CDATA[ " + content + " ]]>"); // CONF-CDXOD065
        return ed;
    }

    private ED createAttachmentText(MediaType mediaType, byte[] content, byte[] hash) {
        ED ed = new ED();
        // TODO ed.setRepresentation(BinaryDataEncoding.B_64); // CONF-CDXOD021
        ed.setIntegrityCheck(hash); // CONF-CDXOD066
        ed.setIntegrityCheckAlgorithm(IntegrityCheckAlgorithm.SHA_1); // CONF-CDXOD067
        ed.setMediaType(mediaType.value()); // CONF-CDXOD068
        ed.getContent().add(content); // CONF-CDXOD069
        return ed;
    }

    private void validate() throws MessageBuilderException {
        StringBuilder sb = new StringBuilder();
        if (receivers == null || receivers.isEmpty()) {
            sb.append("- It is required that at least one receiver be present in a message.\n");
        }
        if (sender == null) {
            sb.append("- It is required that one sender be present in a message.\n");
        }
        if (controlActProcess == null) {
            sb.append("- It is required that one sender be present in a message.\n");
        }
        if (sb.length() > 0) {
            sb.insert(0, "Error building the SubmitDocument message (RCMR_IN000002UV01):\n");
            throw new MessageBuilderException(sb.toString());
        }
    }
}
