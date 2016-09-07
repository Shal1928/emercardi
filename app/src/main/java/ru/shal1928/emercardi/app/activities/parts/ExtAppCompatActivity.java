package ru.shal1928.emercardi.app.activities.parts;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import ru.shal1928.emercardi.app.R;

/**
 * Created by shal1928 on 07.09.2016.
 */
public abstract class ExtAppCompatActivity extends AppCompatActivity {

    private int menuId;
    private Toolbar toolbar;

    public ExtAppCompatActivity(int menuId) {
        this.menuId = menuId;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void initToolbar(int toolbarId, boolean isDisplayHomeAsUpEnabled, boolean isHomeButtonEnabled) {
        this.toolbar = (Toolbar) findViewById(toolbarId);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setElevation(5);
        getSupportActionBar().setDisplayHomeAsUpEnabled(isDisplayHomeAsUpEnabled);
        getSupportActionBar().setHomeButtonEnabled(isHomeButtonEnabled);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(this.menuId, menu);
        return true;
    }
}
