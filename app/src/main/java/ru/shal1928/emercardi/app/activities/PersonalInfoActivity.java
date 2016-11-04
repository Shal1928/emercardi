package ru.shal1928.emercardi.app.activities;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.InverseBindingAdapter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import ru.shal1928.emercardi.app.R;
import ru.shal1928.emercardi.app.activities.dialogs.DatePickerFragment;
import ru.shal1928.emercardi.app.activities.parts.ExtAppCompatActivity;
import ru.shal1928.emercardi.app.databinding.ActivityPersonalInfoBinding;
import ru.shal1928.emercardi.app.helpers.IntentBuilder;
import ru.shal1928.emercardi.app.models.parts.IPersonalInfo;
import ru.shal1928.emercardi.app.viewmodels.IAwareIntnet;
import ru.shal1928.emercardi.app.viewmodels.PersonalInfoViewModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class PersonalInfoActivity<T extends IPersonalInfo & IAwareIntnet> extends ExtAppCompatActivity {

    ActivityPersonalInfoBinding binder;

    T viewModel;

    private static final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());

    public PersonalInfoActivity() {
        super(R.menu.menu_sub);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = IntentBuilder.getRealModel(getIntent(), IPersonalInfo.class);
//        new PersonalInfoViewModel(getIntent())

        this.binder = (ActivityPersonalInfoBinding) DataBindingUtil.setContentView(this,
                R.layout.activity_personal_info);
        this.binder.setPersonalInfo(viewModel);

        initToolbar(R.id.toolbar, true, true);

        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                setResult(RESULT_CANCELED);
                onBackPressed();
            }
        });
    }

    @Override public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_done:
                setResult(RESULT_OK, new IntentBuilder().with(this.viewModel));
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.init(this, R.id.dateOfBirthText, viewModel.getDateOfBirth().getTime());
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @BindingAdapter("android:text")
    public static void setText(TextView view, Calendar value) {
        if (value != null) {
            view.setText(dateFormat.format(value.getTime()));
        }
    }

    @InverseBindingAdapter(attribute = "android:text")
    public static Calendar getText(TextView view) {
        Date date;
        try {
            date = dateFormat.parse(view.getText().toString());
        } catch (ParseException e) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar;
    }

    @BindingAdapter("android:text")
    public static void setText(TextView view, Integer value) {
        if (value != null && view.getText() != null && !view.getText().toString().equals(String.valueOf(value))) {
            view.setText(String.valueOf(value < 0 ? "" : value));
        }
    }

    @InverseBindingAdapter(attribute = "android:text")
    public static Integer getIntegerFromText(TextView view) {
        String text = view.getText().toString();
        return text == null || text.isEmpty() ? -1 : Integer.valueOf(text);
    }
}
