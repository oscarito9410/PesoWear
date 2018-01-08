package com.oscar.pesowear.utils.di;

import android.content.Context;

import com.oscar.maincore.Utils.SessionManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by oemy9 on 07/01/2018.
 */
@Module
public class SharedPreferencesModule {

    private Context ctx;

    public SharedPreferencesModule (Context ctx) {
        this.ctx = ctx;
    }

    @Provides
    @Singleton
    public SessionManager providesSesionManager(){
        return new SessionManager(this.ctx);
    }
}
