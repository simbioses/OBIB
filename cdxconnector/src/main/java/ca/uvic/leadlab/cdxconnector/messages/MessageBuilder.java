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

    MCCIMT100100UV01Device createDeviceForDocRetr(String agentOrganizationIdExtension) {
        MCCIMT100100UV01Device device = new MCCIMT100100UV01Device();
        device.setClassCode(EntityClassDevice.DEV); // CONF-CDXSPR0022 // CONF-CDXSPR0037
        device.setDeterminerCode(EntityDeterminerSpecific.INSTANCE); // CONF-CDXSPR0023 // CONF-CDXSPR0038
        device.getId().add(factory.createII(NullFlavor.NA)); // CONF-CDXSPR0024, CONF-CDXSPR0025 // CONF-CDXSPR0039, CONF-CDXSPR0040

        MCCIMT100100UV01Agent agent = new MCCIMT100100UV01Agent();
        agent.setClassCode(RoleClassAgent.AGNT); // CONF-CDXSPR0027 // CONF-CDXSPR0042

        MCCIMT100100UV01Organization representedOrganization = new MCCIMT100100UV01Organization();
        representedOrganization.setClassCode(EntityClassOrganization.ORG); // CONF-CDXSPR0029 // CONF-CDXSPR0044
        representedOrganization.setDeterminerCode(EntityDeterminerSpecific.INSTANCE); // CONF-CDXSPR0030 // CONF-CDXSPR0045

        // CONF-CDXSPR0031, CONF-CDXSPR0032, CONF-CDXSPR0033, CONF-CDXSPR0034 TODO id.assigningAuthorityName=”CDX Clinic ID”
        // CONF-CDXSPR0046, CONF-CDXSPR0047, CONF-CDXSPR0048, CONF-CDXSPR0049 TODO id.assigningAuthorityName=”ClinicID”
        representedOrganization.getId().add(factory.createII("2.16.840.1.113883.3.277.100.2",
                "CDX Clinic ID",
                agentOrganizationIdExtension));

        agent.setRepresentedOrganization(representedOrganization); // CONF-CDXSPR0028 // CONF-CDXSPR0043
        device.setAsAgent(agent); // CONF-CDXSPR0026, CONF-CDXSPR0041

        return device;
    }

}
