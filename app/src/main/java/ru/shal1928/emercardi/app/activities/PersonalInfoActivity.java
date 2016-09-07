package ru.shal1928.emercardi.app.activities;

import android.os.Bundle;
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
        setContentView(R.layout.activity_personal_info);

       initToolbar(R.id.toolbar, true, true);

//        Intent intent = getIntent();

        binder = ActivityPersonalInfoBinding.inflate(getLayoutInflater());
        UserModel user = new UserModel("Константин Константинович Константинопольский", Calendar.getInstance(), null);
        binder.setUser(user);
    }
}
