package ru.shal1928.emercardi.app.activities;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import ru.shal1928.emercardi.app.R;
import ru.shal1928.emercardi.app.activities.parts.ExtAppCompatActivity;
import ru.shal1928.emercardi.app.databinding.ActivityPersonalInfoBinding;
import ru.shal1928.emercardi.app.models.parts.UserModelProperties;

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

        EditText editText = (EditText) findViewById(R.id.firstNameEditText);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus) return;

                EditText vText = (EditText) v;

                if (!vText.getText().toString().equals(firstName.get())) {
                    firstName.set(vText.getText().toString());
                }
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


    @BindingAdapter({"app:binding"})
    public static void bindEditText(EditText view, final String bindableString) {

        Log.d("binding:", "binding");
//        Pair<BindableString, TextWatcherAdvanced> pair = (Pair) view.getTag(R.id.bound_observable);
//        if (pair == null || pair.first != bindableString) {
//            if (pair != null) {
//                view.removeTextChangedListener(pair.second);
//            }
//
//            TextWatcherAdvanced watcher = new TextWatcherAdvanced(bindableString);
//            view.setTag(R.id.bound_observable, new Pair<BindableString, TextWatcherAdvanced>(bindableString, watcher));
//            view.addTextChangedListener(watcher);
//        }
//        String newValue = bindableString.get();
//        if (!view.getText().toString().equals(newValue)) {
//            view.setText(newValue);
//        }
    }
}
