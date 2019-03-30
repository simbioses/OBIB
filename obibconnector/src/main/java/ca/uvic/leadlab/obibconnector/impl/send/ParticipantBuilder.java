package ca.uvic.leadlab.obibconnector.impl.send;

import ca.uvic.leadlab.obibconnector.facades.datatypes.ParticipantType;
import ca.uvic.leadlab.obibconnector.facades.send.IParticipant;
import ca.uvic.leadlab.obibconnector.facades.send.ISubmitDoc;
import ca.uvic.leadlab.obibconnector.models.document.Participant;

public class ParticipantBuilder<P extends Participant> extends PersonBuilder<P, IParticipant> implements IParticipant {

    ParticipantBuilder(ISubmitDoc submitDoc, P person) {
        super(submitDoc, person);
        this.person.setTypeCode(ParticipantType.IND.label);
    }

    @Override
    public IParticipant typeCode(ParticipantType type) {
        person.setTypeCode(type.label);
        return this;
    }

    @Override
    public IParticipant functionCode(String code) {
        person.setFunctionCode(code);
        return this;
    }
}
