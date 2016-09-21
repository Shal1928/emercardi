package ru.shal1928.emercardi.app.helpers;

import android.app.Activity;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Danila on 21.09.2016.
 */
public class BinderHelper {

    private Activity activity;

    public BinderHelper(Activity activity) {
        this.activity = activity;
    }

    public void bindWithEditText(int id, final ObservableField<String> field) {
        EditText editText = (EditText) activity.findViewById(id);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) return;
                EditText vText = (EditText) v;
                if (!vText.getText().toString().equals(field.get())) {
                    field.set(vText.getText().toString());
                }
            }
        });
    }
}
