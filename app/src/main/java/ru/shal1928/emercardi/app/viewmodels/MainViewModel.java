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

    private final ObservableField<PersonalInfo> personalInfo = new ObservableField<PersonalInfo>();

    private final ObservableField<HealthModel> healthData = new ObservableField<HealthModel>();

    private final ObservableField<List<Object>> notificationRecipients = new ObservableField<List<Object>>();

    public MainViewModel() {
        //
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo.get();
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
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
}
