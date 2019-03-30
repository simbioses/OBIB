package ca.uvic.leadlab.obibconnector.facades.send;

import ca.uvic.leadlab.obibconnector.facades.datatypes.ParticipantType;

public interface IParticipant extends IPerson<IParticipant> {

    IParticipant typeCode(ParticipantType type);

    IParticipant functionCode(String code);

}
