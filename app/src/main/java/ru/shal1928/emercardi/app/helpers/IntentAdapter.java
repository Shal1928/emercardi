package ru.shal1928.emercardi.app.helpers;

import android.content.Intent;
import ru.shal1928.emercardi.app.models.PersonalInfo;
import ru.shal1928.emercardi.app.models.UserModel;
import ru.shal1928.emercardi.app.models.parts.UserModelProperties;
import ru.shal1928.emercardi.app.viewmodels.MainViewModel;

import java.util.Calendar;

/**
 * Created by shal1928 on 18.09.2016.
 */
public class IntentAdapter {

    public static void setPersonalInfoToModel(Intent intent, MainViewModel mainViewModel) {
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setFirstName(intent.getStringExtra(UserModelProperties.FIRST_NAME));
        personalInfo.setLastName(intent.getStringExtra(UserModelProperties.LAST_NAME));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(intent.getLongExtra(UserModelProperties.BIRTH_DATE, 0));
        personalInfo.setDateOfBirth(calendar);
        personalInfo.setHeight(intent.getIntExtra(UserModelProperties.HEIGHT, 0));
        personalInfo.setWeight(intent.getIntExtra(UserModelProperties.WEIGHT, 0));
        mainViewModel.setPersonalInfo(personalInfo);
    }

    public static void setPersonalInfoToIntent(Intent intent, MainViewModel mainViewModel) {
        PersonalInfo personalInfo = mainViewModel.getPersonalInfo();
        intent.putExtra(UserModelProperties.FIRST_NAME, personalInfo.getFirstName());
        intent.putExtra(UserModelProperties.LAST_NAME, personalInfo.getLastName());
        intent.putExtra(UserModelProperties.BIRTH_DATE, personalInfo.getDateOfBirth().getTimeInMillis());
        intent.putExtra(UserModelProperties.HEIGHT, personalInfo.getHeight());
        intent.putExtra(UserModelProperties.WEIGHT, personalInfo.getWeight());
    }
}
