package ca.uvic.leadlab.cdxconnector;

import ca.bccdx.*;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class WSObjectFactory extends ObjectFactory {

    public II createII(NullFlavor nullFlavor) {
        II id = createII();
        id.setNullFlavor(nullFlavor);
        return id;
    }

    public II createII(String root, String extension) {
        II id = createII();
        id.setRoot(root);
        id.setExtension(extension);
        return id;
    }

    public II createII(String root, String assigningAuthorityName, String extension) {
        II id = createII();
        id.setRoot(root);
        id.setAssigningAuthorityName(assigningAuthorityName);
        id.setExtension(extension);
        return id;
    }

    public TS createTS(String value) {
        TS ts = createTS();
        ts.setValue(value);
        return ts;
    }

    public TS createTS(ZonedDateTime value) {
        TS ts = createTS();
        ts.setValue(DateTimeFormatter.ofPattern("yyyyMMddHHmmssZ").format(value));
        return ts;
    }

    public CS createCS(String code) {
        CS cs = createCS();
        cs.setCode(code);
        return cs;
    }

    public MCCIMT000100UV01Receiver createMCCIMT000100UV01Receiver(MCCIMT000100UV01Device device) {
        MCCIMT000100UV01Receiver receiver = createMCCIMT000100UV01Receiver();
        receiver.setDevice(device);
        return receiver;
    }

    public MCCIMT000100UV01Sender createMCCIMT000100UV01Sender(MCCIMT000100UV01Device device) {
        MCCIMT000100UV01Sender sender = createMCCIMT000100UV01Sender();
        sender.setDevice(device);
        return sender;
    }

    public PRPMMT406010UV01OrganizationAddress createPRPMMT406010UV01OrganizationAddress(String value) {
        PRPMMT406010UV01OrganizationAddress address = createPRPMMT406010UV01OrganizationAddress();
        address.setValue(createAD());
        return address;
    }

    public PRPMMT406010UV01OrganizationID createPRPMMT406010UV01OrganizationID(String root, String extension) {
        PRPMMT406010UV01OrganizationID id = createPRPMMT406010UV01OrganizationID();
        id.setValue(createII(root, extension));
        return id;
    }

    public PRPMMT406010UV01OrganizationName createPRPMMT406010UV01OrganizationName(String value) {
        PRPMMT406010UV01OrganizationName name = createPRPMMT406010UV01OrganizationName();
        name.setValue(createEN());
        return name;
    }
}
