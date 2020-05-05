package com.jarvishub.emandemo.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.jarvishub.emandemo.data.DataManager;
import com.jarvishub.emandemo.data.db.DbHelper;
import com.jarvishub.emandemo.data.prefs.PreferencesHelper;
import com.jarvishub.emandemo.di.custom_annotation.DatabaseInfo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mrrobot on 8/25/17.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        this.mApplication = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }


    @Provides
    @Singleton
    SharedPreferences provideSharedPrefs() {
        return mApplication.getSharedPreferences("EMANPREFS", Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(Context context) {
        return new DbHelper(context);
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return DbHelper.DB_NAME;
    }

    @Provides
    @DatabaseInfo
    Integer provideDatabaseVersion() {
        return DbHelper.DB_VERSION;
    }




}
