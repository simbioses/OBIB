package ca.uvic.leadlab.obibconnector.models.support;

import ca.uvic.leadlab.obibconnector.utils.DateFormatter;

import java.util.Date;

public class ErrorMessage {

    String date;

    String context;

    String trace;

    public ErrorMessage() {
    }

    public ErrorMessage(String context, String trace) {
        this.date = DateFormatter.formatDateTime(new Date());
        this.context = context;
        this.trace = trace;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }
}
