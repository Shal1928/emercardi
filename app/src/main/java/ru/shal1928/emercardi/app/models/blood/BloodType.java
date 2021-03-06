package ru.shal1928.emercardi.app.models.blood;

import ru.shal1928.emercardi.app.R;
import ru.shal1928.emercardi.app.models.parts.HasResId;

/**
 * Blood Types.
 */
public enum BloodType implements HasResId {
    O(R.string.blood_type_O), A(R.string.blood_type_A), B(R.string.blood_type_B), AB(R.string.blood_type_AB);

    private int resId;

    BloodType(int resId) {
        this.resId = resId;
    }

    public int getResId() {
        return resId;
    }
}
