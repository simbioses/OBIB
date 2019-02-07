package ca.uvic.leadlab.cdxconnector.messages;

import org.hl7.v3.*;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
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
        receivers.add(factory.createMCCIMT000100UV01Receiver(createDevice(receiverAgentOrganizationIdExtension)));
        return this;
    }

    public SubmitDocumentBuilder sender(String senderAgentOrganizationIdExtension) {
        sender = factory.createMCCIMT000100UV01Sender(createDevice(senderAgentOrganizationIdExtension));
        return this;
    }

    public SubmitDocumentBuilder attachmentText(MediaType mediaType, Serializable content) throws MessageBuilderException {
        if (attachments == null) {
            attachments = new ArrayList<>();
        }
        try {
            attachments.add(factory.createED(mediaType, content));
        } catch (NoSuchAlgorithmException e) {
            throw new MessageBuilderException("Error attaching text", e);
        }
        return this;
    }

    public SubmitDocumentBuilder document(String documentId, Serializable document) {
        controlActProcess = new RCMRIN000002UV01MCAIMT700201UV01ControlActProcess();
        controlActProcess.setClassCode(ActClassControlAct.CACT);
        controlActProcess.setMoodCode(XActMoodIntentEvent.APT);
        controlActProcess.getId().add(factory.createII("2.16.840.1.113883.3.277.100.3",
                "CDX Clinical Document ID",
                documentId)); // Unique Document ID (GUID)
        controlActProcess.setCode(factory.createCD("RCMR_IN000002UV01", "2.16.840.1.113883.1.18"));
        controlActProcess.setText(createText(document)); // Contents of document being submitted, wrapped in a CDATA or html encoded
        return this;
    }

    public RCMRIN000002UV01 build() throws MessageBuilderException {
        validate();
        // Transmission Wrapper
        RCMRIN000002UV01 request = new RCMRIN000002UV01();
        request.setId(factory.createII("2.16.840.1.113883.3.277.100.1", messageId));
        request.setCreationTime(factory.createTS(ZonedDateTime.now())); // Time of transmission yyyyMMddHHMMss-Z (201209241316-0700)
        request.setVersionCode(factory.createCS("Ballot2009May"));
        request.setInteractionId(factory.createII("2.16.840.1.113883.1.6", "RCMR_IN000002UV01"));
        request.setProcessingCode(factory.createCS(ProcessingID.P.value()));
        request.setProcessingModeCode(factory.createCS(ProcessingMode.T.value()));
        request.setAcceptAckCode(factory.createCS(AcknowledgementCondition.AL.value()));
        if (attachments != null && !attachments.isEmpty()) {
            request.getAttachmentText().addAll(attachments);
        }
        request.getReceiver().addAll(receivers);
        request.setSender(sender);
        // Control Act Wrapper
        request.setControlActProcess(controlActProcess);
        return request;
    }

    private ED createText(Serializable content) {
        ED ed = factory.createED();
        ed.setMediaType(MediaType.TEXT_XML.value());
        // TODO ed.setRepresentation(BinaryDataEncoding.TXT);
        ed.getContent().add("<![CDATA[ " + content + " ]]>"); // <!—HTML Encoded CDA document XML here --> OR <![CDATA[ CDA goes here ]]>
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
