package ca.uvic.leadlab.obibconnector.models.common;

public class Loinc {

    private String loincCode;
    private String displayName;

    public Loinc() {
    }

    public Loinc(String loincCode, String displayName) {
        this.loincCode = loincCode;
        this.displayName = displayName;
    }

    public String getLoincCode() {
        return loincCode;
    }

    public void setLoincCode(String loincCode) {
        this.loincCode = loincCode;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
