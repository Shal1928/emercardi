package ru.shal1928.emercardi.app.activities.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
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
    private FragmentActivity activity;
    private Date value;

    public void init(FragmentActivity activity, int editTextId, Date value) {
        this.activity = activity;
        this.editText = (EditText) activity.findViewById(editTextId);
        this.value = value;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        try {
            value = dateFormat.parse(editText.getText().toString());
        } catch (ParseException e) {
            if(value == null) throw new RuntimeException("Can't parse date from string!", e);
        }

        Calendar c = Calendar.getInstance();
        c.setTime(value);

        return new DatePickerDialog(this.activity, this,
                c.get(Calendar.YEAR), c.get(Calendar.MONTH) - 1, c.get(Calendar.DAY_OF_MONTH));
    }

    @Override public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        final Calendar c = Calendar.getInstance();
        c.set(year, monthOfYear, dayOfMonth);
        editText.setText(dateFormat.format(c.getTime()));
    }
}
