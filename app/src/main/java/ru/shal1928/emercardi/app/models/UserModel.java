package ru.shal1928.emercardi.app.models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.Editable;
import android.text.TextWatcher;
import ru.shal1928.emercardi.app.BR;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by shal1928 on 29.05.2016.
 */
public class UserModel extends BaseObservable {

    private String firstName;

    private String lastName;

    private Calendar dateOfBirth;

    private int age;

    private HealthModel healthData;

    private List<Object> notificationRecipients;



    public UserModel() {
        this.notificationRecipients = new LinkedList<Object>();
    }

    public UserModel(String firstName, String lastName, Calendar dateOfBirth, HealthModel healthData) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        Calendar current = Calendar.getInstance();
        this.age = current.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
        this.healthData = healthData;
        this.notificationRecipients = new LinkedList<Object>();
    }



    @Bindable public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    @Bindable public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);
    }

    @Bindable public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        notifyPropertyChanged(BR.dateOfBirth);
    }

    @Bindable public int getAge() {
        return age;
    }

    @Bindable public HealthModel getHealthData() {
        return healthData;
    }

    public void setHealthData(HealthModel healthData) {
        this.healthData = healthData;
        notifyPropertyChanged(BR.healthData);
    }

    @Bindable public List<Object> getNotificationRecipients() {
        return notificationRecipients;
    }

    public void setNotificationRecipients(List<Object> notificationRecipients) {
        this.notificationRecipients = notificationRecipients;
        notifyPropertyChanged(BR.notificationRecipients);
    }

    public void addNotificationRecipient(Object notificationRecipient) {
        this.notificationRecipients.add(notificationRecipient);
    }



    private TextWatcher firstNameWatcher = new TextWatcher() {

        public void afterTextChanged(Editable s) {
        }

        public void beforeTextChanged(CharSequence s, int start,
                int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Important! Use the property setter, otherwhise the model won't be informed about the change.
            setFirstName(String.valueOf(s));
        }
    };
    // Textwatcher Reference: http://developer.android.com/reference/android/text/TextWatcher.html
    public TextWatcher getFirstNameWatcher() {
        return firstNameWatcher;
    }

    private TextWatcher lastNameWatcher = new TextWatcher() {

        public void afterTextChanged(Editable s) {
        }

        public void beforeTextChanged(CharSequence s, int start,
                int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Important! Use the property setter, otherwhise the model won't be informed about the change.
            setLastName(String.valueOf(s));
        }
    };

    public TextWatcher getLastNameWatcher() {
        return lastNameWatcher;
    }

    private TextWatcher dateOfBirthWatcher = new TextWatcher() {

        public void afterTextChanged(Editable s) {
        }

        public void beforeTextChanged(CharSequence s, int start,
                int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Important! Use the property setter, otherwhise the model won't be informed about the change.
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat();
            try {
                calendar.setTime(format.parse(String.valueOf(s)));
                setDateOfBirth(calendar);
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
