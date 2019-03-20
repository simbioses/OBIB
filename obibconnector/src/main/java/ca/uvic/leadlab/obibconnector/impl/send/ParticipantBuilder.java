package ca.uvic.leadlab.obibconnector.impl.send;

import ca.uvic.leadlab.obibconnector.facades.send.IParticipant;
import ca.uvic.leadlab.obibconnector.facades.send.ISubmitDoc;
import ca.uvic.leadlab.obibconnector.models.OBIBConnectorEntities.IPerson;

import java.util.Date;

public class ParticipantBuilder<P extends IPerson> extends PersonBuilder<P, IParticipant> implements IParticipant {

    ParticipantBuilder(ISubmitDoc submitDoc, P person) {
        super(submitDoc, person);
    }

    @Override
    public IParticipant participantTime(Date time) {
        person.setTime(DATE_TIME_FORMATTER.format(time));
        return this;
    }

    @Override
    public IParticipant participantTime(String year, String month, String day, String hour, String minute, String second, String timezone) {
        person.setTime(year + "-" + month + "-" + day + "T" + hour + ":" + minute + ":" + second + "" + timezone);
        return this;
    }
}
