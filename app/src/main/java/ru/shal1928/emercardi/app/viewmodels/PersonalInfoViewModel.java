package ru.shal1928.emercardi.app.viewmodels;

import android.content.Intent;
import android.databinding.ObservableField;
import ru.shal1928.emercardi.app.models.PersonalInfo;
import ru.shal1928.emercardi.app.models.parts.IPersonalInfo;
import ru.shal1928.emercardi.app.models.parts.UserModelProperties;

import java.util.Calendar;

/**
 * Created by Danila on 21.10.2016.
 */
public class PersonalInfoViewModel implements IHasRealModel<IPersonalInfo>, IPersonalInfo, IAwareIntnet {

    //region Observable Fields
    private final ObservableField<String> firstName = new ObservableField<String>();
    private final ObservableField<String> lastName = new ObservableField<String>();
    private final ObservableField<Calendar> dateOfBirth = new ObservableField<Calendar>();
    private final ObservableField<Integer> height = new ObservableField<Integer>();
    private final ObservableField<Integer> weight = new ObservableField<Integer>();
    //endregion

    public PersonalInfoViewModel() {
        //
    }

    public PersonalInfoViewModel(Intent intent) {
        fromIntent(intent);
    }

    @Override public String getFirstName() {
        return this.firstName.get();
    }

    @Override public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    @Override public String getLastName() {
        return this.lastName.get();
    }

    @Override public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    @Override public Calendar getDateOfBirth() {
        return this.dateOfBirth.get();
    }

    @Override public void setDateOfBirth(Calendar dateOfBirth) {
        this.dateOfBirth.set(dateOfBirth);
    }

    @Override public Integer getHeight() {
        return this.height.get();
    }

    @Override public void setHeight(Integer height) {
        this.height.set(height);
    }

    @Override public Integer getWeight() {
        return this.weight.get();
    }

    @Override public void setWeight(Integer weight) {
        this.weight.set(weight);
    }

    @Override public Intent toIntent() {
        Intent intent = new Intent();
        intent.putExtra(UserModelProperties.FIRST_NAME, getFirstName());
        intent.putExtra(UserModelProperties.LAST_NAME, getLastName());
        intent.putExtra(UserModelProperties.BIRTH_DATE, getDateOfBirth());
        intent.putExtra(UserModelProperties.HEIGHT, getHeight());
        intent.putExtra(UserModelProperties.WEIGHT, getWeight());
        return intent;
    }

    @Override public void fromIntent(Intent intent) {
        setFirstName(intent.getStringExtra(UserModelProperties.FIRST_NAME));
        setLastName(intent.getStringExtra(UserModelProperties.LAST_NAME));

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(intent.getLongExtra(UserModelProperties.BIRTH_DATE, 0));
        setDateOfBirth(calendar);

        setHeight(intent.getIntExtra(UserModelProperties.HEIGHT, 0));
        setWeight(intent.getIntExtra(UserModelProperties.WEIGHT, 0));
    }

    @Override public IPersonalInfo getRealModel() {
        return new PersonalInfo(this);
    }
}
