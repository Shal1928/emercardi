package ru.shal1928.emercardi.app.models.parts;

import java.util.Calendar;

/**
 * Created by Danila on 31.10.2016.
 */
public interface IPersonalInfo {

    @IntentGetRuleRecord("FirstName") String getFirstName();

    void setFirstName(String firstName);

    @IntentGetRuleRecord("LastName") String getLastName();

    void setLastName(String lastName);

    @IntentGetRuleRecord("DateOfBirth") Long getDateOfBirth();

    void setDateOfBirth(Long dateOfBirth);

    @IntentGetRuleRecord("Height") Integer getHeight();

    void setHeight(Integer height);

    @IntentGetRuleRecord("Weight") Integer getWeight();

    void setWeight(Integer weight);
}
