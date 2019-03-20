package ca.uvic.leadlab.obibconnector.facades.send;

import java.util.Date;

public interface IParticipant extends IPerson<IParticipant> {

    IParticipant participantTime(Date time);

    IParticipant participantTime(String year, String month, String day, String hour, String minute, String second, String timezone);

}
