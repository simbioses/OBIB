package ca.uvic.leadlab.cdxconnector.messages.registry;

import registrysearch.*;

import java.util.Date;

public class ListClinicBuilder {

    private RegistryMessageObjectFactory factory = new RegistryMessageObjectFactory();

    private String messageId;
    private MCCIMT000100UV01Sender sender;
    private PRPMIN406010UV01QUQIMT021001UV01ControlActProcess controlActProcess;

    public ListClinicBuilder(String messageId) {
        this.messageId = messageId;
        this.controlActProcess = createControlActProcess();
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

    public ListClinicBuilder sender(String senderAgentOrganizationIdExtension) {
        sender = factory.createMCCIMT000100UV01Sender(createDevice(senderAgentOrganizationIdExtension));
        return this;
    }

    public ListClinicBuilder queryById(String root, String extension) {
        PRPMMT406010UV01QueryByParameterPayload query = createParameterPayload();
        // CONF- CDXPR059, CONF- CDXPR061
        query.getOrganizationID().add(factory.createPRPMMT406010UV01OrganizationID(root, extension));
        controlActProcess.setQueryByParameterPayload(query);
        return this;
    }

    public ListClinicBuilder queryByName(String name) {
        PRPMMT406010UV01QueryByParameterPayload query = createParameterPayload();
        // CONF- CDXPR059, CONF- CDXPR060
        query.getOrganizationName().add(factory.createPRPMMT406010UV01OrganizationName(name));
        controlActProcess.setQueryByParameterPayload(query);
        return this;
    }

    public ListClinicBuilder queryByAddress(String address) {
        PRPMMT406010UV01QueryByParameterPayload query = createParameterPayload();
        // CONF- CDXPR059, CONF- CDXPR062
        query.getOrganizationAddress().add(factory.createPRPMMT406010UV01OrganizationAddress(address));
        controlActProcess.setQueryByParameterPayload(query);
        return this;
    }

    public PRPMIN406010UV01 build() {
        PRPMIN406010UV01 request = new PRPMIN406010UV01(); // CONF-CDXPR050
        request.setITSVersion("XML_1.0");
        request.setId(factory.createII("2.16.840.1.113883.3.277.100.1", messageId));
        request.setCreationTime(factory.createTS(new Date()));
        request.setVersionCode(factory.createCS("2010Normative")); // CONF- CDXPR051
        // CONF- CDXPR052
        request.setInteractionId(factory.createII("2.16.840.1.113883.1.6", "PRPM_IN306010UV"));
        request.setProcessingCode(factory.createCS("P"));
        request.setProcessingModeCode(factory.createCS("T"));
        request.setAcceptAckCode(factory.createCS("AL"));
        // CONF- CDXPR053
        request.getReceiver().add(factory.createMCCIMT000100UV01Receiver(createDevice("CDX")));
        request.setSender(sender);
        request.setControlActProcess(controlActProcess); // CONF- CDXPR054
        return request;
    }

    private PRPMIN406010UV01QUQIMT021001UV01ControlActProcess createControlActProcess() {
        PRPMIN406010UV01QUQIMT021001UV01ControlActProcess controlActProcess = new PRPMIN406010UV01QUQIMT021001UV01ControlActProcess();
        controlActProcess.setMoodCode(XActMoodIntentEvent.RQO); // CONF- CDXPR055
        controlActProcess.setClassCode(ActClassControlAct.CACT); // CONF- CDXPR055
        return controlActProcess;
    }

    private PRPMMT406010UV01QueryByParameterPayload createParameterPayload() {
        PRPMMT406010UV01QueryByParameterPayload parameterPayload = controlActProcess.getQueryByParameterPayload(); // CONF- CDXPR056
        if (parameterPayload == null) {
            parameterPayload = new PRPMMT406010UV01QueryByParameterPayload();
        }
        parameterPayload.setStatusCode(factory.createCS("new")); // CONF- CDXPR057, CONF- CDXPR058
        return parameterPayload;
    }
}
