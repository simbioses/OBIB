package ca.uvic.leadlab.cdxconnector.messages;

import org.hl7.v3.*;

import java.util.logging.Logger;

public abstract class MessageBuilder {

    final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    MessageObjectFactory factory = new MessageObjectFactory();

    MCCIMT000100UV01Device createDevice(String agentOrganizationIdExtension) {
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

    MCCIMT100100UV01Device createDeviceForDocRetr(String agentOrganizationIdExtension) {
        MCCIMT100100UV01Device device = new MCCIMT100100UV01Device();
        device.setClassCode(EntityClassDevice.DEV);
        device.setDeterminerCode(EntityDeterminerSpecific.INSTANCE);
        device.getId().add(factory.createII(NullFlavor.NA));

        MCCIMT100100UV01Agent agent = new MCCIMT100100UV01Agent();
        agent.setClassCode(RoleClassAgent.AGNT);

        MCCIMT100100UV01Organization representedOrganization = new MCCIMT100100UV01Organization();
        representedOrganization.setClassCode(EntityClassOrganization.ORG);
        representedOrganization.setDeterminerCode(EntityDeterminerSpecific.INSTANCE);

        // TODO verify CONF-CDXSPR0034 id.assigningAuthorityName=”CDX Clinic ID”
        // TODO verify CONF-CDXSPR0049 id.assigningAuthorityName=”ClinicID”
        representedOrganization.getId().add(factory.createII("2.16.840.1.113883.3.277.100.2",
                "CDX Clinic ID",
                agentOrganizationIdExtension));

        agent.setRepresentedOrganization(representedOrganization);
        device.setAsAgent(agent);

        return device;
    }

}
