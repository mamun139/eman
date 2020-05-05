package com.jarvishub.emandemo.di.module;

import android.content.Context;

import com.jarvishub.emandemo.data.DataManager;
import com.jarvishub.emandemo.data.db.DbHelper;
import com.jarvishub.emandemo.data.prefs.PreferencesHelper;
import com.jarvishub.emandemo.ui.createOrder.CreateOrderPresenter;
import com.jarvishub.emandemo.ui.createOrder.ICreateOrderPresenter;
import com.jarvishub.emandemo.ui.servicePackages.IServicePackagesPresenter;
import com.jarvishub.emandemo.ui.servicePackages.ServicePackagesPresenter;
import com.jarvishub.emandemo.ui.home.IMapPresenter;
import com.jarvishub.emandemo.ui.home.MapPresenter;
import com.jarvishub.emandemo.ui.login.ILoginPresenter;
import com.jarvishub.emandemo.ui.login.LoginPresenter;
import com.jarvishub.emandemo.ui.signUp.ISignUpPresenter;
import com.jarvishub.emandemo.ui.signUp.SignUpPresenter;
import com.jarvishub.emandemo.ui.splash.ISplashPresenter;
import com.jarvishub.emandemo.ui.splash.SplashPresenter;
import com.jarvishub.emandemo.ui.verification.IVerificationPresenter;
import com.jarvishub.emandemo.ui.verification.VerificationPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mrrobot on 9/11/17.
 */
@Module
public class PresenterModule {
    @Provides
    @Singleton
    ISignUpPresenter provideSignUpPresenter(Context context) {
        return new SignUpPresenter(context);
    }

    @Provides
    @Singleton
    ILoginPresenter provideLoginPresenter(Context context) {
        return new LoginPresenter(context);
    }

    @Provides
    @Singleton
    IMapPresenter provideMapPresenter(Context context) {
        return new MapPresenter(context);
    }

    @Provides
    @Singleton
    IServicePackagesPresenter provideServicePackagePresenter(Context context) {
        return new ServicePackagesPresenter(context);
    }
    @Provides
    @Singleton
    ICreateOrderPresenter createOrderPresenter(Context context) {
        return new CreateOrderPresenter(context);
    }
    @Provides
    @Singleton
       DataManager provideDataManager(Context context, DbHelper dbHelper, PreferencesHelper sharedPref) {
        return new DataManager(context, dbHelper,sharedPref);
    }

    @Provides
    @Singleton
    ISplashPresenter provideSplashPresenter(Context context) {
        return new SplashPresenter(context);
    }

    @Provides
    @Singleton
    IVerificationPresenter provideVerificationPresenter(Context context) {
        return new VerificationPresenter(context);
    }
}
