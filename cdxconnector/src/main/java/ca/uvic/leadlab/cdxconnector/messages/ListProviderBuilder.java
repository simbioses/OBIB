package ca.uvic.leadlab.cdxconnector.messages;

import org.hl7.v3.*;

import java.time.ZonedDateTime;

public class ListProviderBuilder extends MessageBuilder {

    private String messageId;
    private MCCIMT000100UV01Sender sender;
    private PRPMIN406010UV01QUQIMT021001UV01ControlActProcess controlActProcess;

    public ListProviderBuilder(String messageId) {
        this.messageId = messageId;
    }

    public ListProviderBuilder sender(String senderAgentOrganizationIdExtension) {
        sender = factory.createMCCIMT000100UV01Sender(createDevice(senderAgentOrganizationIdExtension));
        return this;
    }

    public ListProviderBuilder queryById(String root, String extension) {
        controlActProcess = createControlActProcess();

        PRPMMT406010UV01QueryByParameterPayload query = createParameterPayload();
        query.getOrganizationID().add(factory.createPRPMMT406010UV01OrganizationID(root, extension));

        controlActProcess.setQueryByParameterPayload(query);
        return this;
    }

    public ListProviderBuilder queryByName(String name) {
        controlActProcess = createControlActProcess();

        PRPMMT406010UV01QueryByParameterPayload query = createParameterPayload();
        query.getOrganizationName().add(factory.createPRPMMT406010UV01OrganizationName(name));

        controlActProcess.setQueryByParameterPayload(query);
        return this;
    }

    public ListProviderBuilder queryByAddress(String address) {
        controlActProcess = createControlActProcess();

        PRPMMT406010UV01QueryByParameterPayload query = createParameterPayload();
        query.getOrganizationAddress().add(factory.createPRPMMT406010UV01OrganizationAddress(address));

        controlActProcess.setQueryByParameterPayload(query);
        return this;
    }

    public PRPMIN406010UV01 build() {
        // Transmission Wrapper
        PRPMIN406010UV01 request = factory.createPRPMIN406010UV01();
        request.setITSVersion("XML_1.0");
        request.setId(factory.createII("2.16.840.1.113883.3.277.100.1", messageId));
        request.setCreationTime(factory.createTS(ZonedDateTime.now())); // Time of transmission yyyyMMddHHMMss-Z (201209241316-0700)
        request.setVersionCode(factory.createCS("2010Normative"));
        request.setInteractionId(factory.createII("2.16.840.1.113883.1.6", "PRPM_IN306010UV"));
        request.setProcessingCode(factory.createCS(ProcessingID.P.value()));
        request.setProcessingModeCode(factory.createCS(ProcessingMode.T.value()));
        request.setAcceptAckCode(factory.createCS(AcknowledgementCondition.AL.value()));
        request.getReceiver().add(factory.createMCCIMT000100UV01Receiver(createDevice("CDX"))); // The receiver is always the CDX system
        request.setSender(sender);
        // Control Act Wrapper - this specifies the search criteria
        request.setControlActProcess(controlActProcess);
        return request;
    }

    private PRPMIN406010UV01QUQIMT021001UV01ControlActProcess createControlActProcess() {
        PRPMIN406010UV01QUQIMT021001UV01ControlActProcess controlActProcess = factory
                .createPRPMIN406010UV01QUQIMT021001UV01ControlActProcess();
        controlActProcess.setMoodCode(XActMoodIntentEvent.RQO);
        controlActProcess.setClassCode(ActClassControlAct.CACT);
        return controlActProcess;
    }

    private PRPMMT406010UV01QueryByParameterPayload createParameterPayload() {
        PRPMMT406010UV01QueryByParameterPayload parameterPayload = factory
                .createPRPMMT406010UV01QueryByParameterPayload();
        parameterPayload.setStatusCode(factory.createCS(QueryStatusCode.NEW.value()));
        return parameterPayload;
    }
}
