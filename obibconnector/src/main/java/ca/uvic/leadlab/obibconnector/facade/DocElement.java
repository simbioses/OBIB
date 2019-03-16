package ca.uvic.leadlab.obibconnector.facade;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DocElement implements IAnd {

    static final DateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
    static final DateFormat DATE_TIME_FORMATTER = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZ");

    ISubmitDoc submitDoc;

    @Override
    public ISubmitDoc and() {
        return submitDoc;
    }

    @Override
    public String submit() {
        return submitDoc.submit();
    }
}
