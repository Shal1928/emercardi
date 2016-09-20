package ru.shal1928.emercardi.app.helpers;

import android.databinding.Observable;
import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by Danila on 20.09.2016.
 */
public class TextWatcherAdvanced implements TextWatcher {

    public final ObservableField<String> value = new ObservableField<String>();
    private final ObservableField<String> field;

    private boolean isInEditMode = false;

    public TextWatcherAdvanced(ObservableField<String> f) {
        this.field = f;

        field.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback(){
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (isInEditMode){
                    return;
                }
                value.set(field.get());
            }
        });
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //
    }

    @Override public void afterTextChanged(Editable s) {
        if (!field.get().equals(s.toString())) {
            isInEditMode = true;
            field.set(s.toString());
            isInEditMode = false;
        }
    }
}
