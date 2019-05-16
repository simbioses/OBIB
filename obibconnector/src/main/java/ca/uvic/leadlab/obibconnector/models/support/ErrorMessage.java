package ca.uvic.leadlab.obibconnector.models.support;

public class ErrorMessage {

    String context;

    String trace;

    public ErrorMessage() {
    }

    public ErrorMessage(String context, String trace) {
        this.context = context;
        this.trace = trace;
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
