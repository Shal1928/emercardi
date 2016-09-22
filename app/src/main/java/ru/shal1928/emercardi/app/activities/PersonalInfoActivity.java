package ru.shal1928.emercardi.app.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import ru.shal1928.emercardi.app.R;
import ru.shal1928.emercardi.app.activities.parts.ExtAppCompatActivity;
import ru.shal1928.emercardi.app.databinding.ActivityPersonalInfoBinding;
import ru.shal1928.emercardi.app.models.parts.UserModelProperties;

import java.util.Calendar;


public class PersonalInfoActivity extends ExtAppCompatActivity {

    ActivityPersonalInfoBinding binder;

    //region Observable Fields
    public final ObservableField<String> firstName = new ObservableField<String>();
    public final ObservableField<String> lastName = new ObservableField<String>();
    public final ObservableField<Calendar> dateOfBirth = new ObservableField<Calendar>();
    //endregion

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
//        setFirstName(intent.getStringExtra(UserModelProperties.FIRST_NAME));
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
}
