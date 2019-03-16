package ca.uvic.leadlab.obibconnector.facade;

import java.util.Date;

public interface IParticipantBuilder extends IPersonBuilder<IParticipantBuilder> {

    IParticipantBuilder participantTime(Date time);

    IParticipantBuilder participantTime(String year, String month, String day, String hour, String minute, String second, String timezone);

}
