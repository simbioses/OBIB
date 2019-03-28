package ca.uvic.leadlab.obibconnector.impl.send;

import ca.uvic.leadlab.obibconnector.facades.send.IParticipant;
import ca.uvic.leadlab.obibconnector.facades.send.ISubmitDoc;
import ca.uvic.leadlab.obibconnector.models.common.IPerson;

import java.util.Date;

public class ParticipantBuilder<P extends IPerson> extends PersonBuilder<P, IParticipant> implements IParticipant {

    ParticipantBuilder(ISubmitDoc submitDoc, P person) {
        super(submitDoc, person);
    }

    @Override
    public IParticipant participantTime(Date time) {
        person.setTime(time);
        return this;
    }

}
