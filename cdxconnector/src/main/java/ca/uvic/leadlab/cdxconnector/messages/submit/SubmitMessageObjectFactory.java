package ca.uvic.leadlab.cdxconnector.messages.submit;

import ca.uvic.leadlab.cdxconnector.WSUtil;
import cdasubmit.*;

import java.util.Date;

public class SubmitMessageObjectFactory extends ObjectFactory {

    public II createII(NullFlavor nullFlavor) {
        II id = new II();
        id.setNullFlavor(nullFlavor);
        return id;
    }

    public II createII(String root, String extension) {
        II id = new II();
        id.setRoot(root);
        id.setExtension(extension);
        return id;
    }

    public II createII(String root, String assigningAuthorityName, String extension) {
        II id = new II();
        id.setRoot(root);
        id.setAssigningAuthorityName(assigningAuthorityName);
        id.setExtension(extension);
        return id;
    }

    public TS createTS(String value) {
        TS ts = new TS();
        ts.setValue(value);
        return ts;
    }

    public TS createTS(Date value) {
        TS ts = new TS();
        ts.setValue(WSUtil.DATE_TIME_FORMAT.format(value));
        return ts;
    }

    public CS createCS(String code) {
        CS cs = new CS();
        cs.setCode(code);
        return cs;
    }

    public CD createCD(String code, String codeSystem) {
        CD cd = new CD();
        cd.setCode(code);
        cd.setCodeSystem(codeSystem);
        return cd;
    }

    public MCCIMT000100UV01Receiver createMCCIMT000100UV01Receiver(MCCIMT000100UV01Device device) {
        MCCIMT000100UV01Receiver receiver = new MCCIMT000100UV01Receiver();
        receiver.setDevice(device);
        return receiver;
    }

    public MCCIMT000100UV01Sender createMCCIMT000100UV01Sender(MCCIMT000100UV01Device device) {
        MCCIMT000100UV01Sender sender = new MCCIMT000100UV01Sender();
        sender.setDevice(device);
        return sender;
    }
}

