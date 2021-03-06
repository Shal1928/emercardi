package ru.shal1928.emercardi.app.models.parts;

import ru.shal1928.emercardi.app.models.parts.core.HasRule;
import ru.shal1928.emercardi.app.models.parts.core.IntentGetRuleRecord;
import ru.shal1928.emercardi.app.models.parts.core.IntentSetRuleRecord;

import java.util.Calendar;

/**
 * Created by Danila on 31.10.2016.
 */
@HasRule
public interface IPersonalInfo {

    @IntentGetRuleRecord(value = "FirstName") String getFirstName();
    @IntentSetRuleRecord("FirstName") void setFirstName(String firstName);

    @IntentGetRuleRecord("LastName") String getLastName();
    @IntentSetRuleRecord("LastName") void setLastName(String lastName);

    @IntentGetRuleRecord(value = "DateOfBirth", getModelMethods = {"getTimeInMillis"}) Calendar getDateOfBirth();
    @IntentSetRuleRecord(value = "DateOfBirth") void setDateOfBirth(Calendar calendar);

    @IntentGetRuleRecord("Height") Integer getHeight();
    @IntentSetRuleRecord("Height") void setHeight(Integer height);

    @IntentGetRuleRecord("Weight") Integer getWeight();
    @IntentSetRuleRecord("Weight") void setWeight(Integer weight);
}
