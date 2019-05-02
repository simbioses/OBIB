package ca.uvic.leadlab.cdxconnector;

import java.util.Date;

public class DateRange {

    private Date lowDate;
    private Date highDate;
    private boolean lowDateInclusive;
    private boolean highDateInclusive;

    public DateRange() {
    }

    public DateRange(Date lowDate, boolean lowDateInclusive, Date highDate, boolean highDateInclusive) {
        this.lowDate = lowDate;
        this.highDate = highDate;
        this.lowDateInclusive = lowDateInclusive;
        this.highDateInclusive = highDateInclusive;
    }

    public Date getLowDate() {
        return lowDate;
    }

    public void setLowDate(Date lowDate) {
        this.lowDate = lowDate;
    }

    public Date getHighDate() {
        return highDate;
    }

    public void setHighDate(Date highDate) {
        this.highDate = highDate;
    }

    public boolean isLowDateInclusive() {
        return lowDateInclusive;
    }

    public void setLowDateInclusive(boolean lowDateInclusive) {
        this.lowDateInclusive = lowDateInclusive;
    }

    public boolean isHighDateInclusive() {
        return highDateInclusive;
    }

    public void setHighDateInclusive(boolean highDateInclusive) {
        this.highDateInclusive = highDateInclusive;
    }
}
