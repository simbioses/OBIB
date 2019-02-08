package ca.leadlab.obib.doctr;

import ca.infoway.messagebuilder.codesystem.CodeSystem;
import ca.infoway.messagebuilder.lang.EnumPattern;

public class Realm extends EnumPattern implements ca.infoway.messagebuilder.domainvalue.Realm {

    static {
        /* static init block necessary for translation purposes. Please do not remove. */
    }

    static final long serialVersionUID = 175862977274537669L;

    // Extra-normative codes
    public static final Realm BRITISH_COLUMBIA_CANADA = new Realm("BRITISH_COLUMBIA-CANADA", "CA-BC");

    private final String codeValue;

    private Realm(String name, String codeValue) {
        super(name);
        this.codeValue = codeValue;
    }

    /**
     * {@inheritDoc}
     */
    public String getCodeValue() {
        return this.codeValue;
    }

    /**
     * {@inheritDoc}
     */
    public String getCodeSystem() {
        return CodeSystem.VOCABULARY_HL7_REALM.getRoot();
    }

    /**
     * {@inheritDoc}
     */
    public String getCodeSystemName() {
        return null;
    }
}
