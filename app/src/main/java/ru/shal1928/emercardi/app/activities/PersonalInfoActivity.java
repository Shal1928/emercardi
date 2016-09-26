package ru.shal1928.emercardi.app.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.InverseBindingAdapter;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import ru.shal1928.emercardi.app.R;
import ru.shal1928.emercardi.app.activities.dialogs.DatePickerFragment;
import ru.shal1928.emercardi.app.activities.parts.ExtAppCompatActivity;
import ru.shal1928.emercardi.app.databinding.ActivityPersonalInfoBinding;
import ru.shal1928.emercardi.app.models.parts.UserModelProperties;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.util.Locale.getDefault;


public class PersonalInfoActivity extends ExtAppCompatActivity {

    ActivityPersonalInfoBinding binder;

    //region Observable Fields
    public final ObservableField<String> firstName = new ObservableField<String>();
    public final ObservableField<String> lastName = new ObservableField<String>();
    public final ObservableField<Calendar> dateOfBirth = new ObservableField<Calendar>();
    //endregion

    private static final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());

    public PersonalInfoActivity() {
        super(R.menu.menu_sub);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflateData();

        this.binder = (ActivityPersonalInfoBinding) DataBindingUtil.setContentView(this,
                R.layout.activity_personal_info);
        this.binder.setPersonalInfo(this);

        initToolbar(R.id.toolbar, true, true);

        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                setResult(RESULT_CANCELED);
                onBackPressed();
            }
        });
    }

    private void inflateData() {
        Intent intent = getIntent();
        this.firstName.set(intent.getStringExtra(UserModelProperties.FIRST_NAME));
        this.lastName.set(intent.getStringExtra(UserModelProperties.LAST_NAME));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(intent.getLongExtra(UserModelProperties.BIRTH_DATE, 0));
        this.dateOfBirth.set(calendar);
    }

    private void inflateResult() {
        Intent result = new Intent();
        result.putExtra(UserModelProperties.FIRST_NAME, this.firstName.get());
        result.putExtra(UserModelProperties.LAST_NAME, this.lastName.get());
        result.putExtra(UserModelProperties.BIRTH_DATE, this.dateOfBirth.get().getTimeInMillis());
        setResult(RESULT_OK, result);
    }

    @Override public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_done:
                inflateResult();
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
//
//        private EditText editText = (EditText) getActivity().findViewById(R.id.dateOfBirthText);
//
//        @Override
//        public Dialog onCreateDialog(Bundle savedInstanceState) {
//            Date date;
//            try {
//                date = dateFormat.parse(editText.getText().toString());
//            } catch (ParseException e) {
//                Log.e("DatePickerFragment", "Can't parse date from string!", e);
//                return null;
//            }
//
//            Calendar c = Calendar.getInstance();
//            c.setTime(date);
//
//            // Create a new instance of TimePickerDialog and return it
//            return new DatePickerDialog(getActivity(), this,
//                    c.get(Calendar.YEAR), c.get(Calendar.MONTH) - 1, c.get(Calendar.DAY_OF_MONTH));
//        }
//
//        @Override public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//            final Calendar c = Calendar.getInstance();
//            c.set(year, monthOfYear, dayOfMonth);
//            editText.setText(dateFormat.format(c.getTime()));
//        }
//    }

    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.init(R.id.dateOfBirthText);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

//    @BindingAdapter("android:text")
//    public static void setText(TextView view, Calendar value) {
//        if (value != null) {
//            view.setText(dateFormat.format(value.getTime()));
//        }
//    }
//
//    @InverseBindingAdapter(attribute = "android:text")
//    public static Calendar getText(TextView view) {
//        Date date;
//        try {
//            date = dateFormat.parse(view.getText().toString());
//        } catch (ParseException e) {
//            return null;
//        }
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//
//        return calendar;
//    }
}
