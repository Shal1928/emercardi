package ru.shal1928.emercardi.app.viewmodels;

import android.content.Intent;

/**
 * Created by Danila on 21.10.2016.
 */
public interface IAwareIntnet {
    Intent toIntent();
    void fromIntent(Intent intent);
}
