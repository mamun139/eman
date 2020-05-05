package com.jarvishub.emandemo.di.component;

import com.jarvishub.emandemo.EmanApp;
import com.jarvishub.emandemo.data.DataManager;
import com.jarvishub.emandemo.data.db.DbHelper;
import com.jarvishub.emandemo.data.prefs.PreferencesHelper;
import com.jarvishub.emandemo.data.prefs.IPreferencesHelper;
import com.jarvishub.emandemo.di.module.ApplicationModule;
import com.jarvishub.emandemo.di.module.PresenterModule;
import com.jarvishub.emandemo.network.ApiClient;
import com.jarvishub.emandemo.ui.activePromotion.ActivePromotion;
import com.jarvishub.emandemo.ui.applyPromotion.ApplyPromotion;
import com.jarvishub.emandemo.ui.createOrder.CreateOrderFragment;
import com.jarvishub.emandemo.ui.createOrder.CreateOrderPresenter;
import com.jarvishub.emandemo.ui.createOrder.ICreateOrderPresenter;
import com.jarvishub.emandemo.ui.home.MapPresenter;
import com.jarvishub.emandemo.ui.login.LoginPresenter;
import com.jarvishub.emandemo.ui.orderDetails.OrderDetails;
import com.jarvishub.emandemo.ui.orderHistory.OrderHistory;
import com.jarvishub.emandemo.ui.servicePackages.IServicePackagesPresenter;
import com.jarvishub.emandemo.ui.servicePackages.PackagesFragment;
import com.jarvishub.emandemo.ui.home.HomeActivity;
import com.jarvishub.emandemo.ui.login.LoginActivity;
import com.jarvishub.emandemo.ui.servicePackages.ServicePackagesPresenter;
import com.jarvishub.emandemo.ui.signUp.SignUpActivity;
import com.jarvishub.emandemo.ui.signUp.SignUpPresenter;
import com.jarvishub.emandemo.ui.splash.SplashPresenter;
import com.jarvishub.emandemo.ui.splash.SplashScreenActivity;
import com.jarvishub.emandemo.ui.termsConditions.TermsConditions;
import com.jarvishub.emandemo.ui.track.OrderTrack;
import com.jarvishub.emandemo.ui.verification.VerificationActivity;
import com.jarvishub.emandemo.ui.verification.VerificationPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mrrobot on 28/28/17.
 */

@Singleton
@Component(modules = {ApplicationModule.class, PresenterModule.class})
public interface ApplicationComponent {

    void inject(EmanApp emanApp);

    void inject(SignUpActivity target);

    void inject(SignUpPresenter target);

    void inject(LoginActivity target);

    void inject(LoginPresenter target);

    void inject(PreferencesHelper target);

    void inject(IPreferencesHelper target);

    void inject(DataManager target);

    void inject(DbHelper target);

    void inject(MapPresenter target);

    void inject(HomeActivity target);

    void inject(ServicePackagesPresenter target);

    void inject(PackagesFragment target);

    void inject(CreateOrderPresenter target);

    void inject(CreateOrderFragment target);

    void inject(OrderHistory target);

    void inject(SplashScreenActivity target);

    void inject(OrderTrack target);

    void inject(ApiClient target);

    void inject(OrderDetails target);

    void inject(TermsConditions target);

    void inject(ActivePromotion target);

    void inject(ApplyPromotion target);

    void inject(SplashPresenter target);

    void inject(VerificationPresenter target);

    void inject(VerificationActivity target);
}