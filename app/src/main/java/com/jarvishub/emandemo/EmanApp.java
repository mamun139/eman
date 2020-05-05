package com.jarvishub.emandemo;

import android.app.Application;

import com.jarvishub.emandemo.di.component.ApplicationComponent;
import com.jarvishub.emandemo.di.component.DaggerApplicationComponent;
import com.jarvishub.emandemo.di.module.ApplicationModule;

import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

/**
 * Created by mrrobot on 8/17/17.
 */

@ReportsCrashes(mailTo = "ruhul@jarvishub.com", customReportContent = {
        ReportField.APP_VERSION_CODE, ReportField.APP_VERSION_NAME,
        ReportField.ANDROID_VERSION, ReportField.PHONE_MODEL,
        ReportField.CUSTOM_DATA, ReportField.STACK_TRACE, ReportField.LOGCAT}, mode = ReportingInteractionMode.TOAST, resToastText = R.string.crash_toast_text)
public class EmanApp extends Application {

    public static final String TAG = EmanApp.class.getSimpleName();
    public ApplicationComponent mApplicationComponent;
    private static EmanApp mInstance;

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mApplicationComponent = initDagger(this);

      /*  // The following line triggers the initialization of ACRA for crash Log Reposrting
        if (PreferenceHelper.getPrefernceHelperInstace().getBoolean(
                this, PreferenceHelper.SUBMIT_LOGS, true)) {
            ACRA.init(this);
        }*/
        ACRA.init(this);
    }

    protected ApplicationComponent initDagger(EmanApp application) {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(application))
                .build();
    }

    public static synchronized EmanApp getInstance() {
        return mInstance;
    }
}
