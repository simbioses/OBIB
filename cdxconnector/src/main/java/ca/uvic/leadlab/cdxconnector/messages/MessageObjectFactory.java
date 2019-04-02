package ca.uvic.leadlab.cdxconnector.messages;

import org.hl7.v3.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageObjectFactory extends ObjectFactory {

    public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyyMMddHHmmZZZ");

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

    public TS createTS(Date value) {
        TS ts = createTS();
        ts.setValue(DATE_TIME_FORMAT.format(value));
        return ts;
    }

    public CS createCS(String code) {
        CS cs = createCS();
        cs.setCode(code);
        return cs;
    }

    public CD createCD(String code, String codeSystem) {
        CD cd = createCD();
        cd.setCode(code);
        cd.setCodeSystem(codeSystem);
        return cd;
    }

    public MCCIMT000100UV01Receiver createMCCIMT000100UV01Receiver(MCCIMT000100UV01Device device) {
        MCCIMT000100UV01Receiver receiver = createMCCIMT000100UV01Receiver();
        receiver.setDevice(device);
        return receiver;
    }

    public MCCIMT100100UV01Receiver createMCCIMT100100UV01Receiver(MCCIMT100100UV01Device device) {
        MCCIMT100100UV01Receiver receiver = new MCCIMT100100UV01Receiver();
        receiver.setDevice(device);
        return receiver;
    }

    public MCCIMT000100UV01Sender createMCCIMT000100UV01Sender(MCCIMT000100UV01Device device) {
        MCCIMT000100UV01Sender sender = createMCCIMT000100UV01Sender();
        sender.setDevice(device);
        return sender;
    }

    public MCCIMT100100UV01Sender createMCCIMT100100UV01Sender(MCCIMT100100UV01Device device) {
        MCCIMT100100UV01Sender sender = new MCCIMT100100UV01Sender();
        sender.setDevice(device);
        return sender;
    }

    public PRPMMT406010UV01OrganizationID createPRPMMT406010UV01OrganizationID(String root, String extension) {
        PRPMMT406010UV01OrganizationID id = createPRPMMT406010UV01OrganizationID();
        id.setValue(createII(root, extension));
        return id;
    }

    public PRPMMT406010UV01OrganizationName createPRPMMT406010UV01OrganizationName(String value) {
        PRPMMT406010UV01OrganizationName name = new PRPMMT406010UV01OrganizationName();
        name.setValue(createST(value)); // Changed in the XSD to use ST as specified in the documentation
        return name;
    }

    public PRPMMT406010UV01OrganizationAddress createPRPMMT406010UV01OrganizationAddress(String value) {
        PRPMMT406010UV01OrganizationAddress address = createPRPMMT406010UV01OrganizationAddress();
        address.setValue(createST(value)); // Changed in the XSD to use ST as specified in the documentation
        return address;
    }

    public PRPMMT306010UVProviderID createPRPMMT306010UVProviderID(String root, String extension) {
        PRPMMT306010UVProviderID providerID = new PRPMMT306010UVProviderID();
        providerID.setValue(createII(root, extension));
        return providerID;
    }

    public PRPMMT306010UVProviderName createPRPMMT306010UVProviderName(String value) {
        PRPMMT306010UVProviderName providerName = new PRPMMT306010UVProviderName();
        providerName.setValue(createST(value)); // Changed in the XSD to use ST as specified in the documentation
        return providerName;
    }

    public PRPMMT306010UVSdlcId createPRPMMT306010UVSdlcId(String root, String extension) {
        PRPMMT306010UVSdlcId sdlcId = new PRPMMT306010UVSdlcId();
        sdlcId.setValue(createII(root, extension));
        return sdlcId;
    }

    public ST createST(String content) {
        ST st = createST();
        st.getContent().add(content);
        return st;
    }

    public EN createEN(String content) {
        EN en = createEN();
        en.getDelimiterOrFamilyOrGiven().add(createENXP(content));
        return en;
    }

    public ENXP createENXP(String content) {
        ENXP enxp = createEnFamily();
        enxp.getContent().add(content);
        return enxp;
    }

    public AD createAD(String content) {
        AD ad = createAD();
        ad.getAdditionalLocatorOrBuildingNumberSuffixOrCareOf().add(createADXP(content));
        return ad;
    }

    public ADXP createADXP(String content) {
        ADXP adxp = createAdxpStreetAddressLine();
        adxp.getContent().add(content);
        return adxp;
    }

    public IVLTS createIVLTS(IVXBTS ivxbtsLow, IVXBTS ivxbtsHigh) {
        IVLTS ivlts = new IVLTS();
        ivlts.getCenterOrHighOrLow().add(createIVLTSLow(ivxbtsLow));
        ivlts.getCenterOrHighOrLow().add(createIVLTSHigh(ivxbtsHigh));
        return ivlts;
    }

    public IVXBTS createIVXBTS(boolean inclusive, Date value) {
        IVXBTS ivxbts = createIVXBTS();
        ivxbts.setInclusive(inclusive);
        ivxbts.setValue(DATE_TIME_FORMAT.format(value));
        return ivxbts;
    }
}

