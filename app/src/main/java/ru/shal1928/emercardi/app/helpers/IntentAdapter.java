package ru.shal1928.emercardi.app.helpers;

import android.content.Intent;
import ru.shal1928.emercardi.app.models.UserModel;
import ru.shal1928.emercardi.app.models.parts.UserModelProperties;

import java.util.Calendar;

/**
 * Created by shal1928 on 18.09.2016.
 */
public class IntentAdapter {

    public static void setPersonalInfoToModel(Intent intent, UserModel userModel) {
        userModel.firstName.set(intent.getStringExtra(UserModelProperties.FIRST_NAME));
        userModel.lastName.set(intent.getStringExtra(UserModelProperties.LAST_NAME));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(intent.getLongExtra(UserModelProperties.BIRTH_DATE, 0));
        userModel.dateOfBirth.set(calendar);
    }

    public static void setPersonalInfoToIntent(Intent intent, UserModel userModel) {
        intent.putExtra(UserModelProperties.FIRST_NAME, userModel.firstName.get());
        intent.putExtra(UserModelProperties.LAST_NAME, userModel.lastName.get());
        intent.putExtra(UserModelProperties.BIRTH_DATE, userModel.dateOfBirth.get().getTimeInMillis());
    }

    public static UserModel getPersonalInfo(Intent intent) {
        UserModel userModel = new UserModel();
        setPersonalInfoToModel(intent, userModel);
        return userModel;
    }
}
