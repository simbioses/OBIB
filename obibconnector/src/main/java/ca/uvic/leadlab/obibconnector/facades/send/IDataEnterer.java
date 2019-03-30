package ca.uvic.leadlab.obibconnector.facades.send;

import java.util.Date;

public interface IDataEnterer extends IPerson<IDataEnterer> {

    IDataEnterer time(Date time);

}
