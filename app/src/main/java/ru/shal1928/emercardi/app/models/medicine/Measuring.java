package ru.shal1928.emercardi.app.models.medicine;

import ru.shal1928.emercardi.app.R;
import ru.shal1928.emercardi.app.models.parts.HasResId;

/**
 * Created by shal1928 on 05.06.2016.
 */
public enum Measuring implements HasResId {
    MKG(R.string.µgString), MG(R.string.mgString), ML(R.string.mlString);

    private int resId;

    Measuring(int resId) {
        this.resId = resId;
    }

    public int getResId() {
        return resId;
    }
}
