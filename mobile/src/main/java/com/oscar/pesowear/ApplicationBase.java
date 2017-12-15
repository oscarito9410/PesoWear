package com.oscar.pesowear;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by oemy9 on 08/12/2017.
 */

public class ApplicationBase extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        FlowManager.init(new FlowConfig.Builder(this).build());
    }
}
