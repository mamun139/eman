package com.jarvishub.emandemo.ui.splash;

import android.content.Context;

/**
 * Created by mrrobot on 8/21/17.
 */

public interface ISplashPresenter {
    void doBackGroundTasks();
    void setView(SplashView view, Context context);
}
