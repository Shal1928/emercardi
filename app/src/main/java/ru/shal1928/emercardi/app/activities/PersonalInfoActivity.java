package ru.shal1928.emercardi.app.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import ru.shal1928.emercardi.app.R;
import ru.shal1928.emercardi.app.databinding.ActivityPersonalInfoBinding;
import ru.shal1928.emercardi.app.models.UserModel;

import java.util.Calendar;

public class PersonalInfoActivity extends AppCompatActivity {

    ActivityPersonalInfoBinding binder;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

//        Intent intent = getIntent();

        binder = ActivityPersonalInfoBinding.inflate(getLayoutInflater());
//        binder = DataBindingUtil.setContentView(this, R.layout.activity_personal_info);
        UserModel user = new UserModel("Константин Константинович Константинопольский", Calendar.getInstance(), null);
        binder.setUser(user);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

}
