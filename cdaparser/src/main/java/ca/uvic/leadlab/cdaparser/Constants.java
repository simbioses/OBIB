package ca.uvic.leadlab.cdaparser;

import java.time.format.DateTimeFormatter;

public abstract class Constants {

    /**
     * CDA Date format YYYYMMDDhhmm±ZZzz
     */
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmssZ");

    /**
     * Root Id for patient’s BC Provincial Healthcare Number
     */
    public static final String PATIENTS_BC_PHN_ROOT = "2.16.840.1.113883.4.50";

    /**
     * Root Id for physician’s BC MSP license
     */
    public static final String PHYSICIANS_BC_MSP_ROOT = "2.16.840.1.113883.3.40.2.11";

    /**
     * Root Id for Documents
     */
    public static final String DOCUMENT_ROOT = "2.16.840.1.113883.3.277.100.3";


    // Authoring Device IDs

    public static final String AUTHORING_DEVICE_IHA_ROOT = "2.16.840.1.113883.3.277.1.81";

    public static final String AUTHORING_DEVICE_NHA_ROOT = "2.16.840.1.113883.3.523.1.22";

    // Authoring Software IDs

    public static final String AUTHORING_SOFTWARE_IHA_ROOT = "2.16.840.1.113883.3.277.1.12";

    public static final String AUTHORING_SOFTWARE_NHA_ROOT = "2.16.840.1.113883.3.523.1.63";

    /**
     * Recipient Meditech Id
     */
    public static final String MEDITECH_ID_ROOT = "2.16.840.1.113883.3.277.1.61";

    /**
     * LOINC Code System
     */
    public static final String LOINC_CODE_SYSTEM = "2.16.840.1.113883.6.1";


}
