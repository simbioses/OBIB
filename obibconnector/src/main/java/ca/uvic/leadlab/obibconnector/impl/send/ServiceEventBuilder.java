package ca.uvic.leadlab.obibconnector.impl.send;

import ca.uvic.leadlab.obibconnector.facades.datatypes.DocumentStatus;
import ca.uvic.leadlab.obibconnector.facades.send.IServiceEvent;
import ca.uvic.leadlab.obibconnector.facades.send.ISubmitDoc;
import ca.uvic.leadlab.obibconnector.models.document.ServiceEvent;
import ca.uvic.leadlab.obibconnector.utils.DateFormatter;

import java.util.Date;

public class ServiceEventBuilder extends DocElement implements IServiceEvent {

    private ServiceEvent serviceEvent;

    ServiceEventBuilder(ISubmitDoc submitDoc, ServiceEvent serviceEvent) {
        super(submitDoc);
        this.serviceEvent = serviceEvent;
    }

    @Override
    public IServiceEvent statusCode(DocumentStatus status) {
        serviceEvent.setStatusCode(status.code);
        return this;
    }

    @Override
    public IServiceEvent effectiveTime(Date date) {
        serviceEvent.setEffectiveTime(DateFormatter.formatDateTime(date));
        return this;
    }
}
