package ru.shal1928.emercardi.app.models;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by shal1928 on 29.05.2016.
 */
public class UserModel {

    private String firstName;
    private String middleName;
    private String lastName;

    private HealthModel healthData;

    private List<Object> notificationRecipients;



    public UserModel() {
        this.notificationRecipients = new LinkedList<Object>();
    }

    public UserModel(String firstName, String middleName, String lastName, HealthModel healthData) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.healthData = healthData;
        this.notificationRecipients = new LinkedList<Object>();
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public HealthModel getHealthData() {
        return healthData;
    }

    public void setHealthData(HealthModel healthData) {
        this.healthData = healthData;
    }

    public List<Object> getNotificationRecipients() {
        return notificationRecipients;
    }

    public void setNotificationRecipients(List<Object> notificationRecipients) {
        this.notificationRecipients = notificationRecipients;
    }

    public void addNotificationRecipient(Object notificationRecipient) {
        this.notificationRecipients.add(notificationRecipient);
    }
}
