package ca.uvic.leadlab.cdxconnector.messages.request;

import ca.uvic.leadlab.cdxconnector.messages.exception.MessageBuilderException;
import cdasubmitrequest.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetDocumentBuilder {

    private RequestMessageObjectFactory factory = new RequestMessageObjectFactory();

    private final String messageId;
    private List<MCCIMT000100UV01Receiver> receivers;
    private MCCIMT000100UV01Sender sender;
    private RCMRIN000031UV01QUQIMT021001UV01ControlActProcess controlActProcess;

    public GetDocumentBuilder(String messageId) {
        this.messageId = messageId;
    }

    private MCCIMT000100UV01Device createDevice(String agentOrganizationIdExtension) {
        MCCIMT000100UV01Device device = factory.createMCCIMT000100UV01Device();
        device.setClassCode(EntityClassDevice.DEV);
        device.setDeterminerCode(EntityDeterminerSpecific.INSTANCE);
        device.getId().add(factory.createII(NullFlavor.NA));

        MCCIMT000100UV01Agent agent = factory.createMCCIMT000100UV01Agent();
        agent.setClassCode(RoleClassAgent.AGNT);

        MCCIMT000100UV01Organization representedOrganization = factory.createMCCIMT000100UV01Organization();
        representedOrganization.setClassCode(EntityClassOrganization.ORG);
        representedOrganization.setDeterminerCode(EntityDeterminerSpecific.INSTANCE);
        representedOrganization.getId().add(factory.createII("2.16.840.1.113883.3.277.100.2",
                "CDX Clinic ID",
                agentOrganizationIdExtension));

        agent.setRepresentedOrganization(representedOrganization);
        device.setAsAgent(agent);
        return device;
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

    public GetDocumentBuilder documentQuery(DocumentQueryParameterBuilder documentQueryParameterBuilder) {
        controlActProcess = new RCMRIN000031UV01QUQIMT021001UV01ControlActProcess();
        controlActProcess.setQueryByParameter(documentQueryParameterBuilder.buildGet());
        return this;
    }

    public RCMRIN000031UV01 build() throws MessageBuilderException {
        RCMRIN000031UV01 request = new RCMRIN000031UV01(); // CONF-CDXMCQ012, CONF-CDXMCQ013
        request.setITSVersion("XML_1.0"); // CONF-CDXMCQ014
        request.setId(factory.createII("2.16.840.1.113883.3.277.100.1", messageId)); // CONF-CDXMCQ015, CONF-CDXMCQ016, CONF-CDXMCQ017
        request.setCreationTime(factory.createTS(new Date())); // CONF-CDXMCQ018, CONF-CDXMCQ019
        request.setVersionCode(factory.createCS("Ballot2009May")); // CONF-CDXMCQ020, CONF-CDXMCQ021
        request.setInteractionId(factory.createII("2.16.840.1.113883.1.6", "RCMR_IN000031UV01")); // CONF-CDXMCQ022, CONF-CDXMCQ023, CONF-CDXMCQ024
        request.setProcessingCode(factory.createCS("P")); // CONF-CDXMCQ025, CONF-CDXMCQ026
        request.setProcessingModeCode(factory.createCS("T")); // CONF-CDXMCQ027, CONF-CDXMCQ028
        request.setAcceptAckCode(factory.createCS("NE")); // CONF-CDXMCQ029, CONF-CDXMCQ030
        request.getReceiver().addAll(receivers); // CONF-CDXMCQ031
        request.setSender(sender); // CONF-CDXMCQ046

        request.setControlActProcess(controlActProcess); // CONF-CDXMCQ061,

        return request;
    }
}