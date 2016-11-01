package ru.shal1928.emercardi.app.models;

import ru.shal1928.emercardi.app.models.parts.IPersonalInfo;

import java.util.Calendar;

/**
 * Created by Danila on 21.10.2016.
 */
public class PersonalInfo implements IPersonalInfo {

    private String firstName;
    private String lastName;
    private Calendar dateOfBirth;
    private Integer height;
    private Integer weight;

    public PersonalInfo() {

    }

    public PersonalInfo(IPersonalInfo personalInfo) {
        this.firstName = personalInfo.getFirstName();
        this.lastName = personalInfo.getLastName();
        this.dateOfBirth = personalInfo.getDateOfBirth();
        this.height = personalInfo.getHeight();
        this.weight = personalInfo.getWeight();
    }

    public PersonalInfo(String firstName, String lastName, Calendar dateOfBirth, Integer height, Integer weight) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.height = height;
        this.weight = weight;
    }

    @Override public String getFirstName(){
        return firstName;
    }

    @Override public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override public String getLastName() {
        return lastName;
    }

    @Override public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

    @Override public void setDateOfBirth(Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override public Integer getHeight() {
        return height;
    }

    @Override public void setHeight(Integer height) {
        this.height = height;
    }

    @Override public Integer getWeight() {
        return weight;
    }

    @Override public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
