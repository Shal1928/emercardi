package ru.shal1928.emercardi.app.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import ru.shal1928.emercardi.app.R;
import ru.shal1928.emercardi.app.databinding.ActivityMainBinding;
import ru.shal1928.emercardi.app.models.UserModel;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setIcon(R.drawable.ic_menu_black_24dp);

        binder = DataBindingUtil.setContentView(this, R.layout.activity_main);
        UserModel user = new UserModel("Константин Константинович Константинопольский", Calendar.getInstance(), null);
        binder.setUser(user);
        //        binding(user);
    }

}
