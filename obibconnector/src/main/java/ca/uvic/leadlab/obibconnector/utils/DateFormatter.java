package ca.uvic.leadlab.obibconnector.utils;

import ca.uvic.leadlab.obibconnector.facades.exceptions.OBIBException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DateFormatter {

    private static final DateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateFormat DATE_TIME_FORMATTER = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZ");

    public static Date parseDateTime(String strDateTime) throws OBIBException {
        try {
            if (strDateTime.length() == 10) {
                return DATE_FORMATTER.parse(strDateTime);
            }
            return DATE_TIME_FORMATTER.parse(strDateTime);
        } catch (ParseException e) {
            throw new OBIBException("Error parsing date/time.", e);
        }
    }

    public static String formatDate(Date date) {
        return DATE_FORMATTER.format(date);
    }

    public static String formatDateTime(Date date) {
        return DATE_TIME_FORMATTER.format(date);
    }
}
