package ru.shal1928.emercardi.app.converters;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import static java.util.Locale.getDefault;

/**
 * Created by Danila on 22.09.2016.
 */
public class StringCalendarConverter {

    private static final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, getDefault());

    public static String convertToString(Calendar calendar) {
        return dateFormat.format(calendar.getTime());
    }

    public static Calendar convertToCalendar(String d) {
        Date date;
        try {
            date = dateFormat.parse(d);
        } catch (ParseException e) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar;
    }
}
