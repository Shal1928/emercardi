package ru.shal1928.emercardi.app.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import ru.shal1928.emercardi.app.R;
import ru.shal1928.emercardi.app.activities.parts.ExtAppCompatActivity;
import ru.shal1928.emercardi.app.databinding.ActivityPersonalInfoBinding;
import ru.shal1928.emercardi.app.helpers.TextWatcherAdvanced;
import ru.shal1928.emercardi.app.models.parts.UserModelProperties;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PersonalInfoActivity extends ExtAppCompatActivity {

    ActivityPersonalInfoBinding binder;
//    private UserModel personalInfo;

    //region Observable Fields
    public final ObservableField<String> firstName = new ObservableField<String>();
    public final ObservableField<String> lastName = new ObservableField<String>();
    public final ObservableField<Calendar> dateOfBirth = new ObservableField<Calendar>();
    //endregion

    private boolean isInNotification = false;

    public PersonalInfoActivity() {
        super(R.menu.menu_sub);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.binder = ActivityPersonalInfoBinding.inflate(getLayoutInflater());
//        setContentView(R.layout.activity_personal_info);
        inflateData();

        this.binder = (ActivityPersonalInfoBinding) DataBindingUtil.setContentView(this,
                R.layout.activity_personal_info);
//        this.personalInfo = IntentAdapter.getPersonalInfo(getIntent());
        binder.setPersonalInfo(this);

        initToolbar(R.id.toolbar, true, true);

        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                inflateResult();
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



    //region Watcher
    private TextWatcher firstNameWatcher = new TextWatcherAdvanced() {

        @Override public void onTextChanged(String newValue) {
            firstName.set(newValue);
        }
    };

    public TextWatcher getFirstNameWatcher() {
        return firstNameWatcher;
    }

    private TextWatcher lastNameWatcher = new TextWatcher() {

        public void afterTextChanged(Editable s) {
            //
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Important! Use the property setter, otherwhise the model won't be informed about the change.
            lastName.set(String.valueOf(s));
        }
    };

    public TextWatcher getLastNameWatcher() {
        return lastNameWatcher;
    }

    private TextWatcher dateOfBirthWatcher = new TextWatcher() {

        public void afterTextChanged(Editable s) {
            //
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Important! Use the property setter, otherwhise the model won't be informed about the change.
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat();
            try {
                calendar.setTime(format.parse(String.valueOf(s)));
                dateOfBirth.set(calendar);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    };

    public TextWatcher getDateOfBirthWatcher() {
        return dateOfBirthWatcher;
    }
    //endregion
}
