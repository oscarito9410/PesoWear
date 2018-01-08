package com.oscar.pesowear;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.facebook.stetho.Stetho;
import com.oscar.pesowear.utils.di.SharedPreferencesModule;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by oemy9 on 08/12/2017.
 */

public class ApplicationBase extends MultiDexApplication {
    private static Context context;
    public static ApplicationBase instance;
    @Override
    public void onCreate() {
        super.onCreate();
        initApplication();
    }
    public static ApplicationBase getIntance() {
        if(instance==null){
            instance=new ApplicationBase();
        }
        return instance;
    }
    private void initApplication() {
        Stetho.initializeWithDefaults(this);
        FlowManager.init(new FlowConfig.Builder(this).build());
        instance = this;
        context = getApplicationContext();
    }


}
