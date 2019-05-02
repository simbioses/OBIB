package ca.uvic.leadlab.cdxconnector.messages;

import org.hl7.v3.*;

import java.util.Date;

public class ListProviderBuilder extends MessageBuilder {

    private String messageId;
    private MCCIMT000100UV01Sender sender;
    private PRPMIN306010UVQUQIMT021001UV01ControlActProcess controlActProcess;

    public ListProviderBuilder(String messageId) {
        this.messageId = messageId;
        this.controlActProcess = createControlActProcess();
    }

    public ListProviderBuilder sender(String senderAgentOrganizationIdExtension) {
        sender = factory.createMCCIMT000100UV01Sender(createDevice(senderAgentOrganizationIdExtension));
        return this;
    }

    public ListProviderBuilder queryByProviderId(String root, String extension) {
        PRPMMT306010UVQueryByParameterPayload query = createParameterPayload();
        // CONF- CDXPR010, CONF- CDXPR012
        query.getProviderID().add(factory.createPRPMMT306010UVProviderID(root, extension));
        controlActProcess.setQueryByParameterPayload(query);
        return this;
    }

    public ListProviderBuilder queryByProviderName(String name) {
        PRPMMT306010UVQueryByParameterPayload query = createParameterPayload();
        // CONF- CDXPR010, CONF- CDXPR011
        query.getProviderName().add(factory.createPRPMMT306010UVProviderName(name));
        controlActProcess.setQueryByParameterPayload(query);
        return this;
    }

    public ListProviderBuilder queryBySdlcId(String root, String extension) {
        PRPMMT306010UVQueryByParameterPayload query = createParameterPayload();
        // CONF- CDXPR010, CONF- CDXPR013
        query.getSdlcId().add(factory.createPRPMMT306010UVSdlcId(root, extension));
        controlActProcess.setQueryByParameterPayload(query);
        return this;
    }

    public PRPMIN306010UV build() {
        // TODO validate CONF- CDXPR099
        PRPMIN306010UV request = new PRPMIN306010UV(); // CONF-CDXPR001
        request.setITSVersion("XML_1.0");
        request.setId(factory.createII("2.16.840.1.113883.3.277.100.1", messageId));
        request.setCreationTime(factory.createTS(new Date()));
        request.setVersionCode(factory.createCS("2010Normative")); // CONF- CDXPR002
        // CONF- CDXPR003
        request.setInteractionId(factory.createII("2.16.840.1.113883.1.6", "PRPM_IN306010UV"));
        request.setProcessingCode(factory.createCS(ProcessingID.P.value()));
        request.setProcessingModeCode(factory.createCS(ProcessingMode.T.value()));
        request.setAcceptAckCode(factory.createCS(AcknowledgementCondition.AL.value()));
        // CONF- CDXPR004
        request.getReceiver().add(factory.createMCCIMT000100UV01Receiver(createDevice("CDX")));
        request.setSender(sender);
        request.setControlActProcess(controlActProcess); // CONF- CDXPR005
        return request;
    }

    private PRPMIN306010UVQUQIMT021001UV01ControlActProcess createControlActProcess() {
        PRPMIN306010UVQUQIMT021001UV01ControlActProcess controlActProcess = new PRPMIN306010UVQUQIMT021001UV01ControlActProcess();
        controlActProcess.setMoodCode(XActMoodIntentEvent.RQO); // CONF- CDXPR006
        controlActProcess.setClassCode(ActClassControlAct.CACT); // CONF- CDXPR006
        return controlActProcess;
    }

    private PRPMMT306010UVQueryByParameterPayload createParameterPayload() {
        PRPMMT306010UVQueryByParameterPayload parameterPayload = controlActProcess.getQueryByParameterPayload(); // CONF- CDXPR007
        if (parameterPayload == null) {
            parameterPayload = new PRPMMT306010UVQueryByParameterPayload();
        }
        parameterPayload.setStatusCode(factory.createCS(QueryStatusCode.NEW.value())); // CONF- CDXPR008, CONF- CDXPR009
        return parameterPayload;
    }
}
