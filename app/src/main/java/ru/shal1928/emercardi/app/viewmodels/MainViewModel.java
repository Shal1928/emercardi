package ru.shal1928.emercardi.app.viewmodels;

import android.content.Intent;
import android.databinding.ObservableField;
import ru.shal1928.emercardi.app.models.HealthModel;
import ru.shal1928.emercardi.app.models.PersonalInfo;
import ru.shal1928.emercardi.app.models.parts.IPersonalInfo;
import ru.shal1928.emercardi.app.models.parts.UserModelProperties;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Danila on 31.10.2016.
 */
public class MainViewModel {

    private final ObservableField<IPersonalInfo> personalInfo = new ObservableField<IPersonalInfo>();

    private final ObservableField<HealthModel> healthData = new ObservableField<HealthModel>();

    private final ObservableField<List<Object>> notificationRecipients = new ObservableField<List<Object>>();

    public MainViewModel() {
        //
    }

    public IPersonalInfo getPersonalInfo() {
        return personalInfo.get();
    }

    public void setPersonalInfo(IPersonalInfo personalInfo) {
        this.personalInfo.set(personalInfo);
    }

    public HealthModel getHealthData() {
        return this.healthData.get();
    }

    public void setHealthData(HealthModel healthData) {
        this.healthData.set(healthData);
    }

    public List<Object> getNotificationRecipients() {
        return this.notificationRecipients.get();
    }

    public void setNotificationRecipients(List<Object> notificationRecipients) {
        this.notificationRecipients.set(notificationRecipients);
    }

    public void setPersonalInfo(Intent intent) {
        IPersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setFirstName(intent.getStringExtra(UserModelProperties.FIRST_NAME));
        personalInfo.setLastName(intent.getStringExtra(UserModelProperties.LAST_NAME));
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis();
        personalInfo.setDateOfBirth(intent.getLongExtra(UserModelProperties.BIRTH_DATE, 0));
        personalInfo.setHeight(intent.getIntExtra(UserModelProperties.HEIGHT, 0));
        personalInfo.setWeight(intent.getIntExtra(UserModelProperties.WEIGHT, 0));
        setPersonalInfo(personalInfo);
    }

    public Intent withPersonalInfo(Intent intent) {
        IPersonalInfo personalInfo = getPersonalInfo();
        intent.putExtra(UserModelProperties.FIRST_NAME, personalInfo.getFirstName());
        intent.putExtra(UserModelProperties.LAST_NAME, personalInfo.getLastName());
        intent.putExtra(UserModelProperties.BIRTH_DATE, personalInfo.getDateOfBirth());
        intent.putExtra(UserModelProperties.HEIGHT, personalInfo.getHeight());
        intent.putExtra(UserModelProperties.WEIGHT, personalInfo.getWeight());
        return intent;
    }
}
