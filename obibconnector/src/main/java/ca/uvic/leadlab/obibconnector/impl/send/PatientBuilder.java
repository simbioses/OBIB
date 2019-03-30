package ca.uvic.leadlab.obibconnector.impl.send;

import ca.uvic.leadlab.obibconnector.facades.datatypes.DateFormatter;
import ca.uvic.leadlab.obibconnector.facades.datatypes.Gender;
import ca.uvic.leadlab.obibconnector.facades.send.IPatient;
import ca.uvic.leadlab.obibconnector.facades.send.ISubmitDoc;
import ca.uvic.leadlab.obibconnector.models.document.Patient;

import java.util.Date;

public class PatientBuilder<P extends Patient> extends PersonBuilder<P, IPatient> implements IPatient {

    PatientBuilder(ISubmitDoc submitDoc, P person) {
        super(submitDoc, person);
    }

    @Override
    public IPatient gender(Gender gender) {
        person.setGenderCode(gender.label);
        return this;
    }

    @Override
    public IPatient birthday(Date date) {
        person.setBirthday(DateFormatter.DATE_FORMATTER.format(date));
        return this;
    }

    @Override
    public IPatient birthday(String year, String month, String day) {
        person.setBirthday(year + "-" + month + "-" + day);
        return this;
    }
}
