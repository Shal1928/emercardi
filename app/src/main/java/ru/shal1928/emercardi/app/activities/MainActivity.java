package ru.shal1928.emercardi.app.activities;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import ru.shal1928.emercardi.app.R;
import ru.shal1928.emercardi.app.databinding.ActivityMainBinding;
import ru.shal1928.emercardi.app.models.UserModel;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binder = DataBindingUtil.setContentView(this, R.layout.activity_main);
        UserModel user = new UserModel("Константин", "Константинович", "Константинопольский", null);
        binder.setUser(user);
        //        binding(user);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
