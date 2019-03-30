package ca.uvic.leadlab.obibconnector.facades.send;

import ca.uvic.leadlab.obibconnector.facades.datatypes.Gender;

import java.util.Date;

public interface IPatient extends IPerson<IPatient> {

    IPatient gender(Gender gender);

    IPatient birthday(Date date);

    IPatient birthday(String year, String month, String day);

}
