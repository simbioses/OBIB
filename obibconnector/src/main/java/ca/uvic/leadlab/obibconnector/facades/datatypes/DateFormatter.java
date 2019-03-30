package ca.uvic.leadlab.obibconnector.facades.datatypes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DateFormatter {

    public static final DateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
    public static final DateFormat DATE_TIME_FORMATTER = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZ");

    public static Date parseDate(String strDate) {
        try {
            return DATE_FORMATTER.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace(); // TODO throw error?
        }
        return null;
    }

    public static Date parseDateTime(String strDateTime) {
        try {
            return DATE_TIME_FORMATTER.parse(strDateTime);
        } catch (ParseException e) {
            e.printStackTrace(); // TODO throw error?
        }
        return null;
    }
}
