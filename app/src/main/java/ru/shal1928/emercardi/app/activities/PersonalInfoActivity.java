package ru.shal1928.emercardi.app.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import ru.shal1928.emercardi.app.R;
import ru.shal1928.emercardi.app.activities.parts.ExtAppCompatActivity;
import ru.shal1928.emercardi.app.databinding.ActivityPersonalInfoBinding;
import ru.shal1928.emercardi.app.helpers.IntentAdapter;
import ru.shal1928.emercardi.app.models.UserModel;

import java.util.Calendar;

public class PersonalInfoActivity extends ExtAppCompatActivity {

    ActivityPersonalInfoBinding binder;
    private UserModel personalInfo;

    public PersonalInfoActivity() {
        super(R.menu.menu_sub);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.binder = ActivityPersonalInfoBinding.inflate(getLayoutInflater());
//        setContentView(R.layout.activity_personal_info);



        this.binder = (ActivityPersonalInfoBinding) DataBindingUtil.setContentView(this,
                R.layout.activity_personal_info);
        this.personalInfo = IntentAdapter.getPersonalInfo(getIntent());
        binder.setUser(this.personalInfo);

        initToolbar(R.id.toolbar, true, true);

        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent();
                IntentAdapter.setPersonalInfoToIntent(intent, personalInfo);
                setResult(RESULT_OK, intent);
                onBackPressed();
            }
        });
    }
}
