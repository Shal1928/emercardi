package ru.shal1928.emercardi.app.models;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextWatcher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by shal1928 on 29.05.2016.
 */
public class UserModel extends BaseObservable {

    public final ObservableField<String> firstName = new ObservableField<String>();

    public final ObservableField<String> lastName = new ObservableField<String>();

    public final ObservableField<Calendar> dateOfBirth = new ObservableField<Calendar>();

    public final ObservableField<Integer> age = new ObservableField<Integer>();

    public final ObservableField<Integer> height = new ObservableField<Integer>();

    public final ObservableField<Integer> weight = new ObservableField<Integer>();

    public final ObservableField<HealthModel> healthData = new ObservableField<HealthModel>();

    public final ObservableField<List<Object>> notificationRecipients = new ObservableField<List<Object>>();



    public UserModel() {
        //
    }

    public UserModel(String firstName, String lastName, Calendar dateOfBirth, HealthModel healthData) {
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.dateOfBirth.set(dateOfBirth);
        Calendar current = Calendar.getInstance();
        this.age.set(current.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR));
        this.healthData.set(healthData);
        this.notificationRecipients.set(new LinkedList<Object>());
    }



    private TextWatcher firstNameWatcher = new TextWatcher() {

        public void afterTextChanged(Editable s) {
            //
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Important! Use the property setter, otherwhise the model won't be informed about the change.
            firstName.set(String.valueOf(s));
        }
    };

    public TextWatcher getFirstNameWatcher() {
        return firstNameWatcher;
    }

    private TextWatcher lastNameWatcher = new TextWatcher() {

        public void afterTextChanged(Editable s) {
            //
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Important! Use the property setter, otherwhise the model won't be informed about the change.
            lastName.set(String.valueOf(s));
        }
    };

    public TextWatcher getLastNameWatcher() {
        return lastNameWatcher;
    }

    private TextWatcher dateOfBirthWatcher = new TextWatcher() {

        public void afterTextChanged(Editable s) {
            //
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Important! Use the property setter, otherwhise the model won't be informed about the change.
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat();
            try {
                calendar.setTime(format.parse(String.valueOf(s)));
                dateOfBirth.set(calendar);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    };

    public TextWatcher getDateOfBirthWatcher() {
        return dateOfBirthWatcher;
    }
}


//public class UnixDateConverter {
//    public static String convert(long timestamp) {
//        Calendar mydate = Calendar.getInstance();
//        mydate.setTimeInMillis(timestamp * 1000);
//        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//        return sdf.format(mydate.getTime());
//    }
//}
//
//
//    Импортируем его в разметку:
//
//
//<import type="com.georgeci.bindingssample.UnixDateConverter"/>
//        ...
//<TextView
//   android:id="@+id/birthday"
//           android:layout_width="wrap_content"
//           android:layout_height="wrap_content"
//           android:text="@{UnixDateConverter.convert(user.birthday)}"/>
