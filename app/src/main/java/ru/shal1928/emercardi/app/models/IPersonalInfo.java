package ru.shal1928.emercardi.app.models;

import java.util.Calendar;

/**
 * Created by Danila on 21.10.2016.
 */
public interface IPersonalInfo {

    String getFirstName();
    void setFirstName(String firstName);

    String getLastName();
    void setLastName(String lastName);

    Calendar getDateOfBirth();
    void setDateOfBirth(Calendar dateOfBirth);

    Integer getHeight();
    void setHeight(Integer height);

    Integer getWeight();
    void setWeight(Integer weight);
}
