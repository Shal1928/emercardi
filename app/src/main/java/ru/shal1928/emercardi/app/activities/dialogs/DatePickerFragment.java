package ru.shal1928.emercardi.app.activities.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Danila on 26.09.2016.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private static final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
    private EditText editText;

    public void init(int editTextId) {
        this.editText = (EditText) getActivity().findViewById(editTextId);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Date date;
        try {
            date = dateFormat.parse(editText.getText().toString());
        } catch (ParseException e) {
            Log.e("DatePickerFragment", "Can't parse date from string!", e);
            return null;
        }

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        // Create a new instance of TimePickerDialog and return it
        return new DatePickerDialog(getActivity(), this,
                c.get(Calendar.YEAR), c.get(Calendar.MONTH) - 1, c.get(Calendar.DAY_OF_MONTH));
    }

    @Override public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        final Calendar c = Calendar.getInstance();
        c.set(year, monthOfYear, dayOfMonth);
        editText.setText(dateFormat.format(c.getTime()));
    }
}
