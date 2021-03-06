package ca.uvic.leadlab.cdxconnector.messages.distribution;

import ca.uvic.leadlab.cdxconnector.messages.exception.MessageBuilderException;
import distributionstatus.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DistributionStatusBuilder {

    private DistributionMessageObjectFactory factory = new DistributionMessageObjectFactory();

    private final String messageId;
    private List<MCCIMT000100UV01Receiver> receivers;
    private MCCIMT000100UV01Sender sender;
    private RCMRIN000029UV01QUQIMT021001UV01ControlActProcess controlActProcess;

    public DistributionStatusBuilder(String messageId) {
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

    public DistributionStatusBuilder receiver(String receiverAgentOrganizationIdExtension) {
        if (receivers == null) {
            receivers = new ArrayList<>();
        }
        receivers.add(factory.createMCCIMT000100UV01Receiver(createDevice(receiverAgentOrganizationIdExtension))); // CONF-CDXMQ032
        return this;
    }

    public DistributionStatusBuilder sender(String senderAgentOrganizationIdExtension) {
        sender = factory.createMCCIMT000100UV01Sender(createDevice(senderAgentOrganizationIdExtension)); // CONF-CDXMQ047
        return this;
    }

    public DistributionStatusBuilder documentQuery(DistributionStatusQueryParameterBuilder distributionStatusQueryParameterBuilder) {
        controlActProcess = new RCMRIN000029UV01QUQIMT021001UV01ControlActProcess();
        controlActProcess.setQueryByParameter(distributionStatusQueryParameterBuilder.buildSearch());
        return this;
    }

    public RCMRIN000029UV01 build() throws MessageBuilderException {
        RCMRIN000029UV01 request = new RCMRIN000029UV01(); // CONF-CDXMQ012, CONF-CDXMQ013
        request.setITSVersion("XML_1.0"); // CONF-CDXMQ014
        request.setId(factory.createII("2.16.840.1.113883.3.277.100.1", messageId)); // CONF-CDXMQ015, CONF-CDXMQ016, CONF-CDXMQ017
        request.setCreationTime(factory.createTS(new Date())); // CONF-CDXMQ018, CONF-CDXMQ019
        request.setVersionCode(factory.createCS("Ballot2009May")); // CONF-CDXMQ020, CONF-CDXMQ021
        request.setInteractionId(factory.createII("2.16.840.1.113883.1.6", "RCMR_IN000029UV01")); // CONF-CDXMQ022, CONF-CDXMQ022, CONF-CDXMQ024
        request.setProcessingCode(factory.createCS("P")); // CONF-CDXMQ025, CONF-CDXMQ026
        request.setProcessingModeCode(factory.createCS("T")); // CONF-CDXMQ027, CONF-CDXMQ028
        request.setAcceptAckCode(factory.createCS("NE")); // CONF-CDXMQ029, CONF-CDXMQ030
        request.getReceiver().addAll(receivers); // CONF-CDXMQ031
        request.setSender(sender); // CONF-CDXMQ046

        request.setControlActProcess(controlActProcess); // CONF-CDXMQ061

        return request;
    }
}
