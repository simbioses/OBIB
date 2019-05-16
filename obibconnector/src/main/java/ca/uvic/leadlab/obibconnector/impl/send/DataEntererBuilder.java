package ca.uvic.leadlab.obibconnector.impl.send;

import ca.uvic.leadlab.obibconnector.utils.DateFormatter;
import ca.uvic.leadlab.obibconnector.facades.send.IDataEnterer;
import ca.uvic.leadlab.obibconnector.facades.send.ISubmitDoc;
import ca.uvic.leadlab.obibconnector.models.document.DataEnterer;

import java.util.Date;

public class DataEntererBuilder<P extends DataEnterer> extends PersonBuilder<P, IDataEnterer> implements IDataEnterer {

    DataEntererBuilder(ISubmitDoc submitDoc, P person) {
        super(submitDoc, person);
    }

    @Override
    public IDataEnterer time(Date time) {
        person.setTime(DateFormatter.formatDateTime(time));
        return this;
    }

}
