package ca.uvic.leadlab.obibconnector.impl.send;

import ca.uvic.leadlab.obibconnector.facades.datatypes.DateFormatter;
import ca.uvic.leadlab.obibconnector.facades.send.IAuthor;
import ca.uvic.leadlab.obibconnector.facades.send.ISubmitDoc;
import ca.uvic.leadlab.obibconnector.models.document.Author;
import ca.uvic.leadlab.obibconnector.models.document.Software;

import java.util.Date;

public class AuthorBuilder<P extends Author> extends PersonBuilder<P, IAuthor> implements IAuthor {

    AuthorBuilder(ISubmitDoc submitDoc, P person) {
        super(submitDoc, person);
    }

    @Override
    public IAuthor time(Date time) {
        person.setTime(DateFormatter.DATE_TIME_FORMATTER.format(time));
        return this;
    }

    @Override
    public IAuthor softwareCode(String softwareCode) {
        person.setSoftware(new Software("", softwareCode));
        return this;
    }
}
