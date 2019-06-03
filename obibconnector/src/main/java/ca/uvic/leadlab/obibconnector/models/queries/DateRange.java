package ca.uvic.leadlab.obibconnector.models.queries;

import ca.uvic.leadlab.obibconnector.utils.DateFormatter;

import java.util.Date;

public class DateRange {

    private String start;
    private String end;

    public DateRange() {
    }

    public DateRange(Date start, Date end) {
        this.start = DateFormatter.formatDateTime(start);
        this.end = DateFormatter.formatDateTime(end);
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
