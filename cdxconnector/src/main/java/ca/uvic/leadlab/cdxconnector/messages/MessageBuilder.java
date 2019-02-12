package ca.uvic.leadlab.cdxconnector.messages;

import org.hl7.v3.*;

import java.util.logging.Logger;

public abstract class MessageBuilder {

    final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    MessageObjectFactory factory = new MessageObjectFactory();

    MCCIMT000100UV01Device createDevice(String agentOrganizationIdExtension) {
        MCCIMT000100UV01Device device = factory.createMCCIMT000100UV01Device();
        device.setClassCode(EntityClassDevice.DEV); // CONF-CDXOD025 // CONF-CDXOD040
        device.setDeterminerCode(EntityDeterminerSpecific.INSTANCE); // CONF-CDXOD026 // CONF-CDXOD041
        device.getId().add(factory.createII(NullFlavor.NA)); // CONF-CDXOD027, CONF-CDXOD028 // CONF-CDXOD042, CONF-CDXOD043

        MCCIMT000100UV01Agent agent = factory.createMCCIMT000100UV01Agent();
        agent.setClassCode(RoleClassAgent.AGNT); // CONF-CDXOD029, CONF-CDXOD030 // CONF-CDXOD044, CONF-CDXOD045

        MCCIMT000100UV01Organization representedOrganization = factory.createMCCIMT000100UV01Organization();
        representedOrganization.setClassCode(EntityClassOrganization.ORG); // CONF-CDXOD032 // CONF-CDXOD047
        representedOrganization.setDeterminerCode(EntityDeterminerSpecific.INSTANCE); // CONF-CDXOD033 // CONF-CDXOD048
        // CONF-CDXOD034, CONF-CDXOD035, CONF-CDXOD036, CONF-CDXOD037
        // CONF-CDXOD049, CONF-CDXOD050, CONF-CDXOD051, CONF-CDXOD052
        representedOrganization.getId().add(factory.createII("2.16.840.1.113883.3.277.100.2",
                "CDX Clinic ID",
                agentOrganizationIdExtension));

        agent.setRepresentedOrganization(representedOrganization); // CONF-CDXOD031 // CONF-CDXOD046
        device.setAsAgent(agent);
        return device;
    }

}
