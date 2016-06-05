package ru.shal1928.emercardi.app.models.medicine;

import ru.shal1928.emercardi.app.R;
import ru.shal1928.emercardi.app.models.parts.HasResId;

/**
 * Created by shal1928 on 05.06.2016.
 */
public enum  AdditionalMeasuring implements HasResId {
    FULL(R.string.PartFullString), HALF(R.string.PartHalfString), QUART(R.string.PartQuartString),
    EIGHTH(R.string.PartEighthString);

    private int resId;

    AdditionalMeasuring(int resId) {
        this.resId = resId;
    }

    public int getResId() {
        return resId;
    }
}
