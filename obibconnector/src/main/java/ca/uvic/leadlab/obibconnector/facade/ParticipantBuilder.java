package ca.uvic.leadlab.obibconnector.facade;

import ca.uvic.leadlab.obibconnector.models.OBIBConnectorEntities.IPerson;

import java.util.Date;

public class ParticipantBuilder<P extends IPerson> extends PersonBuilder<P, IParticipantBuilder> implements IParticipantBuilder {

    ParticipantBuilder(ISubmitDoc submitDoc, P person) {
        super(submitDoc, person);
    }

    @Override
    public IParticipantBuilder participantTime(Date time) {
        person.setTime(DATE_TIME_FORMATTER.format(time));
        return this;
    }

    @Override
    public IParticipantBuilder participantTime(String year, String month, String day, String hour, String minute, String second, String timezone) {
        person.setTime(year + "-" + month + "-" + day + "T" + hour + ":" + minute + ":" + second + "" + timezone);
        return this;
    }
}
