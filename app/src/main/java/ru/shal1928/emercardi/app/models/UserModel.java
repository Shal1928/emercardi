package ru.shal1928.emercardi.app.models;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by shal1928 on 29.05.2016.
 */
public class UserModel {

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



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
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
