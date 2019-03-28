package ca.uvic.leadlab.obibconnector.impl.send;

import ca.uvic.leadlab.obibconnector.facades.send.IAnd;
import ca.uvic.leadlab.obibconnector.facades.send.ISubmitDoc;

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
    public Object submit() {
        return submitDoc.submit();
    }
}
