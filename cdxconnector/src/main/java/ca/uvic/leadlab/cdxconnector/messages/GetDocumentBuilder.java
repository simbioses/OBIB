package ca.uvic.leadlab.cdxconnector.messages;

import org.apache.commons.lang3.StringUtils;
import org.hl7.v3.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class GetDocumentBuilder extends MessageBuilder {

    private final String messageId;
    private List<MCCIMT000100UV01Receiver> receivers;
    private MCCIMT000100UV01Sender sender;
    private RCMRIN000031UV01QUQIMT021001UV01ControlActProcess controlActProcess;

    public GetDocumentBuilder(String messageId) {
        this.messageId = messageId;
    }

    public GetDocumentBuilder receiver(String receiverAgentOrganizationIdExtension) {
        if (receivers == null) {
            receivers = new ArrayList<>();
        }
        receivers.add(factory.createMCCIMT000100UV01Receiver(createDevice(receiverAgentOrganizationIdExtension))); // CONF-CDXMCQ032
        return this;
    }

    public GetDocumentBuilder sender(String senderAgentOrganizationIdExtension) {
        sender = factory.createMCCIMT000100UV01Sender(createDevice(senderAgentOrganizationIdExtension)); // CONF-CDXMCQ047
        return this;
    }

    public GetDocumentBuilder documentQuery(String clinicId, String documentId) {
        controlActProcess = new RCMRIN000031UV01QUQIMT021001UV01ControlActProcess();
        controlActProcess.setClassCode(ActClassControlAct.CACT); // CONF-CDXMCQ062
        controlActProcess.setMoodCode(XActMoodIntentEvent.EVN); // CONF-CDXMCQ063

        RCMRMT000003UV01QueryByParameter parameter = new RCMRMT000003UV01QueryByParameter();

        if (StringUtils.isNotBlank(documentId)) {
            RCMRMT000003UV01ClinicalDocumentId clinicalDocumentId = new RCMRMT000003UV01ClinicalDocumentId();
            clinicalDocumentId.setValue(factory.createII("2.16.840.1.113883.3.277.3.4.1", documentId)); // CONF-CDXMCQ069
            parameter.setClinicalDocumentId(clinicalDocumentId); // CONF-CDXMCQ068
        }

        RCMRMT000003UV01RepresentedOrganizationId representedOrganizationId = new RCMRMT000003UV01RepresentedOrganizationId();
        representedOrganizationId.setValue(factory.createII("2.16.840.1.113883.3.277.100.2", clinicId)); // CONF-CDXMCQ071
        parameter.getRepresentedOrganizationId().add(representedOrganizationId); // CONF-CDXMCQ070

        controlActProcess.setQueryByParameter(parameter); // CONF-CDXMCQ064
        return this;
    }

    public RCMRIN000031UV01 build() throws MessageBuilderException {
        RCMRIN000031UV01 request = new RCMRIN000031UV01(); // CONF-CDXMCQ012, CONF-CDXMCQ013
        request.setITSVersion("XML_1.0"); // CONF-CDXMCQ014
        request.setId(factory.createII("2.16.840.1.113883.3.277.100.1", messageId)); // CONF-CDXMCQ015, CONF-CDXMCQ016, CONF-CDXMCQ017
        request.setCreationTime(factory.createTS(ZonedDateTime.now())); // CONF-CDXMCQ018, CONF-CDXMCQ019
        request.setVersionCode(factory.createCS("Ballot2009May")); // CONF-CDXMCQ020, CONF-CDXMCQ021
        request.setInteractionId(factory.createII("2.16.840.1.113883.1.6", "RCMR_IN000031UV01")); // CONF-CDXMCQ022, CONF-CDXMCQ023, CONF-CDXMCQ024
        request.setProcessingCode(factory.createCS(ProcessingID.P.value())); // CONF-CDXMCQ025, CONF-CDXMCQ026
        request.setProcessingModeCode(factory.createCS(ProcessingMode.T.value())); // CONF-CDXMCQ027, CONF-CDXMCQ028
        request.setAcceptAckCode(factory.createCS(AcknowledgementCondition.NE.value())); // CONF-CDXMCQ029, CONF-CDXMCQ030
        request.getReceiver().addAll(receivers); // CONF-CDXMCQ031
        request.setSender(sender); // CONF-CDXMCQ046

        request.setControlActProcess(controlActProcess); // CONF-CDXMCQ061,

        return request;
    }
}