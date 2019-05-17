package ca.uvic.leadlab.obibconnector.facades.send;

import ca.uvic.leadlab.obibconnector.facades.datatypes.DocumentStatus;

import java.util.Date;

public interface IServiceEvent extends IAnd {

    IServiceEvent statusCode(DocumentStatus status);

    IServiceEvent effectiveTime(Date date);
}
