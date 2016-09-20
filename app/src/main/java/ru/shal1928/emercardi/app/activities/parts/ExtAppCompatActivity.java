package ru.shal1928.emercardi.app.activities.parts;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import ru.shal1928.emercardi.app.R;

/**
 * Created by shal1928 on 07.09.2016.
 */
public abstract class ExtAppCompatActivity extends AppCompatActivity implements Observable {

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



    //region Observable Impl
    private transient PropertyChangeRegistry mCallbacks;

    @Override
    public synchronized void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        if (mCallbacks == null) {
            mCallbacks = new PropertyChangeRegistry();
        }
        mCallbacks.add(callback);
    }

    @Override
    public synchronized void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        if (mCallbacks != null) {
            mCallbacks.remove(callback);
        }
    }

    /**
     * Notifies listeners that all properties of this instance have changed.
     */
    public synchronized void notifyChange() {
        if (mCallbacks != null) {
            mCallbacks.notifyCallbacks(this, 0, null);
        }
    }

    /**
     * Notifies listeners that a specific property has changed. The getter for the property
     * that changes should be marked with {@link Bindable} to generate a field in
     * <code>BR</code> to be used as <code>fieldId</code>.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    public void notifyPropertyChanged(int fieldId) {
        if (mCallbacks != null) {
            mCallbacks.notifyCallbacks(this, fieldId, null);
        }
    }
    //endregion
}
