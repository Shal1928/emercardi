package ru.shal1928.emercardi.app.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import ru.shal1928.emercardi.app.R;
import ru.shal1928.emercardi.app.activities.parts.ExtAppCompatActivity;
import ru.shal1928.emercardi.app.databinding.ActivityPersonalInfoBinding;
import ru.shal1928.emercardi.app.models.UserModel;

import java.util.Calendar;

public class PersonalInfoActivity extends ExtAppCompatActivity {

    ActivityPersonalInfoBinding binder;

    public PersonalInfoActivity() {
        super(R.menu.menu_sub);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        binder = ActivityPersonalInfoBinding.inflate(getLayoutInflater());
        binder = (ActivityPersonalInfoBinding)DataBindingUtil.setContentView(this, R.layout.activity_personal_info);
        UserModel user = new UserModel("Константин", "Константинопольский", Calendar.getInstance(), null);
        binder.setUser(user);

//        setContentView(R.layout.activity_personal_info);
        initToolbar(R.id.toolbar, true, true);

        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });

//        Intent intent = getIntent();


    }
}
