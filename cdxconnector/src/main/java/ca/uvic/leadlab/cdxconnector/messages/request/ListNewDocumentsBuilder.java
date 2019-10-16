package ca.uvic.leadlab.cdxconnector.messages.request;

import ca.uvic.leadlab.cdxconnector.messages.exception.MessageBuilderException;
import cdarequest.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListNewDocumentsBuilder {

    private RequestMessageObjectFactory factory = new RequestMessageObjectFactory();

    private final String messageId;
    private List<MCCIMT100100UV01Receiver> receivers;
    private MCCIMT100100UV01Sender sender;

    public ListNewDocumentsBuilder(String messageId) {
        this.messageId = messageId;
    }

    MCCIMT100100UV01Device createDeviceForDocRetr(String agentOrganizationIdExtension, boolean sender) {
        MCCIMT100100UV01Device device = new MCCIMT100100UV01Device();
        device.setClassCode(EntityClassDevice.DEV);
        device.setDeterminerCode(EntityDeterminerSpecific.INSTANCE);
        device.getId().add(factory.createII(NullFlavor.NA));

        MCCIMT100100UV01Agent agent = new MCCIMT100100UV01Agent();
        agent.setClassCode(RoleClassAgent.AGNT);

        MCCIMT100100UV01Organization representedOrganization = new MCCIMT100100UV01Organization();
        representedOrganization.setClassCode(EntityClassOrganization.ORG);
        representedOrganization.setDeterminerCode(EntityDeterminerSpecific.INSTANCE);

        // Receiver = CONF-CDXSPR0034 id.assigningAuthorityName=”CDX Clinic ID”
        // Sender = CONF-CDXSPR0049 id.assigningAuthorityName=”ClinicID”
        representedOrganization.getId().add(factory.createII("2.16.840.1.113883.3.277.100.2",
                sender ? "ClinicID": "CDX Clinic ID",
                agentOrganizationIdExtension));

        agent.setRepresentedOrganization(representedOrganization);
        device.setAsAgent(agent);

        return device;
    }

    public ListNewDocumentsBuilder receiver(String receiverAgentOrganizationIdExtension) {
        if (receivers == null) {
            receivers = new ArrayList<>();
        }
        receivers.add(factory.createMCCIMT100100UV01Receiver(createDeviceForDocRetr(receiverAgentOrganizationIdExtension, false))); // CONF-CDXSPR0021
        return this;
    }

    public ListNewDocumentsBuilder sender(String senderAgentOrganizationIdExtension) {
        sender = factory.createMCCIMT100100UV01Sender(createDeviceForDocRetr(senderAgentOrganizationIdExtension, true)); // CONF-CDXSPR0036
        return this;
    }

    public MCCIIN100001UV01 build() throws MessageBuilderException {
        MCCIIN100001UV01 request = new MCCIIN100001UV01(); // CONF-CDXSPR0001, CONF-CDXSPR0002
        request.setITSVersion("XML_1.0"); // CONF-CDXSPR0003
        request.setId(factory.createII("2.16.840.1.113883.3.277.100.1", messageId)); // CONF-CDXSPR0004, CONF-CDXSPR0005, CONF-CDXSPR0006
        request.setCreationTime(factory.createTS(new Date())); // CONF-CDXSPR0007, CONF-CDXSPR0008
        request.setVersionCode(factory.createCS("Ballot2009May")); // CONF-CDXSPR0009, CONF-CDXSPR0010
        request.setInteractionId(factory.createII("2.16.840.1.113883.1.6", "MCCI_IN100001UV01")); // CONF-CDXSPR0011, CONF-CDXSPR0012, CONF-CDXSPR0013
        request.setProcessingCode(factory.createCS("P")); // CONF-CDXSPR0014, CONF-CDXSPR0015
        request.setProcessingModeCode(factory.createCS("T")); // CONF-CDXSPR0016, CONF-CDXSPR0017
        request.setAcceptAckCode(factory.createCS("AL")); // CONF-CDXSPR0018, CONF-CDXSPR0019
        request.getReceiver().addAll(receivers); // CONF-CDXSPR0020
        request.setSender(sender); // CONF-CDXSPR0035
        return request;
    }
}
