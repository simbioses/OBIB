package ca.uvic.leadlab.obibconnector.facades.send;

import java.util.Date;

public interface IAuthor extends IPerson<IAuthor> {

    IAuthor time(Date time);

    IAuthor softwareCode(String softwareCode);

}
